package com.sf.shiva.oms.ht.web.controller.redis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;

import org.apache.commons.pool2.PooledObject;
import org.apache.commons.pool2.PooledObjectFactory;
import org.apache.commons.pool2.impl.DefaultPooledObject;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.log4j.Logger;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPubSub;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.Protocol;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.exceptions.JedisConnectionException;
import redis.clients.util.Hashing;
import redis.clients.util.Pool;

/**
 * Redis多实例监控池
 * @author 670513
 *
 */
public class ShardedJedisSentinelPool extends Pool<ShardedJedis> {

	/**
	 * 尝试获取监控的次数
	 */
	public static final int MAX_RETRY_SENTINEL = 10;

	private Logger log = Logger.getLogger(ShardedJedisSentinelPool.class);
	/*protected final Logger log = Logger.getLogger(getClass().getName());*/

	/**
	 * 实例池
	 */
	protected GenericObjectPoolConfig poolConfig;

	/**
	 * 默认sockey连接超时时间
	 */
    protected int timeout = Protocol.DEFAULT_TIMEOUT;

    private int sentinelRetry = 0;

    protected String password;

    protected int database = Protocol.DEFAULT_DATABASE;

    protected Set<MasterListener> masterListeners = new HashSet<MasterListener>();

    private volatile List<HostAndPort> currentHostMasters;//当前的主实例队列

    //当前主备对应关系
    private ConcurrentHashMap<String, String> currentMasterSlaves = new ConcurrentHashMap<String, String>();

	private Jedis jedis;

    public ShardedJedisSentinelPool(List<String> masters, Set<String> sentinels) {
		this(masters, sentinels, new GenericObjectPoolConfig(),
			Protocol.DEFAULT_TIMEOUT, null, Protocol.DEFAULT_DATABASE);
    }

    public ShardedJedisSentinelPool(List<String> masters, Set<String> sentinels, String password) {
		this(masters, sentinels, new GenericObjectPoolConfig(),
			Protocol.DEFAULT_TIMEOUT, password);
    }

    public ShardedJedisSentinelPool(final GenericObjectPoolConfig poolConfig, List<String> masters, Set<String> sentinels) {
		this(masters, sentinels, poolConfig, Protocol.DEFAULT_TIMEOUT, null,
			Protocol.DEFAULT_DATABASE);
    }

    public ShardedJedisSentinelPool(List<String> masters, Set<String> sentinels,
	    final GenericObjectPoolConfig poolConfig, int timeout,
	    final String password) {
		this(masters, sentinels, poolConfig, timeout, password,
			Protocol.DEFAULT_DATABASE);
    }

    public ShardedJedisSentinelPool(List<String> masters, Set<String> sentinels,
	    final GenericObjectPoolConfig poolConfig, final int timeout) {
		this(masters, sentinels, poolConfig, timeout, null,
			Protocol.DEFAULT_DATABASE);
    }

    public ShardedJedisSentinelPool(List<String> masters, Set<String> sentinels,
	    final GenericObjectPoolConfig poolConfig, final String password) {
		this(masters, sentinels, poolConfig, Protocol.DEFAULT_TIMEOUT,
			password);
    }

    public ShardedJedisSentinelPool(List<String> masters, Set<String> sentinels,
	    final GenericObjectPoolConfig poolConfig, int timeout,
	    final String password, final int database) {
		this.poolConfig = poolConfig;
		this.timeout = timeout;
		this.password = password;
		this.database = database;

		List<HostAndPort> masterList = initSentinels(sentinels, masters);
		initPool(masterList);
    }

    /**
     * 销毁线程池
     */
    @Override
	public void destroy() {
		for (MasterListener m : masterListeners) {
		    m.shutdown();
		}

		super.destroy();
    }

   /**
    * 获取当前主实例的IP和端口
    */
    public List<HostAndPort> getCurrentHostMaster() {
    	return currentHostMasters;
    }

    /**
     * 初始化线程池
     * @param masters
     */
    private void initPool(List<HostAndPort> masters) {
    	if (!equals(currentHostMasters, masters)) {
    		StringBuffer sb = new StringBuffer();
    		for (HostAndPort master : masters) {
    			sb.append(master.toString());
    			sb.append(" ");
    		}
    		/*log.info("Created ShardedJedisPool to master at [" + sb.toString() + "]");*/
//    		log.info(CommonLogUtil.createSuccessMessageFix("Created ShardedJedisPool to master at [" + sb.toString() + "]"));
    		List<JedisShardInfo> shardMasters = makeShardInfoList(masters);
    		initPool(poolConfig, new ShardedJedisFactory(shardMasters, Hashing.MURMUR_HASH, null));
    		currentHostMasters = masters;
    	}
    }

    /**
     * 当前主机是否已指定主机一致
     * @param currentShardMasters
     * @param shardMasters
     * @return
     */
    private boolean equals(List<HostAndPort> currentShardMasters, List<HostAndPort> shardMasters) {
    	if (currentShardMasters != null && shardMasters != null) {
    		if (currentShardMasters.size() == shardMasters.size()) {
    			for (int i = 0; i < currentShardMasters.size(); i++) {
    				if (!currentShardMasters.get(i).equals(shardMasters.get(i))) return false;
    			}
    			return true;
    		}
    	}
		return false;
	}

    /**
     * 组装JedisShardInfo对象
     * 根据IP和端口
     * @param masters
     * @return
     */
	private List<JedisShardInfo> makeShardInfoList(List<HostAndPort> masters) {
		List<JedisShardInfo> shardMasters = new ArrayList<JedisShardInfo>();
		for (HostAndPort master : masters) {
			JedisShardInfo jedisShardInfo = new JedisShardInfo(master.getHost(), master.getPort(), timeout);
			jedisShardInfo.setPassword(password);

			shardMasters.add(jedisShardInfo);
		}
		return shardMasters;
	}

	/**
	 * 初始化Sentinel哨兵
	 * 监控
	 * @param sentinels
	 * @param masters
	 * @return
	 */
	private List<HostAndPort> initSentinels(Set<String> sentinels, final List<String> masters) {

    	Map<String, HostAndPort> masterMap = new HashMap<String, HostAndPort>();
    	List<HostAndPort> shardMasters = new ArrayList<HostAndPort>();
//    	log.info(CommonLogUtil.createSuccessMessageFix("Trying to find all master from available Sentinels..."));
	    /*log.info("Trying to find all master from available Sentinels...");*/

	    for (String masterName : masters) {
	    	HostAndPort master = null;
	    	boolean fetched = false;

	    	while (!fetched && sentinelRetry < MAX_RETRY_SENTINEL) {
	    		for (String sentinel : sentinels) {
					final HostAndPort hap = toHostAndPort(Arrays.asList(sentinel.split(":")));

					//log.info("Connecting to Sentinel " + hap);

					try {
					    jedis = new Jedis(hap.getHost(), hap.getPort());
					    master = masterMap.get(masterName);
					    if (master == null) {
					    	List<String> hostAndPort = jedis.sentinelGetMasterAddrByName(masterName);
					    	if (hostAndPort != null && hostAndPort.size() > 0) {
					    		master = toHostAndPort(hostAndPort);
								//log.info("Found Redis master at " + master);
								initMasterSlave(hostAndPort, jedis.sentinelSlaves(masterName));
								shardMasters.add(master);
								masterMap.put(masterName, master);
								fetched = true;
								jedis.disconnect();
								break;
					    	}
					    }
					} catch (JedisConnectionException e) {
					    log.error("Cannot connect to sentinel running @ " + hap + ". Trying next one.");
					}
		    	}

		    	if (null == master) {
		    		try {
						log.error("All sentinels down, cannot determine where is "
							+ masterName + " master is running... sleeping 1000ms, Will try again.");
						Thread.sleep(1000);
				    } catch (InterruptedException e) {
				    	log.error("redis sleep is exception");
				    }
		    		fetched = false;
		    		sentinelRetry++;
		    	}
	    	}

	    	// Try MAX_RETRY_SENTINEL times.
	    	if (!fetched && sentinelRetry >= MAX_RETRY_SENTINEL) {
	    		log.error("All sentinels down and try " + MAX_RETRY_SENTINEL + " times, Abort.");
	    		throw new JedisConnectionException("Cannot connect all sentinels, Abort.");
	    	}
	    }

	    // All shards master must been accessed.
	    if (masters.size() != 0 && masters.size() == shardMasters.size()) {
//	    	log.info(CommonLogUtil.createSuccessMessageFix("Starting Sentinel listeners..."));
	    	/*log.info("Starting Sentinel listeners...");*/
			for (String sentinel : sentinels) {
			    final HostAndPort hap = toHostAndPort(Arrays.asList(sentinel.split(":")));
			    MasterListener masterListener = new MasterListener(masters, hap.getHost(), hap.getPort());
			    masterListeners.add(masterListener);
			    masterListener.start();
			}
	    }

		return shardMasters;
    }

	/**
	 * 初始化主实例
	 * @param hostAndPort
	 * @param sentinelSlaves
	 */
    private void initMasterSlave(List<String> hostAndPort,
			List<Map<String, String>> sentinelSlaves) {
    	if (hostAndPort == null || hostAndPort.size() == 0
    		|| sentinelSlaves == null || sentinelSlaves.size() == 0){
    		return;
    	}

		String hostAndPortStr = hostAndPort.get(0) + ":" + hostAndPort.get(1);
		String slaveHostAndPort = sentinelSlaves.get(0).get("ip") + ":" + sentinelSlaves.get(0).get("port");
		currentMasterSlaves.put(hostAndPortStr, slaveHostAndPort);
	}

	private HostAndPort toHostAndPort(List<String> getMasterAddrByNameResult) {
    	String host = getMasterAddrByNameResult.get(0);
    	int port = Integer.parseInt(getMasterAddrByNameResult.get(1));

    	return new HostAndPort(host, port);
    }

    /**
     * PoolableObjectFactory custom impl.
     */
    protected static class ShardedJedisFactory implements PooledObjectFactory<ShardedJedis> {
		private List<JedisShardInfo> shards;
		private Hashing algo;
		private Pattern keyTagPattern;
		
		private Logger log = Logger.getLogger(ShardedJedisFactory.class);

		public ShardedJedisFactory(List<JedisShardInfo> shards, Hashing algo, Pattern keyTagPattern) {
		    this.shards = shards;
		    this.algo = algo;
		    this.keyTagPattern = keyTagPattern;
		}

		@Override
		public PooledObject<ShardedJedis> makeObject() throws Exception {
		    ShardedJedis jedis = new ShardedJedis(shards, algo, keyTagPattern);
		    return new DefaultPooledObject<ShardedJedis>(jedis);
		}

		@Override
		public void destroyObject(PooledObject<ShardedJedis> pooledShardedJedis) throws Exception {
		    final ShardedJedis shardedJedis = pooledShardedJedis.getObject();
		    for (Jedis jedis : shardedJedis.getAllShards()) {
				try {
			    	jedis.quit();
				    jedis.disconnect();
				} catch (Exception e) {
					log.error("ShardedJedisFactory destroyObject error is {}",e);
				}
		    }
		}

		@Override
		public boolean validateObject(PooledObject<ShardedJedis> pooledShardedJedis) {
		    try {
			ShardedJedis jedis = pooledShardedJedis.getObject();
			for (Jedis shard : jedis.getAllShards()) {
			    if (!shard.ping().equals("PONG")) {
				return false;
			    }
			}
			return true;
		    } catch (Exception ex) {
			return false;
		    }
		}

		@Override
		public void activateObject(PooledObject<ShardedJedis> p) throws Exception {

		}

		@Override
		public void passivateObject(PooledObject<ShardedJedis> p) throws Exception {

		}
    }

    protected class JedisPubSubAdapter extends JedisPubSub {
		@Override
		public void onMessage(String channel, String message) {
		}

		@Override
		public void onPMessage(String pattern, String channel, String message) {
		}

		@Override
		public void onPSubscribe(String pattern, int subscribedChannels) {
		}

		@Override
		public void onPUnsubscribe(String pattern, int subscribedChannels) {
		}

		@Override
		public void onSubscribe(String channel, int subscribedChannels) {
		}

		@Override
		public void onUnsubscribe(String channel, int subscribedChannels) {
		}
    }

    /**
     * 监听线程，使用订阅模式
     * @author 670513
     *
     */
    protected class MasterListener extends Thread {

		protected List<String> masters;
		protected String host;
		protected int port;
		protected long subscribeRetryWaitTimeMillis = 5000;
		protected Jedis jedis;
		protected AtomicBoolean running = new AtomicBoolean(false);

		protected MasterListener() {
		}

		public MasterListener(List<String> masters, String host, int port) {
		    this.masters = masters;
		    this.host = host;
		    this.port = port;
		}

		public MasterListener(List<String> masters, String host, int port,
			long subscribeRetryWaitTimeMillis) {
		    this(masters, host, port);
		    this.subscribeRetryWaitTimeMillis = subscribeRetryWaitTimeMillis;
		}

		@Override
		public void run() {

		    running.set(true);

		    while (running.get()) {

			jedis = new Jedis(host, port);

			try {
			    jedis.subscribe(new JedisPubSubAdapter() {
					@Override
					public void onMessage(String channel, String message) {
					   // log.info("Sentinel " + host + ":" + port + " published: " + message + ".");

					    String[] switchMasterMsg = message.split(" ");

					    if (switchMasterMsg.length > 3) {

					    	int index = masters.indexOf(switchMasterMsg[0]);
						    if (index >= 0) {
						    	HostAndPort newHostMaster = toHostAndPort(Arrays.asList(switchMasterMsg[3], switchMasterMsg[4]));
						    	List<HostAndPort> newHostMasters = new ArrayList<HostAndPort>();
						    	for (int i = 0; i < masters.size(); i++) {
						    		newHostMasters.add(null);
						    	}
						    	Collections.copy(newHostMasters, currentHostMasters);
						    	newHostMasters.set(index, newHostMaster);

						    	initPool(newHostMasters);
						    } else {
						    	StringBuffer sb = new StringBuffer();
						    	for (String masterName : masters) {
						    		sb.append(masterName);
						    		sb.append(",");
						    	}
//							    log.info("Ignoring message on +switch-master for master name "
//								    + switchMasterMsg[0]
//								    + ", our monitor master name are ["
//								    + sb + "]");
							}

					    } else {
							log.error("Invalid message received on Sentinel "
								+ host
								+ ":"
								+ port
								+ " on channel +switch-master: "
								+ message);
					    }
					}
			    }, "+switch-master");

			} catch (JedisConnectionException e) {

			    if (running.get()) {
//					log.info("Lost connection to Sentinel at " + host
//						+ ":" + port
//						+ ". Sleeping 5000ms and retrying.");
					try {
					    Thread.sleep(subscribeRetryWaitTimeMillis);
					} catch (InterruptedException e1) {
					    e1.printStackTrace();
					}
			    } else {
					log.error("Unsubscribing from Sentinel at " + host + ":"
						+ port);
			    }
			}
		    }
		}

		public void shutdown() {
		    try {
				//log.info("Shutting down listener on " + host + ":" + port);
				running.set(false);
				// This isn't good, the Jedis object is not thread safe
				jedis.disconnect();
		    } catch (Exception e) {
		    	log.error("Caught exception while shutting down: " + e.getMessage());
		    }
		}
    }

    /**
     * 根据主机的IP和端口 获取该主机对应的从机
     * 参数说明例子：
     *     主机IP是192.168.0.11 端口是6379
     *     那么参数hostAndPort应该为：192.168.0.11:6379
     * @param hostAndPort
     * @return
     */
	public HostAndPort getSlaveByMaster(String hostAndPort) {
		String value = currentMasterSlaves.get(hostAndPort);
		if (value != null){
			String[] hostAndport = value.split(":");
			return new HostAndPort(hostAndport[0], Integer.parseInt(hostAndport[1]));
		}
		return null;
	}


}
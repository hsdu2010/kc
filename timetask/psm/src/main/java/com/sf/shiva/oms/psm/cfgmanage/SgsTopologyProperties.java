package com.sf.shiva.oms.psm.cfgmanage;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 描述：SGS Topology配置信息
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年12月21日      01369610         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 01369610
 * @since 1.0
 */
@Component
public class SgsTopologyProperties implements Serializable {

	/**  */
	private static final long serialVersionUID = 5663175317044450049L;

	@Value("${sgs.topology.max.spout.pending:500}")
	private int maxSpoutPending;// 一次读取kafka会读取多少数据
	@Value("${javaOpts}")
	private String javaOpts;// 自定义JAVA配置
	@Value("${topology.sgs.debugFlag}")
	private boolean debugFlag;// 是否调试模式
	@Value("${topology.sgs.workerNum}")
	private int workerNum;// SGS worker数
	@Value("${topology.sgsSpout.threadNum}")
	private int spoutThreadNum;// SGS计算Spout线程数
	@Value("${topology.sgsBolt.threadNum}")
	private int boltThreadNum;// SGS计算bolt线程数
	@Value("${topology.sgsPackageStatusSendBolt.threadNum}")
	private int packageStatusSendBoltThreadNum;// 包裹状态发送Bolt线程数

	public int getMaxSpoutPending() {
		return maxSpoutPending;
	}

	public void setMaxSpoutPending(int maxSpoutPending) {
		this.maxSpoutPending = maxSpoutPending;
	}

	public String getJavaOpts() {
		return javaOpts;
	}

	public void setJavaOpts(String javaOpts) {
		this.javaOpts = javaOpts;
	}

	public boolean isDebugFlag() {
		return debugFlag;
	}

	public void setDebugFlag(boolean debugFlag) {
		this.debugFlag = debugFlag;
	}

	public int getWorkerNum() {
		return workerNum;
	}

	public void setWorkerNum(int workerNum) {
		this.workerNum = workerNum;
	}

	public int getSpoutThreadNum() {
		return spoutThreadNum;
	}

	public void setSpoutThreadNum(int spoutThreadNum) {
		this.spoutThreadNum = spoutThreadNum;
	}

	public int getBoltThreadNum() {
		return boltThreadNum;
	}

	public void setBoltThreadNum(int boltThreadNum) {
		this.boltThreadNum = boltThreadNum;
	}

	public int getPackageStatusSendBoltThreadNum() {
		return packageStatusSendBoltThreadNum;
	}

	public void setPackageStatusSendBoltThreadNum(int packageStatusSendBoltThreadNum) {
		this.packageStatusSendBoltThreadNum = packageStatusSendBoltThreadNum;
	}

}

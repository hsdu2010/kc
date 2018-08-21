package com.sf.shiva.oms.ht.service.hbase;

import java.util.List;

import com.github.pagehelper.Page;
import com.sf.shiva.oms.ht.domain.HbaseQueryCfg;
import com.sf.shiva.oms.ht.domain.HbaseQueryCfgExample;
import com.sf.shiva.oms.ht.dto.HBaseQueryResultDto;

/**
 * 
 * 描述：hbase数据查询服务
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2018年6月4日      01369626         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 01369626
 * @since 1.0
 */
public interface HBaseQueryService {

    /**
     * 保存hbase查询配置
     * 
     * @param cfg
     *            hbase查询配置
     * @author 01369626
     * @date 2018年6月15日
     */
    public void saveHBaseQueryCfg(HbaseQueryCfg cfg);

    /**
     * 更新hbase查询配置
     * 
     * @param cfg
     * @author 01369626
     * @date 2018年6月15日
     */
    public void updateHBaseQueryCfg(HbaseQueryCfg cfg);

    /**
     * 删除hbase查询配置
     * 
     * @param id
     *            配置主键id
     * @author 01369626
     * @date 2018年6月15日
     */
    public void deleteHBaseQueryCfg(String id);

    /**
     * 分页获取Hbase查询配置
     * 
     * @param example
     *            查询条件
     * @param pageNum
     *            页码
     * @param pageSize
     *            每页条数
     * @return 查询结果
     * @author 01369626
     * @date 2018年6月20日
     */
    public Page<HbaseQueryCfg> selectCfgs(HbaseQueryCfgExample example, Integer pageNum, Integer pageSize);
    
    /**
     * 根据id获取Hbase查询配置
     * @param id
     * @return
     * @author 01369626
     * @date 2018年6月28日
     */
    public HbaseQueryCfg selectById(String id);
    
    /**
     * 查询Hbase
     * @param cfg 查询信息
     * @return 查询结果
     * @author 01369626
     * @date 2018年6月28日
     */
    public List<HBaseQueryResultDto> queryHbase(HbaseQueryCfg cfg);
}

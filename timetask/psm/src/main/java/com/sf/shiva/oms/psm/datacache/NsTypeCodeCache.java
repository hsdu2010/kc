package com.sf.shiva.oms.psm.datacache;

/**
 * 
 * 描述：号段类型代码缓存接口
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2018年10月15日      01369626         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 01369626
 * @since 1.0
 */
public interface NsTypeCodeCache {

    /**
     * 根据号段代码，获取对应的号段类型代码
     * 
     * @param nsCode
     *            号段代码
     * @return 号段代码对应的号段类型代码
     * @author 01369626
     * @date 2018年10月15日
     */
    public String getNsTypeCode(String nsCode);

}

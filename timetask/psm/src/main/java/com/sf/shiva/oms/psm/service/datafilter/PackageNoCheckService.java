package com.sf.shiva.oms.psm.service.datafilter;

/**
 * 
 * 描述：包裹号校验服务接口
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
public interface PackageNoCheckService {

    /**
     * 校验包裹号是否为合法运单号
     * 
     * @param packageNo
     *            包裹号
     * @return true:合法运单号;false:非合法运单号
     * @author 01369626
     * @date 2018年10月15日
     */
    public boolean validPackageNo(String packageNo);

}

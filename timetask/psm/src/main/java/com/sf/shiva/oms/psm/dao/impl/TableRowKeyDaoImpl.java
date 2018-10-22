package com.sf.shiva.oms.psm.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.hadoop.hbase.util.MD5Hash;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sf.shiva.oms.psm.cfgmanage.HbaseProperties;
import com.sf.shiva.oms.psm.common.annotation.NameSpace;
import com.sf.shiva.oms.psm.common.annotation.TableName;
import com.sf.shiva.oms.psm.common.constants.PackageStatusConstant;
import com.sf.shiva.oms.psm.common.enumtype.TableNameEnum;
import com.sf.shiva.oms.psm.common.utils.DateFormatUtil;
import com.sf.shiva.oms.psm.common.utils.HashModUtil;
import com.sf.shiva.oms.psm.dao.TableRowKeyDao;

import scala.collection.mutable.StringBuilder;

/**
 * 
 * 描述：hbase表RowKey获取类
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年12月5日      568677         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 568677
 * @since 1.0
 */
@Component
public class TableRowKeyDaoImpl implements TableRowKeyDao, InitializingBean {

    @Autowired
    private HbaseProperties hbaseProperties;

    private Map<String, Integer> tableParNumMap;

    @Override
    public void afterPropertiesSet() throws Exception {
        tableParNumMap = new HashMap<>();
        tableParNumMap.put(TableNameEnum.SHIVA_OMS_PSM_PACKAGE_STATUS.getName(), hbaseProperties.getPackageStatusParNum());
        tableParNumMap.put(TableNameEnum.SHIVA_OMS_PSM_PACKAGE_STATUS_EVENT.getName(), hbaseProperties.getPackageStatusEventParNum());
        tableParNumMap.put(TableNameEnum.SHIVA_OMS_PSM_PACKAGE_STATUS_SEND_LOAD.getName(), hbaseProperties.getPackageStatusSendLoadParNum());
        tableParNumMap.put(TableNameEnum.SHIVA_OMS_PSM_PACKAGE_SETTLE_CLAIMS.getName(), hbaseProperties.getPackageSettleClaimsNum());
    }

    /**
     * 获取表的分区数
     * 
     * @param nameSpace
     *            命名空间
     * @param tableName
     *            表名称
     * @return 分区数
     * @author 568677
     * @date 2017年12月5日
     */
    private Integer getTableParNum(String nameSpace, String tableName) {
        return tableParNumMap.get((new StringBuilder(nameSpace).append(PackageStatusConstant.UNDER_LINE).append(tableName)).toString());
    }

    /**
     * 获取RowKey前缀（分区号），分区数为空或0时，使用MD5Hash.getMD5AsHex获取前缀，长度为8；否则使用hash取模，长度为3位，
     * 不够自动补0
     * 
     * @param packageNo
     *            包裹号
     * @param tableParNum
     *            表的分区数
     * @return RowKey前缀（分区号）
     * @author 568677
     * @date 2017年12月5日
     */
    private static String getRowKeyPrefix(String packageNo, Integer tableParNum) {
        if (null == tableParNum || 0 == tableParNum) {// 使用散列方式
            return MD5Hash.getMD5AsHex(packageNo.getBytes()).substring(0, 3);
        } else {// 使用hash取模的方式
            return String.format("%03d", HashModUtil.hashMod(packageNo, tableParNum));// 0代表前面补充0,3代表长度，d代表参数为正数
        }
    }

    @Override
    public <T> String getRowKey(String packageNo, Class<T> clazz) {
        String nameSpace = clazz.getAnnotation(NameSpace.class).value();// 获取entity的命名空间
        String tableName = clazz.getAnnotation(TableName.class).value(); // 获取entity的tableName
        String rowKeyPrefix = getRowKeyPrefix(packageNo, getTableParNum(nameSpace, tableName));// 获取前缀
        return new StringBuilder(rowKeyPrefix).append(PackageStatusConstant.UNDER_LINE).append(packageNo).toString();
    }

    @Override
    public <T> String getRowKey(String packageNo, Date operTm, Class<T> clazz) {
        String nameSpace = clazz.getAnnotation(NameSpace.class).value();// 获取entity的命名空间
        String tableName = clazz.getAnnotation(TableName.class).value(); // 获取entity的tableName
        String rowKeyPrefix = getRowKeyPrefix(packageNo, getTableParNum(nameSpace, tableName));// 获取前缀
        return new StringBuilder(rowKeyPrefix).append(PackageStatusConstant.UNDER_LINE).append(packageNo).append(PackageStatusConstant.UNDER_LINE)
                .append(DateFormatUtil.format(operTm, DateFormatUtil.D_YYYYMMDD, DateFormatUtil.T_HHMMSSSSS, false)).toString();
    }

    @Override
    public <T> String getRowKey(String packageNo, String packageStatus, Date operTm, Class<T> clazz) {
        String nameSpace = clazz.getAnnotation(NameSpace.class).value();// 获取entity的命名空间
        String tableName = clazz.getAnnotation(TableName.class).value(); // 获取entity的tableName
        String rowKeyPrefix = getRowKeyPrefix(packageNo, getTableParNum(nameSpace, tableName));// 获取前缀
        return new StringBuilder(rowKeyPrefix).append(PackageStatusConstant.UNDER_LINE).append(packageNo).append(PackageStatusConstant.UNDER_LINE).append(packageStatus).append(PackageStatusConstant.UNDER_LINE)
                .append(DateFormatUtil.format(operTm, DateFormatUtil.D_YYYYMMDD, DateFormatUtil.T_HHMMSSSSS, false)).toString();
    }
}

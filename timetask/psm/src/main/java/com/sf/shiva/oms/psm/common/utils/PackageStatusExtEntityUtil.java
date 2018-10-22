package com.sf.shiva.oms.psm.common.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.sf.shiva.oms.psm.common.entity.PackageStatusRecordEntity;
import com.sf.shiva.oms.psm.common.entity.PackageStatusRecordExtendEntity;

/**
 * 
 * 描述：包裹状态扩展字段处理单元类
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2018年1月9日      01369626         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 01369626
 * @since 1.0
 */
public class PackageStatusExtEntityUtil {

    private PackageStatusExtEntityUtil() {

    }

    /**
     * 填充包裹流水事件扩展属性
     * 
     * @param entity
     *            包裹流水事件对象
     * @param key
     *            属性
     * @param value
     *            属性对应的值
     * @author 01369626
     * @date 2018年1月9日
     */
    public static void fillExtendEntity(PackageStatusRecordEntity entity, String key, String value) {
        if (StringUtils.isNotEmpty(value)) {
            PackageStatusRecordExtendEntity extEntity = new PackageStatusRecordExtendEntity();
            extEntity.setKey(key);
            extEntity.setValue(value);
            List<PackageStatusRecordExtendEntity> extList = entity.getExtendInfoList();
            if (null == extList) {
                extList = new ArrayList<>();
                entity.setExtendInfoList(extList);
            }
            extList.add(extEntity);
        }
    }
}

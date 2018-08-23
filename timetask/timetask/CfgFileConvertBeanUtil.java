package com.sf.shiva.oms.packagestatus.cal.common.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 描述：配置文件转换成为对象
 *
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年11月30日      80002517         Create
 * ****************************************************************************
 * </pre>
 * @author 80002517
 * @since 1.0
 */
public class CfgFileConvertBeanUtil {
	private static final Logger logger = LoggerFactory.getLogger(CfgFileConvertBeanUtil.class);
    /** 
     * 读取配置文件内容转换成为bean对象
     * @param file 文件名
     * @param type 对象类型
     * @return 对象集合
     * @author 80002517
     * @date 2017年11月30日
     */
    public static <T> List<T> convert(String file, Class<T> type){
        try {
            URL url = CfgFileConvertBeanUtil.class.getClassLoader().getResource(file);
            if(url != null){
                String content = FileUtils.readFileToString(new File(url.getPath()));
                if(StringUtils.isNotBlank(content)){
                    List<T> list = new ArrayList<>();//FastJsonConvertUtil.convertJSONToArray(content, type);
                    if(list != null){
                        return list;
                    }
                }
            }
        } catch (IOException e) {
        	logger.error("CfgFileConvertBeanUtil convert error", e);
        }
        return Collections.emptyList();
    }
}

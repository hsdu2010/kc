package com.sf.shiva.oms.ht.common.utils;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * 描述：字符串工具类
 *
 * <pre>
 * HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年3月9日      568677         Create
 * ****************************************************************************
 * </pre>
 * 
 * @author 568677
 * @since 1.0
 */
public class StringUtil {
    private StringUtil() {
    }

    /**
     * 判断字符串searchSeq是否在seq中存在
     * 
     * @param seq
     *            指定字符串
     * @param searchSeq
     *            要查询的字符串
     * @param split
     *            分隔符号，比如逗号
     * @return true：存在；false：不存在
     * @author zhangYao 568677
     * @date 2016年3月25日
     */
    public static boolean isExist(String seq, String searchSeq, String split) {
        if (StringUtils.isNotEmpty(split)) {
            // 拼接开始和结束的标识例如：,1,2,3,4,5,
            StringBuilder seqStr = new StringBuilder();
            seqStr.append(split).append(seq).append(split);

            StringBuilder searchSeqStr = new StringBuilder();
            searchSeqStr.append(split).append(searchSeq).append(split);

            return -1 != StringUtils.indexOf(seqStr, searchSeqStr);
        } else {
            return -1 != StringUtils.indexOf(seq, searchSeq);
        }
    }
}

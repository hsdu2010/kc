package com.sf.shiva.oms.psm.common.utils;

import java.math.BigInteger;

/**
 * 
 * 描述：hash取模工具类
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
public class HashModUtil {

    private HashModUtil() {
    }

    /**
     * hash取模工具
     * 
     * @param str
     *            字符串
     * @param mod
     *            模值
     * @return 取模
     * @author 568677
     * @date 2017年12月5日
     */
    public static int hashMod(String str, int mod) {
        return new BigInteger(String.valueOf(str.replace("\'", " ").trim().hashCode())).abs().intValue() & (mod - 1);
    }
}

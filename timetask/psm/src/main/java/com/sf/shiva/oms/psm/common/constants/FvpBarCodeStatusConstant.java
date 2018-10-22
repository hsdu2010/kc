package com.sf.shiva.oms.psm.common.constants;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
/**
 * 
 * 描述：巴枪状态推断状态,0,1,4不过滤
 *
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2018年1月8日      01369521         Create
 * ****************************************************************************
 * </pre>
 * @author 01369521
 * @since 1.0
 */
public class FvpBarCodeStatusConstant {
    
    public static final Integer ASSUM_BELIEVE = 0 ; //推断可信
    public static final Integer ASSUM_ADD = 1 ; //推断新增
    public static final Integer ASSUM_DOUBTED = 2 ;//推断可疑
    public static final Integer ASSUM_TEMP = 3 ;//推断临时
    public static final Integer ASSUM_VEHICLE_BELIEVE = 4 ;//推断整车可信
    public static final Integer ASSUM_SINGLE_BELIEVE = 5 ;//推断单件可信
    public static final Integer PROCESS_DOUBTED = 6 ;//过程可疑
    public static final Integer BROADCAST_DOUBTED = 7 ;//广播可疑
    public static final Integer UPGRADE_BELIEVE = 8 ;//升格可信
    public static final Integer UPGRADE_ADD = 9 ;//升格新增
    //能接受的巴枪状态
    protected static final Set<Integer> FVP_BARCODE_STATUS_SET = new HashSet<>(); 
    //0 1 4 为可接受巴枪状态
    static{
    	FVP_BARCODE_STATUS_SET.add(ASSUM_BELIEVE);
    	FVP_BARCODE_STATUS_SET.add(ASSUM_ADD);
    	FVP_BARCODE_STATUS_SET.add(ASSUM_VEHICLE_BELIEVE);
    }
    
    private FvpBarCodeStatusConstant() {
    }
    /**
     * 获取可通过的巴枪状态码集合
     * @return
     * @author 01369521
     * @date 2017年12月12日
     */
    public static Set<Integer> getNormalStateMap(){ 
        return Collections.unmodifiableSet(FvpBarCodeStatusConstant.FVP_BARCODE_STATUS_SET);//获取不可修改的set
    }
}

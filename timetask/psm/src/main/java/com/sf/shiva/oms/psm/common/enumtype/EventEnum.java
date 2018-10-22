package com.sf.shiva.oms.psm.common.enumtype;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * 描述： 事件枚举值
 *
 * <pre>HISTORY
 * ****************************************************************************
 *  ID   DATE           PERSON          REASON
 *  1    2017年11月29日      01159741         Create
 * ****************************************************************************
 * </pre>
 * @author 01159741
 * @since 1.0
 */
public enum EventEnum {
    
	EVENT00_0000("00_0000", "无效事件", false, false),
	EVENT10_1000("10_1000", "收件入仓", false, false),
	EVENT10_1001("10_1001", "装件入包", false, false),
	EVENT10_1002("10_1002", "一票一件", false, true),
	EVENT10_1003("10_1003", "一票多件", false, false),
	EVENT10_1004("10_1004", "国际小包收件", false, false),
	EVENT10_1005("10_1005", "装车", false, false),
	EVENT10_1006("10_1006", "清关完成", false, false),
	EVENT10_1007("10_1007", "滞留件入仓", false, false),
	EVENT10_1008("10_1008", "中转批量滞留", false, false),
	EVENT10_1009("10_1009", "中转滞留", false, false),
	EVENT10_1010("10_1010", "海关待查", false, false),
	EVENT10_1011("10_1011", "海关扣件", false, false),
	EVENT10_1012("10_1012", "理货异常", false, false),
	EVENT10_1013("10_1013", "暂存口岸待申报", false, false),
	EVENT10_1014("10_1014", "海关放行待补税", false, false),
	EVENT10_1015("10_1015", "批量清关延误", false, false),
	EVENT10_1016("10_1016", "检疫扣件", false, false),
	EVENT10_1017("10_1017", "运力抵达", false, false),
	EVENT10_1018("10_1018", "理货查验", false, false),
	EVENT10_1019("10_1019", "海关安检", false, false),
	EVENT10_1020("10_1020", "检疫查验", false, false),
	EVENT10_1021("10_1021", "检疫待查", false, false),
	EVENT10_1022("10_1022", "风险自查", false, false),
	EVENT10_1023("10_1023", "派件出仓/滞留件批量出仓", false, false),
	EVENT10_1024("10_1024", "滞留件出仓", false, false),
	EVENT10_1025("10_1025", "加时区域派件出仓", false, false),
	EVENT10_1026("10_1026", "顺丰店/便利店派件出仓", false, false),
	EVENT10_1027("10_1027", "落地配派件出仓", false, false),
	EVENT10_1028("10_1028", "二程接驳出仓", false, false),
	EVENT10_1030("10_1030", "快件卸车且派件出仓", false, false),
	EVENT10_1031("10_1031", "二程接驳派件", false, false),
	EVENT10_1032("10_1032", "港澳台二程接驳派件", false, false),
	EVENT10_1033("10_1033", "到转第三方快递", false, false),
	EVENT10_1034("10_1034", "寄转第三方快递", false, false),
	EVENT10_1035("10_1035", "快件交接", false, false),
	EVENT10_1036("10_1036", "代理交接-收件", false, false),
	EVENT10_1037("10_1037", "出仓确认", false, false),
	EVENT10_1038("10_1038", "正常派件", false, true),
	EVENT10_1039("10_1039", "新旧运单关联", true, false),
	EVENT10_1040("10_1040", "问题件", false, false),
	EVENT10_1041("10_1041", "海关查验", false, false),
	EVENT10_1042("10_1042", "正式报关待申报", false, false),
	EVENT10_1043("10_1043", "移交海关", false, false),
	EVENT10_1044("10_1044", "错分件", false, false),
	EVENT10_1045("10_1045", "快件已破损", false, false),
	EVENT10_1046("10_1046", "滞留件作废操作", false, false),
	EVENT10_1047("10_1047", "卸车非海关", false, false),
	EVENT10_1048("10_1048", "卸车海关", false, false),
	EVENT10_1049("10_1049", "准备派送不翻转", true, false),
	EVENT10_1050("10_1050", "快递员派件至丰巢", false, false),
	EVENT10_1051("10_1051", "快递员取消派件将快件取出丰巢", false, false),
	EVENT10_1052("10_1052", "换单操作", false, false),
	EVENT10_1053("10_1053", "美国进口CFS清关节点", false, false),
	EVENT10_1055("10_1055", "黄单扫描", false, false),
	EVENT10_1056("10_1056", "合作点已派件", false, false),
	EVENT10_1057("10_1057", "取消寄件", false, false),
	EVENT10_1058("10_1058", "上门收件", false, false),
	EVENT10_1059("10_1059", "滞留件入仓", false, true),
	EVENT10_1060("10_1060", "清单图片扫描", false, false),
	EVENT10_1061("10_1061", "子单揽收", false, false),
	EVENT10_1062("10_1062", "便利店交接", false, false),
    EVENT10_1063("10_1063", "客户接触点交接", false, false),
    EVENT10_1064("10_1064", "转寄", false, false),
    EVENT10_1065("10_1065", "退回", false, false),
    EVENT10_1066("10_1066", "第三方签收", false, false),
	EVENT20_1000("20_1000", "到方改派件时间", false, false),
	EVENT20_1001("20_1001", "无法联系到派件客户", false, false),
	EVENT20_1002("20_1002", "到方周末不在", false, false),
	EVENT20_1003("20_1003", "派送任务事件其他", true, false),
	EVENT30_1000("30_1000", "事件理赔结案", false, false);
    
    /**
     * 需要保存流水的事件编码
     */
    private static Set<String> needSaveRecordEventCodeSet = new HashSet<>();
    //不翻转状态，但是需要根据状态判断是否保存流水的事件编码
    private static Set<String> needSaveRecordWithStatusEvents = new HashSet<>();
    
    /**
     * 初始化时保存流水的时间编码
     */
    static{
        for (EventEnum eventEnum : EventEnum.values()) {
            if (eventEnum.saveRecordFlag) {
            	needSaveRecordEventCodeSet.add(eventEnum.getEventCode());
            }
            if(eventEnum.saveRecordSameStatus) {
                needSaveRecordWithStatusEvents.add(eventEnum.getEventCode());
            }
        }
    }
    
    /**
     * 事件编码
     */
    private String eventCode;
    /**
     * 事件描述
     */
    private String eventDesc;
    /**
     * 不翻转状态,保存流水记录
     */
    private boolean saveRecordFlag;
    /**
     * 不翻转状态，事件对应状态和包裹当前状态一致时，保存流水记录，只有当saveRecordFlag为false时,此配置才生效
     */
    private boolean saveRecordSameStatus;
    
    /**
     * 构造函数
     * 
     * @param eventCode
     *            事件代码
     * @param eventDesc
     *            事件描述
     * @param saveRecord
     *            不翻转状态,保存流水记录
     * @param saveRecordSameStatus
     *            不翻转状态，事件对应状态和包裹当前状态一致时，保存流水记录，只有当saveRecordFlag为false时,此配置才生效
     */
    EventEnum(String eventCode, String eventDesc, boolean saveRecord, boolean saveRecordSameStatus) {
        this.eventCode = eventCode;
        this.eventDesc = eventDesc;
        this.saveRecordFlag = saveRecord;
        this.saveRecordSameStatus = saveRecordSameStatus;
    }
    
    public String getEventCode() {
        return eventCode;
    }

    public String getEventDesc() {
        return eventDesc;
    }

	public boolean isSaveRecord() {
		return saveRecordFlag;
	}

	public static Set<String> getNeedSaveRecordEventCodeSet() {
		return needSaveRecordEventCodeSet;
	}

    public static Set<String> getNeedSaveRecordWithStatusEvents() {
        return needSaveRecordWithStatusEvents;
    }
    
	
}
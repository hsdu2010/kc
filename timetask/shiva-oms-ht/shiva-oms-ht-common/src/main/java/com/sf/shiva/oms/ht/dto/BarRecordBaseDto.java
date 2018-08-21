
package com.sf.shiva.oms.ht.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>barRecordBaseDto complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="barRecordBaseDto"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="accountantCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="autoloading" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="barOprCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="barRecordId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="barScanDt" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="barScanTm" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="barScanTmStd" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="barSn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="barUploadOprCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="barUploadTm" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="barUploadTmStd" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *         &lt;element name="barUploadTypeCode" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="batchCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="contnrCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="courierCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="destZoneCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="encryptString" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="extendAttach1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="extendAttach10" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="extendAttach11" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="extendAttach12" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="extendAttach13" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="extendAttach14" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="extendAttach15" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="extendAttach16" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="extendAttach17" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="extendAttach18" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="extendAttach19" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="extendAttach2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="extendAttach20" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="extendAttach21" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="extendAttach22" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="extendAttach23" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="extendAttach24" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="extendAttach25" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="extendAttach26" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="extendAttach27" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="extendAttach28" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="extendAttach29" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="extendAttach3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="extendAttach30" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="extendAttach31" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="extendAttach32" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="extendAttach33" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="extendAttach34" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="extendAttach35" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="extendAttach36" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="extendAttach37" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="extendAttach38" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="extendAttach39" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="extendAttach4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="extendAttach40" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="extendAttach41" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="extendAttach42" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="extendAttach43" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="extendAttach44" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="extendAttach45" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="extendAttach46" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="extendAttach47" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="extendAttach48" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="extendAttach49" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="extendAttach5" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="extendAttach50" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="extendAttach6" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="extendAttach7" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="extendAttach8" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="extendAttach9" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="feeAmt" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="objTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="opAttachInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="opCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="opName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="originalFlag" type="{http://www.w3.org/2001/XMLSchema}boolean"/&gt;
 *         &lt;element name="otherInfo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="payFlg" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="phone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="phoneZone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="scheduleCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="signTypeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="source" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="srcContnrCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="stayWhyCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="stopOverFlg" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="subbillPieceQty" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *         &lt;element name="waybillNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="weightQty" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/&gt;
 *         &lt;element name="zoneCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="zoneTypeCode" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "barRecordBaseDto", propOrder = {
    "accountantCode",
    "autoloading",
    "barOprCode",
    "barRecordId",
    "barScanDt",
    "barScanTm",
    "barScanTmStd",
    "barSn",
    "barUploadOprCode",
    "barUploadTm",
    "barUploadTmStd",
    "barUploadTypeCode",
    "batchCode",
    "contnrCode",
    "courierCode",
    "destZoneCode",
    "encryptString",
    "extendAttach1",
    "extendAttach10",
    "extendAttach11",
    "extendAttach12",
    "extendAttach13",
    "extendAttach14",
    "extendAttach15",
    "extendAttach16",
    "extendAttach17",
    "extendAttach18",
    "extendAttach19",
    "extendAttach2",
    "extendAttach20",
    "extendAttach21",
    "extendAttach22",
    "extendAttach23",
    "extendAttach24",
    "extendAttach25",
    "extendAttach26",
    "extendAttach27",
    "extendAttach28",
    "extendAttach29",
    "extendAttach3",
    "extendAttach30",
    "extendAttach31",
    "extendAttach32",
    "extendAttach33",
    "extendAttach34",
    "extendAttach35",
    "extendAttach36",
    "extendAttach37",
    "extendAttach38",
    "extendAttach39",
    "extendAttach4",
    "extendAttach40",
    "extendAttach41",
    "extendAttach42",
    "extendAttach43",
    "extendAttach44",
    "extendAttach45",
    "extendAttach46",
    "extendAttach47",
    "extendAttach48",
    "extendAttach49",
    "extendAttach5",
    "extendAttach50",
    "extendAttach6",
    "extendAttach7",
    "extendAttach8",
    "extendAttach9",
    "feeAmt",
    "objTypeCode",
    "opAttachInfo",
    "opCode",
    "opName",
    "originalFlag",
    "otherInfo",
    "payFlg",
    "phone",
    "phoneZone",
    "scheduleCode",
    "signTypeCode",
    "source",
    "srcContnrCode",
    "stayWhyCode",
    "stopOverFlg",
    "subbillPieceQty",
    "waybillNo",
    "weightQty",
    "zoneCode",
    "zoneTypeCode"
})
public class BarRecordBaseDto {

    protected String accountantCode;
    protected String autoloading;
    protected String barOprCode;
    protected String barRecordId;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar barScanDt;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar barScanTm;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar barScanTmStd;
    protected String barSn;
    protected String barUploadOprCode;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar barUploadTm;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar barUploadTmStd;
    protected Long barUploadTypeCode;
    protected String batchCode;
    protected String contnrCode;
    protected String courierCode;
    protected String destZoneCode;
    protected String encryptString;
    protected String extendAttach1;
    protected String extendAttach10;
    protected String extendAttach11;
    protected String extendAttach12;
    protected String extendAttach13;
    protected String extendAttach14;
    protected String extendAttach15;
    protected String extendAttach16;
    protected String extendAttach17;
    protected String extendAttach18;
    protected String extendAttach19;
    protected String extendAttach2;
    protected String extendAttach20;
    protected String extendAttach21;
    protected String extendAttach22;
    protected String extendAttach23;
    protected String extendAttach24;
    protected String extendAttach25;
    protected String extendAttach26;
    protected String extendAttach27;
    protected String extendAttach28;
    protected String extendAttach29;
    protected String extendAttach3;
    protected String extendAttach30;
    protected String extendAttach31;
    protected String extendAttach32;
    protected String extendAttach33;
    protected String extendAttach34;
    protected String extendAttach35;
    protected String extendAttach36;
    protected String extendAttach37;
    protected String extendAttach38;
    protected String extendAttach39;
    protected String extendAttach4;
    protected String extendAttach40;
    protected String extendAttach41;
    protected String extendAttach42;
    protected String extendAttach43;
    protected String extendAttach44;
    protected String extendAttach45;
    protected String extendAttach46;
    protected String extendAttach47;
    protected String extendAttach48;
    protected String extendAttach49;
    protected String extendAttach5;
    protected String extendAttach50;
    protected String extendAttach6;
    protected String extendAttach7;
    protected String extendAttach8;
    protected String extendAttach9;
    protected Double feeAmt;
    protected String objTypeCode;
    protected String opAttachInfo;
    protected String opCode;
    protected String opName;
    protected boolean originalFlag;
    protected String otherInfo;
    protected Long payFlg;
    protected String phone;
    protected String phoneZone;
    protected String scheduleCode;
    protected String signTypeCode;
    protected int source;
    protected String srcContnrCode;
    protected String stayWhyCode;
    protected Long stopOverFlg;
    protected Long subbillPieceQty;
    protected String waybillNo;
    protected Double weightQty;
    protected String zoneCode;
    protected Long zoneTypeCode;

    /**
     * 获取accountantCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountantCode() {
        return accountantCode;
    }

    /**
     * 设置accountantCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountantCode(String value) {
        this.accountantCode = value;
    }

    /**
     * 获取autoloading属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAutoloading() {
        return autoloading;
    }

    /**
     * 设置autoloading属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAutoloading(String value) {
        this.autoloading = value;
    }

    /**
     * 获取barOprCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBarOprCode() {
        return barOprCode;
    }

    /**
     * 设置barOprCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBarOprCode(String value) {
        this.barOprCode = value;
    }

    /**
     * 获取barRecordId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBarRecordId() {
        return barRecordId;
    }

    /**
     * 设置barRecordId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBarRecordId(String value) {
        this.barRecordId = value;
    }

    /**
     * 获取barScanDt属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBarScanDt() {
        return barScanDt;
    }

    /**
     * 设置barScanDt属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBarScanDt(XMLGregorianCalendar value) {
        this.barScanDt = value;
    }

    /**
     * 获取barScanTm属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBarScanTm() {
        return barScanTm;
    }

    /**
     * 设置barScanTm属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBarScanTm(XMLGregorianCalendar value) {
        this.barScanTm = value;
    }

    /**
     * 获取barScanTmStd属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBarScanTmStd() {
        return barScanTmStd;
    }

    /**
     * 设置barScanTmStd属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBarScanTmStd(XMLGregorianCalendar value) {
        this.barScanTmStd = value;
    }

    /**
     * 获取barSn属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBarSn() {
        return barSn;
    }

    /**
     * 设置barSn属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBarSn(String value) {
        this.barSn = value;
    }

    /**
     * 获取barUploadOprCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBarUploadOprCode() {
        return barUploadOprCode;
    }

    /**
     * 设置barUploadOprCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBarUploadOprCode(String value) {
        this.barUploadOprCode = value;
    }

    /**
     * 获取barUploadTm属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBarUploadTm() {
        return barUploadTm;
    }

    /**
     * 设置barUploadTm属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBarUploadTm(XMLGregorianCalendar value) {
        this.barUploadTm = value;
    }

    /**
     * 获取barUploadTmStd属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBarUploadTmStd() {
        return barUploadTmStd;
    }

    /**
     * 设置barUploadTmStd属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBarUploadTmStd(XMLGregorianCalendar value) {
        this.barUploadTmStd = value;
    }

    /**
     * 获取barUploadTypeCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getBarUploadTypeCode() {
        return barUploadTypeCode;
    }

    /**
     * 设置barUploadTypeCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setBarUploadTypeCode(Long value) {
        this.barUploadTypeCode = value;
    }

    /**
     * 获取batchCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBatchCode() {
        return batchCode;
    }

    /**
     * 设置batchCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBatchCode(String value) {
        this.batchCode = value;
    }

    /**
     * 获取contnrCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContnrCode() {
        return contnrCode;
    }

    /**
     * 设置contnrCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContnrCode(String value) {
        this.contnrCode = value;
    }

    /**
     * 获取courierCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCourierCode() {
        return courierCode;
    }

    /**
     * 设置courierCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCourierCode(String value) {
        this.courierCode = value;
    }

    /**
     * 获取destZoneCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDestZoneCode() {
        return destZoneCode;
    }

    /**
     * 设置destZoneCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDestZoneCode(String value) {
        this.destZoneCode = value;
    }

    /**
     * 获取encryptString属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEncryptString() {
        return encryptString;
    }

    /**
     * 设置encryptString属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEncryptString(String value) {
        this.encryptString = value;
    }

    /**
     * 获取extendAttach1属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtendAttach1() {
        return extendAttach1;
    }

    /**
     * 设置extendAttach1属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtendAttach1(String value) {
        this.extendAttach1 = value;
    }

    /**
     * 获取extendAttach10属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtendAttach10() {
        return extendAttach10;
    }

    /**
     * 设置extendAttach10属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtendAttach10(String value) {
        this.extendAttach10 = value;
    }

    /**
     * 获取extendAttach11属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtendAttach11() {
        return extendAttach11;
    }

    /**
     * 设置extendAttach11属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtendAttach11(String value) {
        this.extendAttach11 = value;
    }

    /**
     * 获取extendAttach12属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtendAttach12() {
        return extendAttach12;
    }

    /**
     * 设置extendAttach12属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtendAttach12(String value) {
        this.extendAttach12 = value;
    }

    /**
     * 获取extendAttach13属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtendAttach13() {
        return extendAttach13;
    }

    /**
     * 设置extendAttach13属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtendAttach13(String value) {
        this.extendAttach13 = value;
    }

    /**
     * 获取extendAttach14属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtendAttach14() {
        return extendAttach14;
    }

    /**
     * 设置extendAttach14属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtendAttach14(String value) {
        this.extendAttach14 = value;
    }

    /**
     * 获取extendAttach15属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtendAttach15() {
        return extendAttach15;
    }

    /**
     * 设置extendAttach15属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtendAttach15(String value) {
        this.extendAttach15 = value;
    }

    /**
     * 获取extendAttach16属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtendAttach16() {
        return extendAttach16;
    }

    /**
     * 设置extendAttach16属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtendAttach16(String value) {
        this.extendAttach16 = value;
    }

    /**
     * 获取extendAttach17属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtendAttach17() {
        return extendAttach17;
    }

    /**
     * 设置extendAttach17属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtendAttach17(String value) {
        this.extendAttach17 = value;
    }

    /**
     * 获取extendAttach18属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtendAttach18() {
        return extendAttach18;
    }

    /**
     * 设置extendAttach18属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtendAttach18(String value) {
        this.extendAttach18 = value;
    }

    /**
     * 获取extendAttach19属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtendAttach19() {
        return extendAttach19;
    }

    /**
     * 设置extendAttach19属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtendAttach19(String value) {
        this.extendAttach19 = value;
    }

    /**
     * 获取extendAttach2属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtendAttach2() {
        return extendAttach2;
    }

    /**
     * 设置extendAttach2属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtendAttach2(String value) {
        this.extendAttach2 = value;
    }

    /**
     * 获取extendAttach20属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtendAttach20() {
        return extendAttach20;
    }

    /**
     * 设置extendAttach20属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtendAttach20(String value) {
        this.extendAttach20 = value;
    }

    /**
     * 获取extendAttach21属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtendAttach21() {
        return extendAttach21;
    }

    /**
     * 设置extendAttach21属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtendAttach21(String value) {
        this.extendAttach21 = value;
    }

    /**
     * 获取extendAttach22属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtendAttach22() {
        return extendAttach22;
    }

    /**
     * 设置extendAttach22属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtendAttach22(String value) {
        this.extendAttach22 = value;
    }

    /**
     * 获取extendAttach23属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtendAttach23() {
        return extendAttach23;
    }

    /**
     * 设置extendAttach23属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtendAttach23(String value) {
        this.extendAttach23 = value;
    }

    /**
     * 获取extendAttach24属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtendAttach24() {
        return extendAttach24;
    }

    /**
     * 设置extendAttach24属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtendAttach24(String value) {
        this.extendAttach24 = value;
    }

    /**
     * 获取extendAttach25属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtendAttach25() {
        return extendAttach25;
    }

    /**
     * 设置extendAttach25属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtendAttach25(String value) {
        this.extendAttach25 = value;
    }

    /**
     * 获取extendAttach26属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtendAttach26() {
        return extendAttach26;
    }

    /**
     * 设置extendAttach26属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtendAttach26(String value) {
        this.extendAttach26 = value;
    }

    /**
     * 获取extendAttach27属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtendAttach27() {
        return extendAttach27;
    }

    /**
     * 设置extendAttach27属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtendAttach27(String value) {
        this.extendAttach27 = value;
    }

    /**
     * 获取extendAttach28属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtendAttach28() {
        return extendAttach28;
    }

    /**
     * 设置extendAttach28属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtendAttach28(String value) {
        this.extendAttach28 = value;
    }

    /**
     * 获取extendAttach29属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtendAttach29() {
        return extendAttach29;
    }

    /**
     * 设置extendAttach29属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtendAttach29(String value) {
        this.extendAttach29 = value;
    }

    /**
     * 获取extendAttach3属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtendAttach3() {
        return extendAttach3;
    }

    /**
     * 设置extendAttach3属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtendAttach3(String value) {
        this.extendAttach3 = value;
    }

    /**
     * 获取extendAttach30属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtendAttach30() {
        return extendAttach30;
    }

    /**
     * 设置extendAttach30属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtendAttach30(String value) {
        this.extendAttach30 = value;
    }

    /**
     * 获取extendAttach31属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtendAttach31() {
        return extendAttach31;
    }

    /**
     * 设置extendAttach31属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtendAttach31(String value) {
        this.extendAttach31 = value;
    }

    /**
     * 获取extendAttach32属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtendAttach32() {
        return extendAttach32;
    }

    /**
     * 设置extendAttach32属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtendAttach32(String value) {
        this.extendAttach32 = value;
    }

    /**
     * 获取extendAttach33属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtendAttach33() {
        return extendAttach33;
    }

    /**
     * 设置extendAttach33属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtendAttach33(String value) {
        this.extendAttach33 = value;
    }

    /**
     * 获取extendAttach34属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtendAttach34() {
        return extendAttach34;
    }

    /**
     * 设置extendAttach34属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtendAttach34(String value) {
        this.extendAttach34 = value;
    }

    /**
     * 获取extendAttach35属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtendAttach35() {
        return extendAttach35;
    }

    /**
     * 设置extendAttach35属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtendAttach35(String value) {
        this.extendAttach35 = value;
    }

    /**
     * 获取extendAttach36属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtendAttach36() {
        return extendAttach36;
    }

    /**
     * 设置extendAttach36属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtendAttach36(String value) {
        this.extendAttach36 = value;
    }

    /**
     * 获取extendAttach37属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtendAttach37() {
        return extendAttach37;
    }

    /**
     * 设置extendAttach37属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtendAttach37(String value) {
        this.extendAttach37 = value;
    }

    /**
     * 获取extendAttach38属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtendAttach38() {
        return extendAttach38;
    }

    /**
     * 设置extendAttach38属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtendAttach38(String value) {
        this.extendAttach38 = value;
    }

    /**
     * 获取extendAttach39属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtendAttach39() {
        return extendAttach39;
    }

    /**
     * 设置extendAttach39属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtendAttach39(String value) {
        this.extendAttach39 = value;
    }

    /**
     * 获取extendAttach4属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtendAttach4() {
        return extendAttach4;
    }

    /**
     * 设置extendAttach4属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtendAttach4(String value) {
        this.extendAttach4 = value;
    }

    /**
     * 获取extendAttach40属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtendAttach40() {
        return extendAttach40;
    }

    /**
     * 设置extendAttach40属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtendAttach40(String value) {
        this.extendAttach40 = value;
    }

    /**
     * 获取extendAttach41属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtendAttach41() {
        return extendAttach41;
    }

    /**
     * 设置extendAttach41属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtendAttach41(String value) {
        this.extendAttach41 = value;
    }

    /**
     * 获取extendAttach42属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtendAttach42() {
        return extendAttach42;
    }

    /**
     * 设置extendAttach42属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtendAttach42(String value) {
        this.extendAttach42 = value;
    }

    /**
     * 获取extendAttach43属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtendAttach43() {
        return extendAttach43;
    }

    /**
     * 设置extendAttach43属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtendAttach43(String value) {
        this.extendAttach43 = value;
    }

    /**
     * 获取extendAttach44属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtendAttach44() {
        return extendAttach44;
    }

    /**
     * 设置extendAttach44属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtendAttach44(String value) {
        this.extendAttach44 = value;
    }

    /**
     * 获取extendAttach45属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtendAttach45() {
        return extendAttach45;
    }

    /**
     * 设置extendAttach45属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtendAttach45(String value) {
        this.extendAttach45 = value;
    }

    /**
     * 获取extendAttach46属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtendAttach46() {
        return extendAttach46;
    }

    /**
     * 设置extendAttach46属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtendAttach46(String value) {
        this.extendAttach46 = value;
    }

    /**
     * 获取extendAttach47属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtendAttach47() {
        return extendAttach47;
    }

    /**
     * 设置extendAttach47属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtendAttach47(String value) {
        this.extendAttach47 = value;
    }

    /**
     * 获取extendAttach48属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtendAttach48() {
        return extendAttach48;
    }

    /**
     * 设置extendAttach48属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtendAttach48(String value) {
        this.extendAttach48 = value;
    }

    /**
     * 获取extendAttach49属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtendAttach49() {
        return extendAttach49;
    }

    /**
     * 设置extendAttach49属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtendAttach49(String value) {
        this.extendAttach49 = value;
    }

    /**
     * 获取extendAttach5属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtendAttach5() {
        return extendAttach5;
    }

    /**
     * 设置extendAttach5属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtendAttach5(String value) {
        this.extendAttach5 = value;
    }

    /**
     * 获取extendAttach50属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtendAttach50() {
        return extendAttach50;
    }

    /**
     * 设置extendAttach50属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtendAttach50(String value) {
        this.extendAttach50 = value;
    }

    /**
     * 获取extendAttach6属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtendAttach6() {
        return extendAttach6;
    }

    /**
     * 设置extendAttach6属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtendAttach6(String value) {
        this.extendAttach6 = value;
    }

    /**
     * 获取extendAttach7属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtendAttach7() {
        return extendAttach7;
    }

    /**
     * 设置extendAttach7属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtendAttach7(String value) {
        this.extendAttach7 = value;
    }

    /**
     * 获取extendAttach8属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtendAttach8() {
        return extendAttach8;
    }

    /**
     * 设置extendAttach8属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtendAttach8(String value) {
        this.extendAttach8 = value;
    }

    /**
     * 获取extendAttach9属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExtendAttach9() {
        return extendAttach9;
    }

    /**
     * 设置extendAttach9属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExtendAttach9(String value) {
        this.extendAttach9 = value;
    }

    /**
     * 获取feeAmt属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getFeeAmt() {
        return feeAmt;
    }

    /**
     * 设置feeAmt属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setFeeAmt(Double value) {
        this.feeAmt = value;
    }

    /**
     * 获取objTypeCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObjTypeCode() {
        return objTypeCode;
    }

    /**
     * 设置objTypeCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObjTypeCode(String value) {
        this.objTypeCode = value;
    }

    /**
     * 获取opAttachInfo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpAttachInfo() {
        return opAttachInfo;
    }

    /**
     * 设置opAttachInfo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpAttachInfo(String value) {
        this.opAttachInfo = value;
    }

    /**
     * 获取opCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpCode() {
        return opCode;
    }

    /**
     * 设置opCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpCode(String value) {
        this.opCode = value;
    }

    /**
     * 获取opName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpName() {
        return opName;
    }

    /**
     * 设置opName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpName(String value) {
        this.opName = value;
    }

    /**
     * 获取originalFlag属性的值。
     * 
     */
    public boolean isOriginalFlag() {
        return originalFlag;
    }

    /**
     * 设置originalFlag属性的值。
     * 
     */
    public void setOriginalFlag(boolean value) {
        this.originalFlag = value;
    }

    /**
     * 获取otherInfo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOtherInfo() {
        return otherInfo;
    }

    /**
     * 设置otherInfo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOtherInfo(String value) {
        this.otherInfo = value;
    }

    /**
     * 获取payFlg属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getPayFlg() {
        return payFlg;
    }

    /**
     * 设置payFlg属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setPayFlg(Long value) {
        this.payFlg = value;
    }

    /**
     * 获取phone属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置phone属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhone(String value) {
        this.phone = value;
    }

    /**
     * 获取phoneZone属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhoneZone() {
        return phoneZone;
    }

    /**
     * 设置phoneZone属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhoneZone(String value) {
        this.phoneZone = value;
    }

    /**
     * 获取scheduleCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getScheduleCode() {
        return scheduleCode;
    }

    /**
     * 设置scheduleCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setScheduleCode(String value) {
        this.scheduleCode = value;
    }

    /**
     * 获取signTypeCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSignTypeCode() {
        return signTypeCode;
    }

    /**
     * 设置signTypeCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSignTypeCode(String value) {
        this.signTypeCode = value;
    }

    /**
     * 获取source属性的值。
     * 
     */
    public int getSource() {
        return source;
    }

    /**
     * 设置source属性的值。
     * 
     */
    public void setSource(int value) {
        this.source = value;
    }

    /**
     * 获取srcContnrCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSrcContnrCode() {
        return srcContnrCode;
    }

    /**
     * 设置srcContnrCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSrcContnrCode(String value) {
        this.srcContnrCode = value;
    }

    /**
     * 获取stayWhyCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStayWhyCode() {
        return stayWhyCode;
    }

    /**
     * 设置stayWhyCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStayWhyCode(String value) {
        this.stayWhyCode = value;
    }

    /**
     * 获取stopOverFlg属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getStopOverFlg() {
        return stopOverFlg;
    }

    /**
     * 设置stopOverFlg属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setStopOverFlg(Long value) {
        this.stopOverFlg = value;
    }

    /**
     * 获取subbillPieceQty属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getSubbillPieceQty() {
        return subbillPieceQty;
    }

    /**
     * 设置subbillPieceQty属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setSubbillPieceQty(Long value) {
        this.subbillPieceQty = value;
    }

    /**
     * 获取waybillNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWaybillNo() {
        return waybillNo;
    }

    /**
     * 设置waybillNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWaybillNo(String value) {
        this.waybillNo = value;
    }

    /**
     * 获取weightQty属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getWeightQty() {
        return weightQty;
    }

    /**
     * 设置weightQty属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setWeightQty(Double value) {
        this.weightQty = value;
    }

    /**
     * 获取zoneCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getZoneCode() {
        return zoneCode;
    }

    /**
     * 设置zoneCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setZoneCode(String value) {
        this.zoneCode = value;
    }

    /**
     * 获取zoneTypeCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getZoneTypeCode() {
        return zoneTypeCode;
    }

    /**
     * 设置zoneTypeCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setZoneTypeCode(Long value) {
        this.zoneTypeCode = value;
    }

}

package com.sf.shiva.oms.ht.web.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;

public class StringToDateConverter implements Converter<String, Date> {

	private static final Logger logger = LoggerFactory.getLogger(StringToDateConverter.class);

	private String pattern;  
	public StringToDateConverter(){ 
		super();
    } 
	  
    public StringToDateConverter(String pattern){  
        this.pattern = pattern;  
    }  
  
    public Date convert(String s) {  
        Date date1 = null;
        Date date2 = null;
        String tempPattern = this.pattern;
        if(s != null && !"".equals(s.trim())){  
        	String dateStr = s.trim();
        	boolean isSlash = false;
    		boolean isLine = false;
    		if(dateStr.indexOf("/") != -1){
    			isSlash = true;
    		}
    		else if(dateStr.indexOf("-") != -1){
    			isLine = true;
    		}
        	if(isSlash){
    			tempPattern = "MM/dd/yyyy HH:mm:ss";
    		} else if(isLine){
    			tempPattern = "yyyy-MM-dd HH:mm:ss";
    		}
        	
        	try{ 
            	if(isSlash == false
            			&& isLine == false){
            		date1 = new Date(Long.valueOf(dateStr));
            	}
            }catch(Exception e){  
                logger.error("字符串转换日期异常,当前时间毫秒数字符串为:"+dateStr , e);  
            } 
     
        	if(tempPattern != null){
        		 try{ 
                 	SimpleDateFormat simpleDateFormat = new SimpleDateFormat(tempPattern);  
                     simpleDateFormat.setLenient(false);  
                     date2 = simpleDateFormat.parse(dateStr);  
                 }catch(ParseException e){  
                     logger.error("转换日期异常：pattern当前设置为:"+tempPattern+" ,日期字符串当前设置为:"+dateStr , e);  
                     throw new IllegalArgumentException("转换日期异常：pattern当前设置为:"+tempPattern+" ,日期字符串当前设置为:"+dateStr , e);  
                 }  
        	}  
        }  
        
        return date2 != null? date2 : date1;
    }  
}

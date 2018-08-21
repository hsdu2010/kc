package com.sf.shiva.oms.ht.service.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.json.ParseException;
@SuppressWarnings({"rawtypes", "unchecked"})
public class ParameterUtil {
    
    private static SimpleDateFormat DAY_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
    
    private ParameterUtil(){
        
    }
    
    public static String  argToString(String param){
        return param;
    }
    
    public static List  argToList(String param, Class cls) throws ParseException{
        return (List) JSON.parse(param, cls);
    }
    
    public static Enum  argToEnum(String param, Class cls){
        return Enum.valueOf(cls, param);
    }
    
    public static Date  argToDate(String value) throws java.text.ParseException{
        if (value.length() == 10) {
          return DATE_FORMAT.parse(value);
        }
        return DAY_FORMAT.parse(value);
    }
    
    public static Object argToPojo(String param, Class cls) throws ParseException{
        return JSON.parse(param, cls);
    }
}

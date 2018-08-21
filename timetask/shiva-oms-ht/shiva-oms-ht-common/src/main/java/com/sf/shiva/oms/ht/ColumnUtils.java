package com.sf.shiva.oms.ht;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class ColumnUtils {
	
	public static String generatorFK(){
		Random random  = new Random();
		Integer integer = random.nextInt(10000);
		if (integer<1000) {
			integer+=1000;
		} 
		String keyLastFour = String.valueOf(integer);
		String dateStr = String.valueOf(new Date().getTime());
		return dateStr+keyLastFour;
	}
	
	public static String nowDateString(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		java.util.Date date=new java.util.Date();  
		String str=sdf.format(date);  
		return str;
	}

}

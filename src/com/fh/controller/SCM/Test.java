package com.fh.controller.SCM;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

	public static void main(String[] args) {
		/*
		 * SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); String
		 * beginTime=new String("2017-06-09 10:22:22"); String endTime=new
		 * String("2017-05-08 11:22:22");
		 * 
		 * try { Date sd1=df.parse(beginTime); Date sd2 = df.parse(endTime);
		 * 
		 * System.out.println(sd1.getTime()); System.out.println(sd2.getTime()); long
		 * long1 =sd1.getTime(); long long2= sd2.getTime(); } catch (ParseException e) {
		 * // TODO Auto-generated catch block e.printStackTrace(); }
		 */

		//System.out.println(com.fh.util.MD5.md5("2").toUpperCase() );//238D8F95B82E950AE0DE1581F88AFE32
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		
		String ddString = formatter.format(date); 
	}

}

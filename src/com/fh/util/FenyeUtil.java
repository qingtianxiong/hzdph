package com.fh.util;

import java.util.HashMap;
import java.util.Map;

public class FenyeUtil {
	
	public static Map<String, Integer>  Fenye(String fnt,String jmys1,String yema,int count){
		Map<String, Integer> map = new HashMap<String, Integer>();
		
		int ys = 0;
		int jmys = 1;//ҳ����ʾ��ҳ��
		if (jmys1!=null) {
			jmys = Integer.parseInt(jmys1);
		}
		
		if("1".equals(fnt)) {
			if ("".equals(yema)) {yema = "1";}
			jmys = Integer.parseInt(yema);
			//���ҳ��
			int wy = count/15;
			if (count%15>0) {
				wy = wy+1;
			}
			
			if (jmys>wy) {
				jmys = wy;
			}
			if (jmys<=0) { jmys = 1; }
			ys = (jmys-1)*15;
		}else if("2".equals(fnt)) {
			ys = 0;
			jmys = 1;
		}else if("3".equals(fnt)) {
			jmys = jmys-1;
			if (jmys<=0) { jmys = 1; }
			ys = (jmys-1)*15;
		}else if("4".equals(fnt)) {
			//βҳ
			int wy = count/15;
			if (count%15>0) {
				wy = wy+1;
			}
			jmys = jmys+1;
			if (jmys>wy) {
				jmys = jmys-1;
			}
			ys = (jmys-1)*15;
		}else if("5".equals(fnt)) {
			//βҳ
			int wy = count/15;
			if (count%15>0) {
				wy = wy+1;
			}
			jmys = wy;
			ys = (wy-1)*15;
		}
		
		map.put("ys",ys);
		map.put("jmys",jmys);//ҳ��
		
		return map;
	}
}

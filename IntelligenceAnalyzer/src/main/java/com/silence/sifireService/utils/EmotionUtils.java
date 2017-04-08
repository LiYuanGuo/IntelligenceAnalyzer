/**
 * 
 */
package com.silence.sifireService.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * @title EmotionUtils.java
 * @author liyuanguo
 * @time 2017年3月30日 上午10:54:17
 * @description TODO
 * @version V1.0
 */
public class EmotionUtils {
	
	/**
	 * 
	 * @author liyuanguo
	 * @time 2017年3月31日 上午10:54:26
	 * @description 情感分析 主要使用python jieba包
	 * @param content
	 * @return
	 * @throws IOException
	 * @throws InterruptedException
	 */
	public static String getEmotion(String content) throws IOException, InterruptedException {
		try {
		   System.out.println("start");
		   //分别包含三个参数 python程序路径 |python情感分析程序|文本
		   String[] argss = new String[] {"python.exe",NormalUtils.getXmlPath()+"classes\\sentiment.py",content};
		   //String[] argss = new String[] {"C:\\Python27\\python.exe","D:\\sentiment\\sentiment.py",content};
		   Process pr = Runtime.getRuntime().exec(argss);
		   BufferedReader in = new BufferedReader(new InputStreamReader(pr.getInputStream()));
		   String line;
		   String result="";
		   while ((line = in.readLine()) != null) {
			result+=line;
		    System.out.println(line);
		   }
		   in.close();
		   pr.waitFor();
		   System.out.println("end");
		   return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";

	}
}

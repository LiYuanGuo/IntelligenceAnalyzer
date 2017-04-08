/**
 * 
 */
package com.silence.sifireService.utils;

/**
 * @title NormalUtils.java
 * @author liyuanguo
 * @time 2017年3月31日 上午10:09:42
 * @description TODO
 * @version V1.0
 */
public class NormalUtils {
	
	/**
	 * 
	 * @author liyuanguo
	 * @time 2017年3月31日 上午10:09:56
	 * @description 获取部署后webinfo路径
	 * @return
	 */
	public static String getXmlPath(){  
        //file:/D:/JavaWeb/.metadata/.me_tcat/webapps/TestBeanUtils/WEB-INF/classes/   
        String path=Thread.currentThread().getContextClassLoader().getResource("").toString();  
        path=path.replace('/', '\\'); // 将/换成\  
        path=path.replace("file:", ""); //去掉file:  
        path=path.replace("classes\\", ""); //去掉class\  
        path=path.substring(1); //去掉第一个\,如 \D:\JavaWeb...  
        //System.out.println(path);  
        return path;  
    }  
}

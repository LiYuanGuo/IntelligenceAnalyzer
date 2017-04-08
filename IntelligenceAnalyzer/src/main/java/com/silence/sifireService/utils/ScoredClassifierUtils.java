/**
 * 
 */
package com.silence.sifireService.utils;

import com.aliasi.classify.ScoredClassification;
import com.aliasi.classify.ScoredClassifier;
import com.silence.sifireService.pool.ClassifirePool;

/**
 * @title ScoredClassifierUtils.java
 * @author liyuanguo
 * @time 2017年3月31日 上午9:41:15
 * @description TODO
 * @version V1.0
 */
public class ScoredClassifierUtils {
	/**
	 * 
	 * @author liyuanguo
	 * @time 2017年3月31日 上午9:46:15
	 * @description 文本分类 使用 LingPipe(http://alias-i.com/lingpipe/)
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public static String getClass(String content) throws Exception{
		ClassifirePool pool=ClassifirePool.getInstance();
		String classStr="";
		ScoredClassifier<CharSequence> compiledClassifier=pool.fetchScoredClassifier(1000);
		if(null!=compiledClassifier){
			ScoredClassification classification = compiledClassifier.classify(content.subSequence(0, content.length()));
	        classStr=classification.bestCategory();
			System.out.println(classStr);
		}else{
		}
		return classStr; 
	}
}

/**
 * 
 */
package com.silence.sifireService.utils;

import java.io.StringReader;

import org.lionsoul.jcseg.extractor.SummaryExtractor;
import org.lionsoul.jcseg.extractor.impl.TextRankSummaryExtractor;
import org.lionsoul.jcseg.sentence.SentenceSeg;
import org.lionsoul.jcseg.tokenizer.core.ISegment;

import com.silence.sifireService.pool.SummaryPool;

/**
 * @title SummaryUtils.java
 * @author liyuanguo
 * @time 2017年3月30日 下午1:57:03
 * @description TODO
 * @version V1.0
 */
public class SummaryUtils {
	
	/**
	 * 
	 * @author liyuanguo
	 * @time 2017年3月31日 上午11:34:53
	 * @description 摘要提取 主要使用jcseg-core
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public static String getSummary(String content) throws Exception{
		SummaryPool pool=SummaryPool.getInstance();
		ISegment seg=pool.fetchISegment(1000);
		if(seg!=null){
			SummaryExtractor extractor = new TextRankSummaryExtractor(seg, new SentenceSeg());

			//3, 从一个Reader输入流中获取length长度的摘要
			String summary = extractor.getSummary(new StringReader(content), 64);
			return summary;
		}else{
			return "";
		}
		
	}

}

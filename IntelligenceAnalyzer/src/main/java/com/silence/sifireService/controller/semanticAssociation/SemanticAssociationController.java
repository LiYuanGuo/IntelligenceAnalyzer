/**
 * 
 */
package com.silence.sifireService.controller.semanticAssociation;

import java.util.Collection;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.silence.sifireService.pool.Word2VecPool;

/**
 * @title DeepLearningController.java
 * @author liyuanguo
 * @time 2017年3月22日 下午2:50:22
 * @description 语义联想
 * @version V1.0
 */
@Controller
@RequestMapping("/deepLearning")
public class SemanticAssociationController {
	
	/**
	 * 
	 * @author liyuanguo
	 * @time 2017年3月31日 上午10:28:35
	 * @description 语义联想
	 * @param word
	 * @param count
	 * @return
	 */
	@RequestMapping(value ="/getWordsList",produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public Collection<String> getWordsList(String word,int count){
		return Word2VecPool.getInstance().getWord2vec().wordsNearest(word, count);
	}
}

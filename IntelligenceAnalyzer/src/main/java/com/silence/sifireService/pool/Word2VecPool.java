/**
 * 
 */
package com.silence.sifireService.pool;

import org.deeplearning4j.models.word2vec.Word2Vec;

/**
 * @title Word2VecPool.java
 * @author liyuanguo
 * @time 2017年3月31日 上午10:33:20
 * @description 用deeplearning4j做语义联想
 * @version V1.0
 */

public class Word2VecPool {
	private Word2Vec word2vec;
	private static Word2VecPool instance=new Word2VecPool();
	
    public static Word2VecPool getInstance() {
        return instance;
    }
    
    public Word2Vec getWord2vec() {
		return word2vec;
	}

	public void setWord2vec(Word2Vec word2vec) {
		this.word2vec = word2vec;
	}
}

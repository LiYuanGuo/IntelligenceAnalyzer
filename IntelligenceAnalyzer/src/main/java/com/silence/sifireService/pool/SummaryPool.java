/**
 * 
 */
package com.silence.sifireService.pool;

import java.io.IOException;
import java.util.LinkedList;

import org.apache.solr.client.solrj.SolrClient;
import org.lionsoul.jcseg.tokenizer.core.ADictionary;
import org.lionsoul.jcseg.tokenizer.core.DictionaryFactory;
import org.lionsoul.jcseg.tokenizer.core.ISegment;
import org.lionsoul.jcseg.tokenizer.core.JcsegTaskConfig;
import org.lionsoul.jcseg.tokenizer.core.SegmentFactory;

import com.google.common.collect.Lists;
import com.silence.sifireService.global.Global;

/**
 * @title SummaryPool.java
 * @author liyuanguo
 * @time 2017年3月30日 上午11:51:57
 * @description TODO
 * @version V1.0
 */
public class SummaryPool {
	
	private static SummaryPool  instance= new SummaryPool();;
	 protected  LinkedList<ISegment> pool = Lists.newLinkedList();
	    private  SummaryPool(){
	    	JcsegTaskConfig config = new JcsegTaskConfig(true);
			config.setClearStopwords(true);     //设置过滤停止词
			config.setAppendCJKSyn(false);      //设置关闭同义词追加
			config.setKeepUnregWords(false);    //设置去除不识别的词条
			ADictionary dic = DictionaryFactory.createSingletonDictionary(config);
	       for(int i=0;i<Global.ISEGMENT_NUM;i++){
			try {
				ISegment seg = SegmentFactory.createJcseg(
					    JcsegTaskConfig.COMPLEX_MODE, 
					    new Object[]{config, dic}
					);
				if(null!=seg){
					System.out.println("初始化了："+(i+1)+"个ISegment");
					pool.add(seg);
				}
			} catch (Exception e) {
				continue;
			}
	       }
	    }
	    public  static SummaryPool getInstance(){
	        return  instance;
	    }
	    public  void releaseISegment(ISegment iSegment) {
	        if (iSegment != null) {
	            synchronized (pool) {
	                pool.addLast(iSegment);
	                pool.notifyAll();
	            }
	        }
	    }
	    public ISegment fetchISegment(long mills) throws InterruptedException {
	        synchronized (pool){
	            if(mills<=0){
	                while (pool.isEmpty()){
	                    pool.wait();
	                }
	                return pool.removeFirst();
	            }else {
	                long future= System.currentTimeMillis()+mills;
	                long remianing=mills;
	                while (pool.isEmpty() && remianing >0){
	                    pool.wait(remianing);
	                    remianing=future-System.currentTimeMillis();
	                }
	                ISegment iSegment =null;
	                if(!pool.isEmpty()){
	                	iSegment=pool.removeFirst();
	                }
	                return iSegment;
	            }
	        }
	    }
	    
	   
	    public void empty(){
	        pool=Lists.newLinkedList();
	    }	

}

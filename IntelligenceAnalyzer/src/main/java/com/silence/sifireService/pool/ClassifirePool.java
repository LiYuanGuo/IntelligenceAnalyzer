/**
 * 
 */
package com.silence.sifireService.pool;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.LinkedList;

import com.aliasi.classify.ScoredClassifier;
import com.google.common.collect.Lists;
import com.silence.sifireService.global.Global;
import com.silence.sifireService.utils.NormalUtils;

/**
 * @title ClassifirePool.java
 * @author liyuanguo
 * @time 2017年3月31日 上午9:32:04
 * @description TODO
 * @version V1.0
 */
public class ClassifirePool {
	private static ClassifirePool  instance= new ClassifirePool();;
	protected  LinkedList<ScoredClassifier<CharSequence>> pool = Lists.newLinkedList();
    private  ClassifirePool(){
       String webInfoPath=NormalUtils.getXmlPath();
       
       String modelFile = webInfoPath+"classes\\Classifier";
       for(int i=0;i<Global.SCOREDCLASSIFIER_NUM;i++){
		try {
			ScoredClassifier<CharSequence> compiledClassifier = null;
	            ObjectInputStream oi = new ObjectInputStream(new FileInputStream(modelFile));
	            compiledClassifier = (ScoredClassifier<CharSequence>)oi.readObject();
	            oi.close();
			if(null!=compiledClassifier){
				System.out.println("初始化了："+(i+1)+"个compiledClassifier");
				pool.add(compiledClassifier);
			}
		} catch (Exception e) {
			continue;
		}
       }
    }
    public  static ClassifirePool getInstance(){
        return  instance;
    }
    public  void releaseScoredClassifier(ScoredClassifier<CharSequence> scoredClassifier) {
        if (scoredClassifier != null) {
            synchronized (pool) {
                pool.addLast(scoredClassifier);
                pool.notifyAll();
            }
        }
    }
    public ScoredClassifier<CharSequence> fetchScoredClassifier(long mills) throws InterruptedException {
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
                ScoredClassifier<CharSequence> scoredClassifier =null;
                if(!pool.isEmpty()){
                	scoredClassifier=pool.removeFirst();
                }
                return scoredClassifier;
            }
        }
    }
    
   
    public void empty(){
        pool=Lists.newLinkedList();
    }	
    
    

}

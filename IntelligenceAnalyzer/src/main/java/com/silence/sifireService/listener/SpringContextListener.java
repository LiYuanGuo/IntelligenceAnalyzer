package com.silence.sifireService.listener;

import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.Word2Vec;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Controller;

import com.silence.sifireService.pool.ClassifirePool;
import com.silence.sifireService.pool.EntityChargePool;
import com.silence.sifireService.pool.SummaryPool;
import com.silence.sifireService.pool.Word2VecPool;
import com.silence.sifireService.utils.NormalUtils;


@Controller
public class SpringContextListener implements ApplicationListener<ContextRefreshedEvent> {
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		
		  if(event.getApplicationContext().getDisplayName().equals("Root WebApplicationContext")){
			  
			 //预加载模型 加载模型慢
			 String path2=NormalUtils.getXmlPath()+"classes\\word2vec_module";
			 if(Word2VecPool.getInstance().getWord2vec()==null){
				 Word2Vec word2vec=WordVectorSerializer.readWord2VecModel(path2);
				 Word2VecPool.getInstance().setWord2vec(word2vec);
			 }
			  //预初始化指定数目摘要提取 ISegment(分割器)
			  SummaryPool.getInstance();
			  //预初始化指定数目实体识别 ISegment(分割器)
			  EntityChargePool.getInstance();
			  //预初始化指定数目文本分类器 
			  ClassifirePool.getInstance();
			  
		  }
		
	}
}

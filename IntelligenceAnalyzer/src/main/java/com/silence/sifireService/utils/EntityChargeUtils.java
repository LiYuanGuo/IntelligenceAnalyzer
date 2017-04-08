/**
 * 
 */
package com.silence.sifireService.utils;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.lionsoul.jcseg.tokenizer.core.ISegment;
import org.lionsoul.jcseg.tokenizer.core.IWord;

import com.silence.sifireService.pool.EntityChargePool;

/**
 * @title EntityChargeUtils.java
 * @author liyuanguo
 * @time 2017年3月30日 下午2:31:29
 * @description TODO
 * @version V1.0
 */
public class EntityChargeUtils {

	/**
	 * 
	 * @author liyuanguo
	 * @time 2017年3月31日 上午11:23:28
	 * @description 实体识别 主要使用jcseg-core
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public static List<Map<String,Object>> getEntity(String content) throws Exception{
		List<Map<String,Object>> dataList=new ArrayList<>();
		EntityChargePool pool=EntityChargePool.getInstance();
		ISegment seg=pool.fetchISegment(1000);
		HashSet<String> reSet = new HashSet<String>();
		if(seg!=null){
			
			//设置要被分词的文本
			seg.reset(new StringReader(content));

			//获取分词结果
			IWord word = null;
			while ( (word = seg.next()) != null ) {
				//实体词
				String entity=word.getValue();
				if(null!=entity&&entity.length()>1){
					//实体属性
					String[] propertys=word.getPartSpeech();
					if(null!=propertys&&propertys.length>0){
						String property=propertys[0];
						Map<String,Object> map=new HashMap<>();
						map.put("property", property);
						map.put("word", entity);
						switch(property){
							case "gc":
								
								if(!reSet.contains(property+entity)){
									map.put("descrption", "化学相关词汇");
									dataList.add(map);
									reSet.add(property+entity);
								}
								break;
							case "nh":
								if(!reSet.contains(property+entity)){
									map.put("descrption", "医药疾病等健康相关名词");
									dataList.add(map);
									reSet.add(property+entity);
								}
								break;
							case "ni":
								if(!reSet.contains(property+entity)){
									map.put("descrption", "机构相关");
									dataList.add(map);
									reSet.add(property+entity);
								}
								break;
							case "nic":
								if(!reSet.contains(property+entity)){
									map.put("descrption", "下属机构");
									dataList.add(map);
									reSet.add(property+entity);
								}
								
								break;
							case "nit":
								if(!reSet.contains(property+entity)){
									map.put("descrption", "教育相关机构");
									dataList.add(map);
									reSet.add(property+entity);
								}
								break;
							case "nmc":
								if(!reSet.contains(property+entity)){
									map.put("descrption", "化学品名");
									dataList.add(map);
									reSet.add(property+entity);
								}
								break;
							case "nr":
								if(!reSet.contains(property+entity)){
									map.put("descrption", "人名");
									dataList.add(map);
									reSet.add(property+entity);
								}
								break;
							case "ns":
								if(!reSet.contains(property+entity)){
									map.put("descrption", "地名");
									dataList.add(map);
									reSet.add(property+entity);
								}
								break;
							case "nsf":
								if(!reSet.contains(property+entity)){
									map.put("descrption", "音译地名");
									dataList.add(map);
									reSet.add(property+entity);
								}
								break;
							case "ntcf":
								if(!reSet.contains(property+entity)){
									map.put("descrption", "工厂");
									dataList.add(map);
									reSet.add(property+entity);
								}
								break;
							case "nth":
								if(!reSet.contains(property+entity)){
									map.put("descrption", "医院");
									dataList.add(map);
									reSet.add(property+entity);
								}
								break;
							case "nto":
								if(!reSet.contains(property+entity)){
									map.put("descrption", "政府机构");
									dataList.add(map);
									reSet.add(property+entity);
								}
								break;
							case "s":
								if(!reSet.contains(property+entity)){
									map.put("descrption", "处所词");
									dataList.add(map);
									reSet.add(property+entity);
								}
								break;
							case "t":
								if(!reSet.contains(property+entity)){
									if(word.getEntity()!=null){
										map.put("descrption", "时间");
										dataList.add(map);
									}
									reSet.add(property+entity);
								}
								break;
							case "nts":
								if(!reSet.contains(property+entity)){
									map.put("descrption", "中小学");
									dataList.add(map);
									reSet.add(property+entity);
								}
								break;
							case "ntu":
								if(!reSet.contains(property+entity)){
									map.put("descrption", "大学");
									dataList.add(map);
									reSet.add(property+entity);
								}
								break;
							case "ntch":
								if(!reSet.contains(property+entity)){
									map.put("descrption", "酒店宾馆");
									dataList.add(map);
									reSet.add(property+entity);
								}
								break;
							case "ntcb":
								if(!reSet.contains(property+entity)){
									map.put("descrption", "银行");
									dataList.add(map);
									reSet.add(property+entity);
								}
								break;
							case "ntc":
								if(!reSet.contains(property+entity)){
									map.put("descrption", "公司名");
									dataList.add(map);
									reSet.add(property+entity);
								}
								break;
							case "nt":
								if(!reSet.contains(property+entity)){
									map.put("descrption", "机构团体名");
									dataList.add(map);
									reSet.add(property+entity);
								}
								break;
							case "nrj":
								if(!reSet.contains(property+entity)){
									map.put("descrption", "日语人名");
									dataList.add(map);
									reSet.add(property+entity);
								}
								break;
							case "nrf":
								if(!reSet.contains(property+entity)){
									map.put("descrption", "音译人名");
									dataList.add(map);
									reSet.add(property+entity);
								}
								break;
							case "nnd":
								if(!reSet.contains(property+entity)){
									map.put("descrption", " 职业");
									dataList.add(map);
									reSet.add(property+entity);
								}
								break;
							case "nm":
								if(!reSet.contains(property+entity)){
									map.put("descrption", "物品名");
									dataList.add(map);
									reSet.add(property+entity);
								}
								break;
								
						}
					}
				}
				
			    
			}
			
		}else{
			
		}
		return dataList;
	}
	
	/**
	 * 
	 * @author liyuanguo
	 * @time 2017年3月30日 下午4:29:50
	 * @description 返回标注文本
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public static String getEntityString(String content) throws Exception{
		HashSet<String> reSet = new HashSet<String>();
		EntityChargePool pool=EntityChargePool.getInstance();
		ISegment seg=pool.fetchISegment(1000);
		if(seg!=null){
			
			//设置要被分词的文本
			seg.reset(new StringReader(content));

			//获取分词结果
			IWord word = null;
			while ( (word = seg.next()) != null ) {
				String entity=word.getValue();
				if(null!=entity&&entity.length()>1){
					String[] propertys=word.getPartSpeech();
					if(null!=propertys&&propertys.length>0){
						String property=propertys[0];
						Map<String,Object> map=new HashMap<>();
						map.put("property", property);
						map.put("word", entity);
						String re=property.toUpperCase();
						switch(property){
							case "gc":
								if(!reSet.contains(entity)){
									content=content.replaceAll(entity, "<"+re+">"+entity+"</"+re+">");
									reSet.add(entity);
								}
								break;
							case "nh":
								if(!reSet.contains(entity)){
									content=content.replaceAll(entity, "<"+re+">"+entity+"</"+re+">");
									reSet.add(entity);
								}
								break;
							case "ni":
								if(!reSet.contains(entity)){
									content=content.replaceAll(entity, "<"+re+">"+entity+"</"+re+">");
									reSet.add(entity);
								}
								break;
							case "nic":
								if(!reSet.contains(entity)){
									content=content.replaceAll(entity, "<"+re+">"+entity+"</"+re+">");
									reSet.add(entity);
								}
								break;
							case "nit":
								if(!reSet.contains(entity)){
									content=content.replaceAll(entity, "<"+re+">"+entity+"</"+re+">");
									reSet.add(entity);
								}
								break;
							case "nmc":
								if(!reSet.contains(entity)){
									content=content.replaceAll(entity, "<"+re+">"+entity+"</"+re+">");
									reSet.add(entity);
								}
								break;
							case "nr":
								if(!reSet.contains(entity)){
									content=content.replaceAll(entity, "<"+re+">"+entity+"</"+re+">");
									reSet.add(entity);
								}
								break;
							case "ns":
								if(!reSet.contains(entity)){
									content=content.replaceAll(entity, "<"+re+">"+entity+"</"+re+">");
									reSet.add(entity);
								}
								break;
							case "nsf":
								if(!reSet.contains(entity)){
									content=content.replaceAll(entity, "<"+re+">"+entity+"</"+re+">");
									reSet.add(entity);
								}
								break;
							case "ntcf":
								if(!reSet.contains(entity)){
									content=content.replaceAll(entity, "<"+re+">"+entity+"</"+re+">");
									reSet.add(entity);
								}
								break;
							case "nth":
								if(!reSet.contains(entity)){
									content=content.replaceAll(entity, "<"+re+">"+entity+"</"+re+">");
									reSet.add(entity);
								}
								break;
							case "nto":
								if(!reSet.contains(entity)){
									content=content.replaceAll(entity, "<"+re+">"+entity+"</"+re+">");
									reSet.add(entity);
								}
								break;
							case "s":
								if(!reSet.contains(entity)){
									content=content.replaceAll(entity, "<"+re+">"+entity+"</"+re+">");
									reSet.add(entity);
								}
								break;
							case "t":
								if(!reSet.contains(entity)){
									content=content.replaceAll(entity, "<"+re+">"+entity+"</"+re+">");
									reSet.add(entity);
								}
								break;
							case "nts":
								if(!reSet.contains(entity)){
									content=content.replaceAll(entity, "<"+re+">"+entity+"</"+re+">");
									reSet.add(entity);
								}
								break;
							case "ntu":
								if(!reSet.contains(entity)){
									content=content.replaceAll(entity, "<"+re+">"+entity+"</"+re+">");
									reSet.add(entity);
								}
								break;
							case "ntch":
								if(!reSet.contains(entity)){
									content=content.replaceAll(entity, "<"+re+">"+entity+"</"+re+">");
									reSet.add(entity);
								}
								break;
							case "ntcb":
								if(!reSet.contains(entity)){
									content=content.replaceAll(entity, "<"+re+">"+entity+"</"+re+">");
									reSet.add(entity);
								}
								break;
							case "ntc":
								if(!reSet.contains(entity)){
									content=content.replaceAll(entity, "<"+re+">"+entity+"</"+re+">");
									reSet.add(entity);
								}
								break;
							case "nt":
								if(!reSet.contains(entity)){
									content=content.replaceAll(entity, "<"+re+">"+entity+"</"+re+">");
									reSet.add(entity);
								}
								break;
							case "nrj":
								if(!reSet.contains(entity)){
									content=content.replaceAll(entity, "<"+re+">"+entity+"</"+re+">");
									reSet.add(entity);
								}
								break;
							case "nrf":
								if(!reSet.contains(entity)){
									content=content.replaceAll(entity, "<"+re+">"+entity+"</"+re+">");
									reSet.add(entity);
								}
								break;
							case "nnd":
								if(!reSet.contains(entity)){
									content=content.replaceAll(entity, "<"+re+">"+entity+"</"+re+">");
									reSet.add(entity);
								}
								break;
							case "nm":
								if(!reSet.contains(entity)){
									content=content.replaceAll(entity, "<"+re+">"+entity+"</"+re+">");
									reSet.add(entity);
								}
								break;
								
						}
					}
				}
			    
			}
			
		}else{
			
		}
		return content;
	}
	

}

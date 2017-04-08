/**
 * 
 */
package com.silence.sifireService.controller.entityCharge;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.silence.sifireService.utils.EntityChargeUtils;
import com.silence.sifireService.utils.SummaryUtils;

/**
 * @title EntityCharge.java
 * @author liyuanguo
 * @time 2017年3月30日 下午3:11:33
 * @description TODO
 * @version V1.0
 */
@Controller
@RequestMapping("/entityCharge")
public class EntityChargeController {
	/**
	 * 
	 * @author liyuanguo
	 * @time 2017年3月30日 下午2:11:09
	 * @description 实体识别
	 * @param content
	 * @return
	 */
	@RequestMapping(value ="/getEntityList",produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public Map<String,Object> getEntityList(String content){
		Map<String,Object> map=new HashMap<String,Object>();
		try {
			List<Map<String,Object>> data=EntityChargeUtils.getEntity(content);
			map.put("data",data);
			map.put("message", "提取成功");
			map.put("status","success");
		} catch (Exception e) {
			map.put("message", "提取出现异常");
			map.put("status","failure");
			map.put("data", "");
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 * 
	 * @author liyuanguo
	 * @time 2017年3月30日 下午4:31:35
	 * @description 实体标注
	 * @param content
	 * @return
	 */
	@RequestMapping(value ="/getEntity",produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public Map<String,Object> getEntity(String content){
		Map<String,Object> map=new HashMap<String,Object>();
		try {
			String data=EntityChargeUtils.getEntityString(content);
			map.put("data",data);
			map.put("message", "提取成功");
			map.put("status","success");
		} catch (Exception e) {
			map.put("message", "提取出现异常");
			map.put("status","failure");
			map.put("data", "");
			e.printStackTrace();
		}
		return map;
	}
	
}

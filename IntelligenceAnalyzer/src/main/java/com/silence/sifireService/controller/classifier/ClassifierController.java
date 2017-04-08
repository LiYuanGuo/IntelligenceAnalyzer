/**
 * 
 */
package com.silence.sifireService.controller.classifier;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.silence.sifireService.utils.EntityChargeUtils;
import com.silence.sifireService.utils.ScoredClassifierUtils;

/**
 * @title ClassifierController.java
 * @author liyuanguo
 * @time 2017年3月31日 上午9:47:21
 * @description 
 * @version V1.0
 */
@Controller
@RequestMapping("/classifier")
public class ClassifierController {

	/**
	 * 
	 * @author liyuanguo
	 * @time 2017年3月31日 上午10:51:03
	 * @description 文本分类
	 * @param content
	 * @return
	 */
	@RequestMapping(value ="/getClass",produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public Map<String,Object> getClass(String content){
		Map<String,Object> map=new HashMap<String,Object>();
		try {
			String data=ScoredClassifierUtils.getClass(content);
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

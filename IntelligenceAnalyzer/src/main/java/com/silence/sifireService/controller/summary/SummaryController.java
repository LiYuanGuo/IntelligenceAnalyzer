/**
 * 
 */
package com.silence.sifireService.controller.summary;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.silence.sifireService.utils.EmotionUtils;
import com.silence.sifireService.utils.SummaryUtils;

/**
 * @title SummaryController.java
 * @author liyuanguo
 * @time 2017年3月30日 下午2:09:19
 * @description TODO
 * @version V1.0
 */
@Controller
@RequestMapping("/summary")
public class SummaryController {
	
	
	/**
	 * 
	 * @author liyuanguo
	 * @time 2017年3月30日 下午2:11:09
	 * @description 摘要提取
	 * @param content
	 * @return
	 */
	@RequestMapping(value ="/getSummary",produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public Map<String,Object> getSummary(String content){
		Map<String,Object> map=new HashMap<String,Object>();
		try {
			String data=SummaryUtils.getSummary(content);
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

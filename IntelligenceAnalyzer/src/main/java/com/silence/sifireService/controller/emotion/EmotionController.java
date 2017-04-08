/**
 * 
 */
package com.silence.sifireService.controller.emotion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.silence.sifireService.utils.EmotionUtils;

/**
 * @author liyuuanguo
 *
 */
@Controller
@RequestMapping("/emotion")
public class EmotionController {
	
	/**
	 * 
	 * @author liyuanguo
	 * @time 2017年3月30日 下午2:11:43
	 * @description 情感分析
	 * @param content
	 * @return
	 */
	@RequestMapping(value ="/getEmotion",produces = {"application/json;charset=UTF-8"})
	@ResponseBody
	public Map<String,Object> getAllSite(String content){
		Map<String,Object> map=new HashMap<String,Object>();
		try {
			String data=EmotionUtils.getEmotion(content);
			map.put("data",data);
			map.put("message", "分析成功");
			map.put("status","success");
		} catch (Exception e) {
			map.put("message", "分析出现异常");
			map.put("status","failure");
			map.put("data", "");
			e.printStackTrace();
		}
		return map;
	}
}

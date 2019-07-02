package cn.org.aischool.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import cn.org.aischool.cfg.ConstantCfg;
import cn.org.aischool.entity.RobotResVo;
import cn.org.aischool.service.AnalysisService;

/**
 * 
 * @author huangliyang
 * @Description 语义分析controller，与UNIT对话的controller
 * @Version
 * @Date 2019年2月10日
 */
@Controller
public class AnalysisController {
	
	//注入服务类
	@Autowired
	private AnalysisService analysisService;

	@RequestMapping("/getRobotRes")
	@ResponseBody
	public String getRobotReq(String voiceText, String key) {
//		AnalysisServiceImpl analysisService = new AnalysisServiceImpl();
		
		if(key.equals(ConstantCfg.KEY)) {
			RobotResVo robotResVo = analysisService.getRobotRes(voiceText);
			return JSONObject.toJSON(robotResVo).toString();
		}else{
			String result ="{ result：失败，code：400，message：你这么皮怎么不跟爸爸我姓 }";
			return JSONObject.toJSON(result).toString();
		}
	}
	
	
	public static void main(String[] args) {
		
		AnalysisController ac = new AnalysisController();
		String robotReq = ac.getRobotReq("唱一首歌", "971026");
		System.out.println(robotReq);
	}
}













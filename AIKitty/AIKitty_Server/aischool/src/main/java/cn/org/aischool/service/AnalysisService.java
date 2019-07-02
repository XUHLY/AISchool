package cn.org.aischool.service;

import org.springframework.stereotype.Service;

import cn.org.aischool.entity.RobotResVo;
/**
 * 
 * @author huangliyang
 * @Description 接收前端传入的文字进行unit处理分析的服务接口
 * @Version 
 * @Date 2019年2月26日
 */
@Service
public interface AnalysisService {

	
	/**
	 * @Description 跟Unit对话  获得返回对象的方法
	 * @param voiceText 识别出的语音文本
	 * @return
	 */
	public RobotResVo getRobotRes(String query);
}

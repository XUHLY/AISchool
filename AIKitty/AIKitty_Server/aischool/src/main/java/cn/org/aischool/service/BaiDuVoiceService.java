package cn.org.aischool.service;

import org.springframework.stereotype.Service;
/**
 * 
 * @author huangliyang
 * @Description 百度语音服务接口
 * @Version 
 * @Date 2019年2月26日
 */
@Service
public interface BaiDuVoiceService {

	/**
	 * @Description 语音识别
	 * @param audioPath 语音文件所在路径
	 * @param tempFileName 临时文件名
	 * @return
	 * @throws Exception 
	 */
	public String  voiceRecognition(String audioPath,String tempFileName) throws Exception;

	/**
	 * @Description 语音合成的方法
	 * @param voiceText 要合成分文本
	 * @param per 设置发音人  0为女声，1为男声，3为情感合成-度逍遥，4为情感合成-度丫丫，默认为普通女
	 * @return
	 */
	public String  voiceSynthesis(String Text,String per);
	
}

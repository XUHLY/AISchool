package cn.org.aischool.service;

import org.springframework.stereotype.Service;

import cn.org.aischool.entity.VoiceTextVo;

/**
 * 
 * @author huangliyang
 * @Description 语音识别返回文本的服务接口
 * @Version 
 * @Date 2019年2月26日
 */
@Service
public interface VoiceService {

	public VoiceTextVo getVoiceText (String audioPath, String tempFileName) throws Exception;
}

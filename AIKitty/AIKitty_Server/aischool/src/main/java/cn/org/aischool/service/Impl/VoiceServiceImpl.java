package cn.org.aischool.service.Impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import cn.org.aischool.cfg.ConstantCfg;
import cn.org.aischool.entity.VoiceTextVo;
import cn.org.aischool.service.BaiDuVoiceService;
import cn.org.aischool.service.VoiceService;
/**
 * 
 * @author huangliyang
 * @Description 语音识别返回文本的服务类
 * @Version 
 * @Date 2019年2月26日
 */
@Service
public class VoiceServiceImpl implements VoiceService {

	@Autowired
	private BaiDuVoiceService baiDuVoiceService;

	@Override
	public VoiceTextVo getVoiceText(String audioPath,String tempFileName) throws Exception {
		//识别返回的文本
		String result = baiDuVoiceService.voiceRecognition(audioPath, tempFileName);
		//设置语音识别返回的vo
		VoiceTextVo vtVo = new VoiceTextVo();
		vtVo.setRole(ConstantCfg.CHATOBJECT_USER);
		vtVo.setChatInfo(result);
		return vtVo;
	}

}

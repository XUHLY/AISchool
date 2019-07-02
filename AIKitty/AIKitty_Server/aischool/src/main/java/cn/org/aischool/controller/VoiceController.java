package cn.org.aischool.controller;

import java.io.File;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSONObject;

import cn.org.aischool.cfg.AppCfg;
import cn.org.aischool.cfg.ConstantCfg;
import cn.org.aischool.entity.VoiceTextVo;
import cn.org.aischool.service.VoiceService;

@Controller
public class VoiceController {

	// 注入全局变量配置类
	@Autowired
	private AppCfg app;

	// 注入voiceService
	@Autowired
	private VoiceService vS;

	// 日志
	private static final Logger logger = LoggerFactory.getLogger(VoiceController.class);

	// 上传语音获取识别文字
	@RequestMapping("/getVoiceText")
	@ResponseBody
	public String getVoiceText(@RequestParam("file") MultipartFile file) {
		//失败返回
		VoiceTextVo vtvo = new VoiceTextVo();
		vtvo.setRole(ConstantCfg.SYSTEN_MESSAGE);
		vtvo.setChatInfo("语音识别失败");
		if (file.isEmpty()) {
			return JSONObject.toJSONString(vtvo);
		}
		// 获取文件名
		String fileName = file.getOriginalFilename();
		logger.info("上传的文件名为：" + fileName);
		// 获取文件的后缀名
		String suffixName = fileName.substring(fileName.lastIndexOf("."));
		logger.info("上传的后缀名为：" + suffixName);
		// 文件上传后的路径
		String audioPath = app.getTempPath();
		System.out.println(audioPath);
		// 解决中文问题，liunx下中文路径，图片显示问题
		// fileName = UUID.randomUUID() + suffixName;
		String tempFileName = "tempAudio" + suffixName;
		File dest = new File(audioPath + tempFileName);
		// 检测是否存在目录
		if (!dest.getParentFile().exists()) {
			dest.getParentFile().mkdirs();
		}
		try {
			file.transferTo(dest);
			return JSONObject.toJSONString(vS.getVoiceText(audioPath, tempFileName));
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return JSONObject.toJSONString(vtvo);
	}
}

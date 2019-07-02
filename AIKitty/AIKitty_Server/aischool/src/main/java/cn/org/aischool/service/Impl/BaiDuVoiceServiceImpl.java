package cn.org.aischool.service.Impl;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;
import com.baidu.aip.util.Util;

import cn.org.aischool.cfg.AppCfg;
import cn.org.aischool.cfg.ConstantCfg;
import cn.org.aischool.service.BaiDuVoiceService;
import cn.org.aischool.utils.Mp3ToPcmUtil;

/**
 * 
 * @author huangliyang
 * @Description 百度语音服务类
 * @Version
 * @Date 2019年2月26日
 */
@Service
public class BaiDuVoiceServiceImpl implements BaiDuVoiceService {

	//注入配置类
	@Autowired
	private AppCfg app;
	
	//语音合成
	@Override
	public String voiceSynthesis(String Text,String per) {
		// 初始化一个AipSpeech
		AipSpeech client = new AipSpeech(ConstantCfg.APP_ID, ConstantCfg.API_KEY,
				ConstantCfg.SECRET_KEY);
		// 可选：设置网络连接参数
		client.setConnectionTimeoutInMillis(2000);
		client.setSocketTimeoutInMillis(60000);

		// 可选：设置代理服务器地址, http和socket二选一，或者均不设置
		// client.setHttpProxy("proxy_host", proxy_port); // 设置http代理
		// client.setSocketProxy("proxy_host", proxy_port); // 设置socket代理

		// 可选：设置log4j日志输出格式，若不设置，则使用默认配置
		// 也可以直接通过jvm启动参数设置此环境变量
		System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

		// 设置可选参数
		HashMap<String, Object> options = new HashMap<String, Object>();
	    options.put("spd", "4");  //spd 语速，取值0-9，默认为5中语速 
	    options.put("pit", "5");  //pit 音调，取值0-9，默认为5中语调 
	    options.put("per", per);  //per 发音人选择, 0为女声，1为男声，3为情感合成-度逍遥，4为情感合成-度丫丫，默认为普通女
	                              //vol 音量，取值0-15，默认为5中音量 
		// 调用接口                   
		TtsResponse res = client.synthesis(Text, "zh", 1, options);
		byte[] data = res.getData();
		JSONObject res1 = res.getResult();
		if (data != null) {
			try {
				// 获取跟目录
				File path = new File(app.getTempPath());
//				File path = new File("E:/aischool/audiotemp/");
//				File path = new File(ResourceUtils.getURL("classpath:").getPath());
				if (!path.exists())
//					path = new File("E:/aischool/audiotemp/");
					path = new File(app.getTempPath());
				String videoPath = path.getAbsolutePath();
				System.out.println("path:" + path.getAbsolutePath());
				Util.writeBytesToFileSystem(data, videoPath + "/output.mp3");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if (res1 != null) {
			System.out.println(res1.toString(2));
		}
		return null;
	}

	//语音识别
	@Override
	public String voiceRecognition(String audioPath, String tempFileName) throws Exception {
		// 初始化一个AipSpeech
		AipSpeech client = new AipSpeech(ConstantCfg.APP_ID, ConstantCfg.API_KEY,
				ConstantCfg.SECRET_KEY);
		// 可选：设置网络连接参数
		client.setConnectionTimeoutInMillis(2000);
		client.setSocketTimeoutInMillis(60000);

		// 可选：设置代理服务器地址, http和socket二选一，或者均不设置
		// client.setHttpProxy("proxy_host", proxy_port); // 设置http代理
		// client.setSocketProxy("proxy_host", proxy_port); // 设置socket代理

		// 可选：设置log4j日志输出格式，若不设置，则使用默认配置
		// 也可以直接通过jvm启动参数设置此环境变量
		System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

		// 调用接口
		// JSONObject res = client.asr("C:/Users/hp/Documents/Tencent
		// Files/1968171453/FileRecv/20171127_213608.wav", "wav", 16000, null);
		// System.out.println(res.toString(2));

		// 将接收到的mp3文件转成pcm文件
		Mp3ToPcmUtil.mp3ToPcm(audioPath + tempFileName, audioPath + "audio.pcm");
		// 设置请求语音识别的参数
		HashMap<String, Object> options = new HashMap<String, Object>();
		options.put("dev_pid", 1536);
		// 发起请求
		JSONObject res = client.asr(audioPath + "audio.pcm", "pcm", 16000, options);
		// 获得key为“result”的识别内容
		JSONArray result = (JSONArray) res.get("result");
		//System.out.println(res.toString());
		//System.out.println(result.toString());
		return result.getString(0).toString();
	}

}

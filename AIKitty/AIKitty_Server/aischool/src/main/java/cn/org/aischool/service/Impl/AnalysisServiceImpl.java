package cn.org.aischool.service.Impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import cn.org.aischool.cfg.ConstantCfg;
import cn.org.aischool.entity.EXPRESS;
import cn.org.aischool.entity.JOKE;
import cn.org.aischool.entity.MUSIC;
import cn.org.aischool.entity.NEWS;
import cn.org.aischool.entity.RobotResVo;
import cn.org.aischool.entity.UnitRes;
import cn.org.aischool.entity.WEATHER;
import cn.org.aischool.service.AnalysisService;
import cn.org.aischool.service.BaiDuVoiceService;
import cn.org.aischool.service.ResourcesService;
import cn.org.aischool.service.UnitService;

/**
 * 
 * @author huangliyang
 * @Description 接收前端传入的文字进行unit处理分析的服务类
 * @Version
 * @Date 2019年2月26日
 */
@Service
public class AnalysisServiceImpl implements AnalysisService {

	 @Autowired
	 private BaiDuVoiceService baiDuVoiceService;
	
	 @Autowired
	 private UnitService unitService;
	
	 @Autowired
	 private ResourcesService resourcesService;

	@Override
	public RobotResVo getRobotRes(String query) {

//		BaiDuVoiceService baiDuVoiceService = new BaiDuVoiceServiceImpl();
//
//		UnitService unitService = new UnitServiceImpl();
//
//		ResourcesService resourcesService = new ResourcesServiceImpl();
		// 声明返回的对象
		RobotResVo rrVo = new RobotResVo();
		rrVo.setRole(ConstantCfg.CHATOBJECT_BOT);
		//获取本地IP地址用于audioUrl拼接
		String localIp = "www.aischool.org.cn";
//		try {
//			InetAddress adress = InetAddress.getLocalHost();
//			localIp = adress.getHostAddress();
//			System.out.println(localIp);
//		} catch (UnknownHostException e1) {
//			localIp = "www.pmproj.cn";
//			e1.printStackTrace();
//		}
		// 处理接受unit应答后的逻辑
		UnitRes unitRes = unitService.getUnitRes(query);
		if(unitRes == null) {
			baiDuVoiceService.voiceSynthesis("哎呀，出错了，我马上喊主人修我哈。", "4");//用度丫丫情感读出
			RobotResVo rVo = new RobotResVo(ConstantCfg.CHATOBJECT_BOT, 
					"哎呀，出错了，我马上喊主人修我哈。", null, null, "www.aischool.org.cn:8888/aischool/output.mp3");
			return rVo;
		}
		// 意图 用于匹配获取什么资源
		String intent = unitRes.getIntentName();
		switch (intent) {
		
		case ConstantCfg.MUSIC:
			Map<String, Map<String, String>> musicMap = unitRes.getUserSlots();
			List<MUSIC> musicList;
			String s = "";
			if (musicMap.get("user_song") != null) {
				s = musicMap.get("user_song").get("normalized_word");
				musicList = resourcesService.getMusicList(s, "song", 8, 1);
			} else if (musicMap.get("user_song") == null && musicMap.get("user_singer") != null) {
				s = musicMap.get("user_singer").get("normalized_word");
				musicList = resourcesService.getMusicList(s, "song", 8, 1);
			} else {
				musicList = resourcesService.getHotMusicList("26", 8, 0);
			}
//			for (MUSIC music : musicList) {
//				System.out.println(music.getName());
//			}
			rrVo.setData(JSONObject.toJSONString(musicList).toString());
			rrVo.setNavigateUrl("/pages/musicPlay/musicPlay");
			rrVo.setSay(unitRes.getSay());
			break;
			
		case ConstantCfg.WEATHER:
			Map<String, Map<String, String>> weatherMap = unitRes.getUserSlots();
			String area = "";
			String time = "";
			if (weatherMap.get("user_location") != null) {
				area = weatherMap.get("user_location").get("original_word");
			} else {
				area = "北京";
			}
			if (weatherMap.get("user_time") != null) {
				String user_time = weatherMap.get("user_time").get("normalized_word");
				try {
					Date date = new SimpleDateFormat("yyyy-MM-dd").parse(user_time);
					time = new SimpleDateFormat("yyyyMMdd").format(date);
				} catch (ParseException e) {
					e.printStackTrace();
					time = new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis());
				}
					
			} else {
				time = new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis());
			}

			List<WEATHER> weather = resourcesService.getWeather(area);
			for (WEATHER weather2 : weather) {
				if (weather2.getDaytime().equals(time)) {
					rrVo.setSay(weather2.toString());
				}
			}
			break;
			
		case ConstantCfg.NEWS:
//			Map<String, Map<String, String>> newsMap = unitRes.getUserSlots();
			List<NEWS> news = resourcesService.getNews(null);
			Collections.shuffle(news);//打乱
			Object newsJson = JSONObject.toJSON(news.get(0));//取第一条新闻
			rrVo.setData(newsJson.toString());
			rrVo.setSay("今日头条："+news.get(0).getTitle()+"</br>点击查看具体内容");
			break;
			
		case ConstantCfg.FAQ_JOKE:
			int n = (int) (Math.random()*2);//随机是查询的笑话  还是unit回复的笑话
			if(n == 0){
				List<JOKE> jokes = resourcesService.getJokes();
				Collections.shuffle(jokes);//打乱
				Object jokesJson = JSONObject.toJSON(jokes.get(0).getText());//取第一条笑话
				rrVo.setSay(jokesJson.toString());
			}else {
				rrVo.setSay(unitRes.getSay());
			}
			break;
			
		case ConstantCfg.EXPRESS:
			Map<String, Map<String, String>> expressMap = unitRes.getUserSlots();
			String no = "";
			if(expressMap.get("user_courier_num") != null){
				no = expressMap.get("user_courier_num").get("original_word").toString();
			}else {
				rrVo.setSay("说出你的快递单号哟！请这样说：快递单号是+你的快递单号");
				break;
			}
			if(no != null && no.length() > 0) {
				List<EXPRESS> express = resourcesService.getExpress(no);
				EXPRESS expressNew = express.get(0);//第一条，最新的快递消息
				if(expressNew == null) {
					rrVo.setSay("糟了，没查到，请检查快递单号后直接说：快递单号是+你的快递单号");
				}
				String now = new SimpleDateFormat("yyyy年MM月dd日HH时mm分").format(System.currentTimeMillis());
				rrVo.setSay("快递单号："+expressNew.getNo()+"。 "+now+"，您的快递到达"+expressNew.getContent());
			}
			break;
			
		case ConstantCfg.COURSE:
			rrVo.setSay("老铁，不好意思，学校还没开放接口，去学校官网看看吧，未来，我将越来越聪明，期待为您做更多的事情！");
			break;
			
		case ConstantCfg.FAQ_LOCATION:
			rrVo.setSay(unitRes.getSay());
			rrVo.setNavigateUrl("/pages/map/map");
			break;
			
		case ConstantCfg.FAQ_SHOW_FUTURE:
			rrVo.setSay(unitRes.getSay());
			rrVo.setNavigateUrl("/pages/showFuture/showFuture");
			break;
			
		case ConstantCfg.FAQ_YUHUI://给宝宝的专属表白
			rrVo.setSay(unitRes.getSay());
			baiDuVoiceService.voiceSynthesis(rrVo.getSay(), "4");//情书用度丫丫情感读出
			rrVo.setAudioUrl("http://"+localIp+":8888/aischool/output.mp3");
			return rrVo;

		default:
			rrVo.setSay(unitRes.getSay());
			break;
		}
		if(rrVo.getSay() == null && rrVo.getSay().length() <= 0) {
			baiDuVoiceService.voiceSynthesis("好累啊，不想读，我只想说我爱你呀，比心。", "4");
		}
		int i = (int) (Math.random()*100);//随机彩蛋 设定概率为1/100
		if(i == 0) {
			baiDuVoiceService.voiceSynthesis("这是一个彩蛋哟，主人说，我说一百次话才允许我偷懒一次呢。我爱你！", "4");
		}else{
			baiDuVoiceService.voiceSynthesis(rrVo.getSay(), "0");
		}
		System.out.println(i);
		rrVo.setAudioUrl("http://"+localIp+":8888/aischool/output.mp3");
		return rrVo;
	}

	public static void main(String[] args) {
		AnalysisServiceImpl al = new AnalysisServiceImpl();
		RobotResVo robotRes = al.getRobotRes("今天什么课");
		Object json = JSONObject.toJSON(robotRes);
		System.out.println(json);
	}
}

package cn.org.aischool.service.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.org.aischool.entity.EXPRESS;
import cn.org.aischool.entity.JOKE;
import cn.org.aischool.entity.MUSIC;
import cn.org.aischool.entity.NEWS;
import cn.org.aischool.entity.WEATHER;
import cn.org.aischool.service.ResourcesService;
import cn.org.aischool.utils.AliYunHttpUtils;

/**
 * 
 * @author huangliyang
 * @Description 获取资源的服务类
 * @Version
 * @Date 2019年2月26日
 */
@Service
public class ResourcesServiceImpl implements ResourcesService {

	@Override
	public List<MUSIC> getMusicList(String keyword, String type, Integer pageSize, Integer page) {
		List<MUSIC> musicList = new ArrayList<MUSIC>();
		String host = "https://23333333.itooi.cn";
		String path = "/tencent/search/";
		String method = "GET";
		Map<String, String> headers = new HashMap<String, String>();// 没有或为空会报错
		headers.put("user-agent", "user-agent");
		headers.put("Content-type", "application/x-www-form-urlencoded");
		Map<String, String> querys = new HashMap<String, String>();
		//querys.put("key", "765929048");
		querys.put("keyword", keyword);
		querys.put("type", type);
		querys.put("pageSize", pageSize.toString());
		querys.put("page", page.toString());
		querys.put("format","1");
		try {

			HttpResponse response = AliYunHttpUtils.doGet(host, path, method, headers, querys);

			JSONObject object = JSONObject.parseObject(EntityUtils.toString(response.getEntity()));

			JSONArray jsonArray = object.getJSONArray("data");

			List<MUSIC> parseArray = JSONObject.parseArray(jsonArray.toJSONString(), MUSIC.class);

//			for (MUSIC music : parseArray) {
//				musicList.add(music);
//			}
			for (int i = 0 ; i < 6 ; i++) {
				musicList.add(parseArray.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return musicList;
	}

	@Override
	public List<MUSIC> getHotMusicList( String id, Integer pageSize, Integer page) {
		List<MUSIC> musicList = new ArrayList<MUSIC>();
		String host = "https://23333333.itooi.cn";
		String path = "/tencent/topList/";
		String method = "GET";
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("user-agent", "user-agent");
		headers.put("Content-type", "application/x-www-form-urlencoded");
		Map<String, String> querys = new HashMap<String, String>();
		//querys.put("key", "765929048");
		querys.put("id", id);
		querys.put("pageSize", pageSize.toString());
		querys.put("page", page.toString());
		querys.put("format","1");
		try {

			HttpResponse response = AliYunHttpUtils.doGet(host, path, method, headers, querys);

			JSONObject object = JSONObject.parseObject(EntityUtils.toString(response.getEntity()));

			JSONArray jsonArray = object.getJSONArray("data");

			List<MUSIC> parseArray = JSONObject.parseArray(jsonArray.toJSONString(), MUSIC.class);

//			for (MUSIC music : parseArray) {
//				musicList.add(music);
//			}
			for (int i = 0 ; i < 6 ; i++) {
				musicList.add(parseArray.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return musicList;
	}
	
	@Override
	public List<WEATHER> getWeather(String area) {
		List<WEATHER> weatherList = new ArrayList<WEATHER>();
		String host = "http://saweather.market.alicloudapi.com";
		String path = "/day15";
		String method = "GET";
		String appcode = "724d58f185b34a6da286605e2022329c";
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", "APPCODE " + appcode);
		Map<String, String> querys = new HashMap<String, String>();
		querys.put("area", area);// 地名不能为空
		// querys.put("areaid", "101230506");
		try {
			HttpResponse response = AliYunHttpUtils.doGet(host, path, method, headers, querys);

			JSONObject object = JSONObject.parseObject(EntityUtils.toString(response.getEntity()));

			JSONArray jsonArray = object.getJSONObject("showapi_res_body").getJSONArray("dayList");

			List<WEATHER> parseArray = JSONObject.parseArray(jsonArray.toJSONString(), WEATHER.class);

			for (WEATHER weather : parseArray) {
				weatherList.add(weather);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return weatherList;
	}

	@Override
	public List<NEWS> getNews(String type) {
		List<NEWS> NewsList = new ArrayList<NEWS>();
		String host = "http://toutiao-ali.juheapi.com";
		String path = "/toutiao/index";
		String method = "GET";
		String appcode = "724d58f185b34a6da286605e2022329c";
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", "APPCODE " + appcode);
		Map<String, String> querys = new HashMap<String, String>();
		querys.put("type", type);

		try {

			HttpResponse response = AliYunHttpUtils.doGet(host, path, method, headers, querys);

			JSONObject object = JSONObject.parseObject(EntityUtils.toString(response.getEntity()));

			JSONArray jsonArray = object.getJSONObject("result").getJSONArray("data");

			List<NEWS> parseArray = JSONObject.parseArray(jsonArray.toJSONString(), NEWS.class);

			for (NEWS news : parseArray) {
				NewsList.add(news);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return NewsList;
	}

	@Override
	public List<EXPRESS> getExpress(String no) {
		List<EXPRESS> expressList = new ArrayList<EXPRESS>();
		String host = "https://goexpress.market.alicloudapi.com";
		String path = "/goexpress";
		String method = "GET";
		String appcode = "724d58f185b34a6da286605e2022329c";
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", "APPCODE " + appcode);
		Map<String, String> querys = new HashMap<String, String>();
		querys.put("no", no);// 快递单号
		// querys.put("type", "zto");//快递公司
		try {
			HttpResponse response = AliYunHttpUtils.doGet(host, path, method, headers, querys);

			JSONObject object = JSONObject.parseObject(EntityUtils.toString(response.getEntity()));

			JSONArray jsonArray = object.getJSONArray("list");
			// 获取快递公司名称
			Object name = object.get("name");
			// 获取快递单号
			Object num = object.get("no");

			List<EXPRESS> parseArray = JSONObject.parseArray(jsonArray.toJSONString(), EXPRESS.class);

			for (EXPRESS express : parseArray) {
				express.setName(name.toString());
				express.setNo(num.toString());
				expressList.add(express);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return expressList;
	}

	@Override
	public String getCourse() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<JOKE> getJokes() {
		List<JOKE> jokeList = new ArrayList<JOKE>();
		String host = "https://ali-joke.showapi.com";
		String path = "/textJoke";
		String method = "GET";
		String appcode = "724d58f185b34a6da286605e2022329c";
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", "APPCODE " + appcode);
		Map<String, String> querys = new HashMap<String, String>();
		querys.put("maxResult", "50");//最大条数
		// querys.put("page", "1");
		// querys.put("time", "2016-07-01");
		try {
			HttpResponse response = AliYunHttpUtils.doGet(host, path, method, headers, querys);

			JSONObject object = JSONObject.parseObject(EntityUtils.toString(response.getEntity()));

			JSONArray jsonArray = object.getJSONObject("showapi_res_body").getJSONArray("contentlist");

			List<JOKE> parseArray = JSONObject.parseArray(jsonArray.toJSONString(), JOKE.class);

			for (JOKE joke : parseArray) {
				jokeList.add(joke);
				// Object jsonString = JSONObject.toJSON(joke);
				System.out.println(joke.getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jokeList;
	}

	public static void main(String[] args) {
		ResourcesServiceImpl l = new ResourcesServiceImpl();
//		List<JOKE> jokes = l.getJokes();
//		System.out.println(jokes.get(1).getText());
//		
		 List<MUSIC> musicList = l.getHotMusicList("26", 6, 0);
		 System.out.println(musicList);
//		
//		 List<WEATHER> list = l.getWeather("广州市");
//		 for (WEATHER weather : list) {
//		 System.out.println(weather.toString());
//		 }
		 
//		 List<NEWS> list = l.getNews(null);
//		 for (NEWS news : list) {
//			 System.out.println(news.toString());
//		 }
//		
//		 List<EXPRESS> list = l.getExpress("75133040501204");
//		 for (EXPRESS e : list) {
//		 System.out.println(e.getContent().toString());
//		 }

	}
}

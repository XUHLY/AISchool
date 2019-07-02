package cn.org.aischool.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cn.org.aischool.entity.EXPRESS;
import cn.org.aischool.entity.JOKE;
import cn.org.aischool.entity.MUSIC;
import cn.org.aischool.entity.NEWS;
import cn.org.aischool.entity.WEATHER;

/**
 * 
 * @author huangliyang
 * @Description 获取资源的服务接口
 * @Version 
 * @Date 2019年2月26日
 */
@Service
public interface ResourcesService {

	/** 获取歌曲资源 
	 * @param  keyword 搜索关键词   必填  自行处理默认搜索抚仙湖
	 * @param  type 搜索类型  默认搜索音乐     必填1. 音乐搜索:type=song
									2. 专辑搜索:type=album
									3. 歌单搜索:type=list (QQ音乐限制歌单每页最多查询50条)
									4. MV搜索:type=mv
									5. 用户搜索:type=user
									6. 歌词搜索:type=lrc
	 * @param  pageSize 搜索结果数量  默认100条     不必填
	 * @param  page 搜索结果页数  默认一页        不必填
	 */
	public List<MUSIC> getMusicList(String keyword, String type, Integer pageSize, Integer page);
	
	/**
	 * @param id
	 * @param pageSize
	 * @param page
	 * @return
	 */
	List<MUSIC> getHotMusicList(String id, Integer pageSize, Integer page);
	
	/** 查询天气 
	 * @param area 地名  在处理时给个默认值为淮北
	 */
	public List<WEATHER> getWeather(String area);
	
	/** 获取新闻资源
	 *@param type 新闻类型（可选）
	 * top(头条，默认) shehui(社会) guonei(国内) guoji(国际) yule(娱乐)
	 * tiyu(体育) junshi(军事) keji(科技) caijing(财经) shishang(时尚)
	 */
	public List<NEWS>  getNews(String type);
	
	/** 获取笑话资源 
	 */
	public List<JOKE> getJokes();
	
	/** 查询快递 
	 * @param no 快递单号
	 */
	public List<EXPRESS> getExpress(String no);
	
	/** 查询课程 
	 */
	public String getCourse();

	
	
}

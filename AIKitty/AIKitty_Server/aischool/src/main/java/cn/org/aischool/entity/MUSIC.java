package cn.org.aischool.entity;

import org.springframework.stereotype.Component;
/**
 * 
 * @author huangliyang
 * @Description 返回的音乐数据格式
 * @Version 
 * @Date 2019年2月20日
 */
@Component
public class MUSIC {

	private String id;
	private String name;
	private String time;
	private String singer;
	private String url;
	private String pic;
	private String lrc;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getSinger() {
		return singer;
	}
	public void setSinger(String singer) {
		this.singer = singer;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getPic() {
		return pic;
	}
	public void setPic(String pic) {
		this.pic = pic;
	}
	public String getLrc() {
		return lrc;
	}
	public void setLrc(String lrc) {
		this.lrc = lrc;
	}
	@Override
	public String toString() {
		return "Song [id=" + id + ", name=" + name + ", time=" + time + ", singer=" + singer + ", url=" + url + ", pic="
				+ pic + ", lrc=" + lrc + "]";
	}


}

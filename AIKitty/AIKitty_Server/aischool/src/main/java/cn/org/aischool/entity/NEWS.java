package cn.org.aischool.entity;

import org.springframework.stereotype.Component;
/**
 * 
 * @author huangliyang
 * @Description 返回的新闻实体
 * @Version 
 * @Date 2019年2月20日
 */
@Component
public class NEWS {
// {"uniquekey":"fa543318db6b33c7923eba8eb68bdee9",
//	"title":"2019款国产奔驰A级比肩奔驰E级，内饰太亮眼，网友：这是A级车？",
//	"date":"2019-03-01 12:59",
//	"category":"头条",
//	"author_name":"车方向",
//	"url":"http:\/\/mini.eastday.com\/mobile\/190301125907567.html",
//	"thumbnail_pic_s":"http:\/\/07imgmini.eastday.com\/mobile\/20190301\/20190301125907_01906b31d25067f6c9145cc9fbd00926_4_mwpm_03200403.jpg",
//	"thumbnail_pic_s02":"http:\/\/07imgmini.eastday.com\/mobile\/20190301\/20190301125907_01906b31d25067f6c9145cc9fbd00926_3_mwpm_03200403.jpg",
//	"thumbnail_pic_s03":"http:\/\/07imgmini.eastday.com\/mobile\/20190301\/20190301125907_01906b31d25067f6c9145cc9fbd00926_6_mwpm_03200403.jpg"}
	private String uniquekey;
	private String title;
	private String date;
	private String category;
	private String author_name;
	private String url;
	private String thumbnail_pic_s;
	private String thumbnail_pic_s02;
	private String thumbnail_pic_s03;
	public String getUniquekey() {
		return uniquekey;
	}
	public void setUniquekey(String uniquekey) {
		this.uniquekey = uniquekey;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getAuthor_name() {
		return author_name;
	}
	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getThumbnail_pic_s() {
		return thumbnail_pic_s;
	}
	public void setThumbnail_pic_s(String thumbnail_pic_s) {
		this.thumbnail_pic_s = thumbnail_pic_s;
	}
	public String getThumbnail_pic_s02() {
		return thumbnail_pic_s02;
	}
	public void setThumbnail_pic_s02(String thumbnail_pic_s02) {
		this.thumbnail_pic_s02 = thumbnail_pic_s02;
	}
	public String getThumbnail_pic_s03() {
		return thumbnail_pic_s03;
	}
	public void setThumbnail_pic_s03(String thumbnail_pic_s03) {
		this.thumbnail_pic_s03 = thumbnail_pic_s03;
	}
}

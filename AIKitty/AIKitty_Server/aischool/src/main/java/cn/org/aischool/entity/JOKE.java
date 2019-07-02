package cn.org.aischool.entity;

import org.springframework.stereotype.Component;
/**
 * 
 * @author huangliyang
 * @Description 返回的笑话数据格式
 * @Version 
 * @Date 2019年2月20日
 */
@Component
public class JOKE {
    /* {
    "showapi_res_error": "",
    "showapi_res_id": "c1fb8c88c5ef4455ac4af1b37f6eff26",
    "showapi_res_code": 0,
    "showapi_res_body": {"allPages":992,"ret_code":0,
    "contentlist":
    [{"id":"5c7a0dd46e363207e1a32dc0",
    "title":"我一直在虚心学习数卷...",
    "text":"我一直在虚心学习数卷高端深奥的论述语言，终于学有所成，今天给大家来一段。“脱胎于帕埃拉雏形社会的萨拉米民族，因其根深蒂固的派柏罗尼性而无法脱费列罗，几乎无法自发莫扎里拉化到拉沃奥利层次，注定永久地错过法费莱进程而深陷在派珀罗尼漩涡中，只能一次又一次地重演着拉扎涅亚循环，这是萨拉米顺民共通的玛卡罗尼宿命。”不要被一堆不明觉厉的术语吓走，我只是罗列了一堆吃的。","type":1,"ct":"2019-03-02 13:00:04.507"},{"id":"5c7a011e6e363207e1a32965","title":"一夫妻倆吵架，男的大...","text":"一夫妻倆吵架，男的大声对女的吼道：“我他妈就你生命中一过客！”\r\n女的幽幽的来了句：“过客也是客啊，那咱也得接啊。”",
    "type":1,
    "ct":"2019-03-02 12:05:50.723"}
    ],
    "currentPage":1,
    "allNum":19827,
    "maxResult":20}
  }
*/
	private String id;
	private String ct;
	private String text;
	private String title;
	private String type;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCt() {
		return ct;
	}
	public void setCt(String ct) {
		this.ct = ct;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	
}

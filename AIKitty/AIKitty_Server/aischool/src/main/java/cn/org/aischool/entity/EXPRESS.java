package cn.org.aischool.entity;


import org.springframework.stereotype.Component;
/**
 * 
 * @author huangliyang
 * @Description 返回的快递数据格式
 * @Version 
 * @Date 2019年2月20日
 */
@Component
public class EXPRESS {
	/*
	 * {"code":"OK",
	 * "no":"780098068058",
	 * "type":"ZTO",
	 * "list":[{"content":"【石家庄市】 快件已在 【长安三部】 签收,签收人: 本人, 感谢使用中通快递,期待再次为您服务!","time":"2018-03-09 11:59:26"},
	 * {"content":"【石家庄市】 快件已到达 【长安三部】（0311-85344265）,业务员 容晓光（13081105270） 正在第1次派件, 请保持电话畅通,并耐心等待","time":"2018-03-09 09:03:10"},
	 * {"content":"【石家庄市】 快件离开 【石家庄】 发往 【长安三部】","time":"2018-03-08 23:43:44"},{"content":"【石家庄市】 快件到达 【石家庄】","time":"2018-03-08 21:00:44"},
	 * {"content":"【广州市】 快件离开 【广州中心】 发往 【石家庄】","time":"2018-03-07 01:38:45"},
	 * {"content":"【广州市】 快件到达 【广州中心】","time":"2018-03-07 01:36:53"},
	 * {"content":"【广州市】 快件离开 【广州花都】 发往 【石家庄中转】","time":"2018-03-07 00:40:57"},
	 * {"content":"【广州市】 【广州花都】（020-37738523） 的 马溪 （18998345739） 已揽收","time":"2018-03-07 00:01:55"}],"state":"3","msg":"查询成功","name":"中通快递","site":"www.zto.com","phone":"95311","logo":"http://img3.fegine.com/express/zto.jpg"}
	 */

	private String content;//内容
	private String time;//时间
	private String name;//快递公司
	private String no;//快递单号
	
	@Override
	public String toString() {
		return "快递单号："+no+"\n</br>"+"您的"+name+"快递已经到达："+content+"\n</br>"+"到达时间："+time;
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}
	
	
}

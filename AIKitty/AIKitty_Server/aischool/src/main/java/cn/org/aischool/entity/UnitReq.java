package cn.org.aischool.entity;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Component;

/**
 * 
 * @author huangliyang
 * @Description UNIT对话参数实体
 * @Version 
 * @Date 2019年2月19日
 */
@Component
public class UnitReq {
	private String version;//2.0，当前api版本对应协议版本号为2.0，固定值
	private String service_id;//可选	机器人ID，service_id 与skill_ids不能同时缺失，至少一个有值。
	private List<String> skill_ids;//可选	技能ID列表
	private String log_id;//必需	开发者需要在客户端生成的唯一id，用来定位请求，响应中会返回该字段。对话中每轮请求都需要一个log_id
	private String session_id;//必需	session保存机器人的历史会话信息，由机器人创建，客户端从上轮应答中取出并直接传递，不需要了解其内容。
	private HashMap<String, String> dialog_state;//可选 机器人对话状态
	private HashMap<String, String> request;//必需	本轮请求体。
	
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getService_id() {
		return service_id;
	}
	public void setService_id(String service_id) {
		this.service_id = service_id;
	}
	public List<String> getSkill_ids() {
		return skill_ids;
	}
	public void setSkill_ids(List<String> skill_ids) {
		this.skill_ids = skill_ids;
	}
	public String getLog_id() {
		return log_id;
	}
	public void setLog_id(String log_id) {
		this.log_id = log_id;
	}
	public String getSession_id() {
		return session_id;
	}
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}
	public HashMap<String, String> getDialog_state() {
		return dialog_state;
	}
	public void setDialog_state(HashMap<String, String> dialog_state) {
		this.dialog_state = dialog_state;
	}
	public HashMap<String, String> getRequest() {
		return request;
	}
	public void setRequest(HashMap<String, String> request) {
		this.request = request;
	}
	
}
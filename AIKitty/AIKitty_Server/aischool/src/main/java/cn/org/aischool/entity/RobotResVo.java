package cn.org.aischool.entity;

import org.springframework.stereotype.Component;
/**
 * 
 * @author huangliyang
 * @Description 机器人返回的数据类型
 * @Version 
 * @Date 2019年2月20日
 */
@Component
public class RobotResVo {
	
	private Integer role;// 用于确定是用户还是机器人  这里面应该设置为机器人

	private String say;// 回答的具体内容
	
	private String data;//数据
	
	private String navigateUrl;// 跳转链接
	
	private String audioUrl;//音频链接
	
	public RobotResVo() {}

	public RobotResVo(Integer role, String say, String data, String navigateUrl, String audioUrl) {
		super();
		this.role = role;
		this.say = say;
		this.data = data;
		this.navigateUrl = navigateUrl;
		this.audioUrl = audioUrl;
	}

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public String getSay() {
		return say;
	}

	public void setSay(String say) {
		this.say = say;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getNavigateUrl() {
		return navigateUrl;
	}

	public void setNavigateUrl(String navigateUrl) {
		this.navigateUrl = navigateUrl;
	}

	public String getAudioUrl() {
		return audioUrl;
	}

	public void setAudioUrl(String audioUrl) {
		this.audioUrl = audioUrl;
	}
}

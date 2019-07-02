package cn.org.aischool.entity;

import org.springframework.stereotype.Component;

/**
 * 
 * @author huangliyang
 * @Description 语音识别的返回对象
 *
 */
@Component
public class VoiceTextVo {

	private Integer role;//用于确定是用户还是机器人   这里面应该设置为用户
	
	private String chatInfo;//具体内容

	public Integer getRole() {
		return role;
	}

	public void setRole(Integer role) {
		this.role = role;
	}

	public String getChatInfo() {
		return chatInfo;
	}

	public void setChatInfo(String chatInfo) {
		this.chatInfo = chatInfo;
	}
}

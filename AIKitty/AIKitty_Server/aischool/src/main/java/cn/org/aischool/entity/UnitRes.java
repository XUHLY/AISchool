package cn.org.aischool.entity;

import java.util.Map;

import org.springframework.stereotype.Component;


/**
 * 
 * @author huangliyang
 * @Description UNIT返回的对话封装
 * @Version 
 * @Date 2019年2月19日
 */
@Component
public class UnitRes {
	
	//机器人回答的话
	private String say;
	
	//意图名称  用于匹配
	private String intentName;
	
	//意图返回的词槽参数
	private Map<String, Map<String,String>> userSlots;
	
	
	public String getSay() {
		return say;
	}
	public void setSay(String say) {
		this.say = say;
	}
	public String getIntentName() {
		return intentName;
	}
	public void setIntentName(String intentName) {
		this.intentName = intentName;
	}
	public Map<String, Map<String, String>> getUserSlots() {
		return userSlots;
	}
	public void setUserSlots(Map<String, Map<String, String>> userSlots) {
		this.userSlots = userSlots;
	}
}
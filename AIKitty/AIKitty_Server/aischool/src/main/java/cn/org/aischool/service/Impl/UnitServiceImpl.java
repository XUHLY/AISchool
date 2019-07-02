package cn.org.aischool.service.Impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cn.org.aischool.entity.UnitReq;
import cn.org.aischool.entity.UnitRes;
import cn.org.aischool.service.UnitService;
import cn.org.aischool.utils.BaiDuHttpUtils;

/**
 * 
 * @author huangliyang
 * @Description unit对话服务
 * @Version 
 * @Date 2019年2月19日
 */
@Service
public class UnitServiceImpl implements UnitService {
	/*{\"log_id\":\"UNITTEST_10000\",
	 * \"version\":\"2.0\",
	 * \"service_id\":\"S11756\",
	 * \"session_id\":\"service-session-id-1550565063117-68c00eb498c94065946f4e71d141159f\",
	 * \"request\":
	 * 				{\"query\":\"你妈妈是谁\",
	 * 				\"user_id\":\"88888\"},
	 * 				\"dialog_state\":{\"contexts\":{\"SYS_REMEMBERED_SKILLS\":[\"1057\"]}}}";
     
     *{"log_id":"UNITTEST_10000",
     *"request":{"user_id":"88888"},
     *"service_id":"S11756",
     *"dialog_state":{"contexts":"{\"SYS_REMEMBERED_SKILLS\":[\"1057\"]}"},
     *"version":"2.0"}

     */
	/**
	 * 
	 * @param query 对话问的文本
	 * @return UnitRes UNIT返回的对话封装
	 */
    public  UnitRes getUnitRes(String query) {
    	
    	//返回的对象
    	UnitRes ur = new UnitRes();
        // 请求URL
        String talkUrl = "https://aip.baidubce.com/rpc/2.0/unit/service/chat";
        //对话参数对象
        UnitReq urp = new UnitReq();
        String version = "2.0";
        String log_id = "UNITTEST_10000";
    	String service_id = "S11756";
    	List<String> skill_ids = null;
    	String session_id = "service-session-id-1550565063117-68c00eb498c94065946f4e71d141159f";
    	HashMap<String, String> dialog_state = new HashMap<String, String>();
//    	dialog_state.put("contexts", "{\"SYS_REMEMBERED_SKILLS\":[\"1057\"]");
    	HashMap<String, String> request = new HashMap<String, String>();
    	request.put("query", query);
    	request.put("user_id", "88888");
    	
    	urp.setVersion(version);
    	urp.setLog_id(log_id);
    	urp.setService_id(service_id);
    	urp.setSkill_ids(skill_ids);
    	urp.setSession_id(session_id);
    	urp.setDialog_state(dialog_state);
    	urp.setRequest(request);
    	
    	Object json = JSONObject.toJSON(urp);
        
        try {
            // 请求参数
//          String params = "{\"log_id\":\"UNITTEST_10000\",\"version\":\"2.0\",\"service_id\":\"S11756\",\"session_id\":\"service-session-id-1550565063117-68c00eb498c94065946f4e71d141159f\",\"request\":{\"query\":\"你妈妈是谁\",\"user_id\":\"88888\"},\"dialog_state\":{\"contexts\":{\"SYS_REMEMBERED_SKILLS\":[\"1057\"]}}}";
            String params = json.toString();
            System.out.println(json.toString());
            String accessToken = "24.7a4cf11d58915f90a39d2237df970413.2592000.1563435508.282335-15332293";
            String result = BaiDuHttpUtils.post(talkUrl, accessToken, "application/json", params);
            System.out.println(result);
            JSONObject resultObject = (JSONObject) JSONObject.parseObject(result).get("result");
            JSONArray responselist =  (JSONArray) resultObject.get("response_list");
            JSONObject responselist_0 = (JSONObject) responselist.get(0);
            //回答的话
            String say = (String) ((JSONObject)((JSONArray) responselist_0.get("action_list")).get(0)).get("say");
            //获取返回的意图名
            String intentName = (String) ((JSONObject)((JSONObject) responselist_0.get("schema"))).get("intent");
            //词槽列表
            JSONArray slots = (JSONArray) ((JSONObject)((JSONObject) responselist_0.get("schema"))).get("slots");
            
            //词槽map
            Map<String, Map<String,String>> userSlots = new HashMap<String, Map<String,String>>();
            
            if (slots != null) {
            	for (Object slot : slots) {
            		Map<String,String> userSlot = new HashMap<String,String>();
            		JSONObject s = (JSONObject) slot;
            		userSlot.put("original_word", s.get("original_word").toString());
            		userSlot.put("normalized_word", s.get("normalized_word").toString());
            		userSlots.put(s.getString("name"), userSlot);
            	}
			}
            
            //设置返回对象
            ur.setSay(say);
            ur.setIntentName(intentName);
            ur.setUserSlots(userSlots);
            
            return ur;
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }
    
    public static void main(String[] args) {
    	
    	UnitServiceImpl usl = new UnitServiceImpl();
    	
        System.out.println(JSONObject.toJSON(usl.getUnitRes("快递")));
    }
}
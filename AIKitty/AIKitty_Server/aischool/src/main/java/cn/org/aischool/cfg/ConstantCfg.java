package cn.org.aischool.cfg;

import org.springframework.stereotype.Component;

/**
 * @description:全局常量配置类
 * @author: huangliyang @Date：2019/01/29
 */
@Component
public class ConstantCfg {
	// 请求接口秘钥
	public static final String KEY = "971026";
	// 百度集成APP_ID
	public static final String APP_ID = "15332293";

	// 百度集成 API_KEY
	public static final String API_KEY = "m9iKRQVwwjW0FP2CZBkIut5C";

	// 百度集成 SECRET_KEY
	public static final String SECRET_KEY = "54gFMUf4nNnzZ4CAxMu5pYjrkRjZLMmG";

	// 智能机器人
	public static final Integer CHATOBJECT_BOT = 0;

	// 用户
	public static final Integer CHATOBJECT_USER = 1;

	// 系统提示
	public static final Integer SYSTEN_MESSAGE = 2;

	// 意图
	public static final String MUSIC = "MUSIC";

	// 意图
	public static final String EXPRESS = "EXPRESS";

	// 意图
	public static final String NEWS = "NEWS";

	// 意图
	public static final String WEATHER = "WEATHER";

	// 意图
	public static final String COURSE = "COURSE";

	// 意图
	public static final String FAQ_JOKE = "FAQ_JOKE";

	// 意图
	public static final String FAQ_YUHUI = "FAQ_YUHUI";

	// 意图
	public static final String FAQ_LOCATION = "FAQ_LOCATION";

	// 意图
	public static final String FAQ_SHOW_FUTURE = "FAQ_SHOWFUTURE";
}

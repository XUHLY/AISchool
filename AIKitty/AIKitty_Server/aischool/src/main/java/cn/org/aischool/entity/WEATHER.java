package cn.org.aischool.entity;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.stereotype.Component;

/**
 * 
 * @author huangliyang
 * @Description 返回的天气数据类型
 * @Version
 * @Date 2019年2月20日
 */
@Component
public class WEATHER {
	/*
	 * { "showapi_res_error": "", "showapi_res_id":
	 * "b0cac4855a0a4d33960964b7d79a3d85", "showapi_res_code": 0,
	 * "showapi_res_body": {"ret_code":0,"area":"广州","areaid":"101280101",
	 * "dayList":[ { "night_weather_code":"08", "day_weather":"多云",
	 * "night_weather":"中雨", "night_wind_power":"4-5级", "areaid":"101280101",
	 * "day_wind_power":"3-4级", "day_weather_code":"01", "daytime":"20190302",
	 * "area":"广州",
	 * "day_weather_pic":"http://app1.showapi.com/weather/icon/day/01.png",
	 * "night_air_temperature":"16", "day_air_temperature":"26",
	 * "day_wind_direction":"南风",
	 * "night_weather_pic":"http://app1.showapi.com/weather/icon/night/08.png",
	 * "night_wind_direction":"北风"}, }
	 */
	private String night_weather_code;// 晚上天气code
	private String day_weather;// 白天天气
	private String night_weather;// 晚上天气
	private String night_wind_power;// 晚上风力
	private String areaid;// 地名id
	private String day_wind_power;// 白天风力
	private String day_weather_code;// 白天天气code
	private String daytime;// 日期yyyyMMdd
	private String area;// 地名
	private String day_weather_pic;// 白天天气图片
	private String night_air_temperature;// 晚上气温
	private String day_air_temperature;// 白天气温
	private String day_wind_direction;// 白天风向
	private String night_weather_pic;// 晚上天气图片
	private String night_wind_direction;// 晚上风向

	@Override
	public String toString() {
		String dateString = null;
		try {
			dateString = new SimpleDateFormat("yyyy年MM月dd日").format(new SimpleDateFormat("yyyyMMdd").parse(daytime)) ;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return dateString+" "+area+" 白天 "+day_weather+" "+day_wind_direction+day_wind_power+",晚上 "+night_weather+" "
		      +night_wind_direction+night_wind_power+",全天气温 "+night_air_temperature+"℃"+"~"+day_air_temperature+"℃";
	}

	public String getNight_weather_code() {
		return night_weather_code;
	}

	public void setNight_weather_code(String night_weather_code) {
		this.night_weather_code = night_weather_code;
	}

	public String getDay_weather() {
		return day_weather;
	}

	public void setDay_weather(String day_weather) {
		this.day_weather = day_weather;
	}

	public String getNight_weather() {
		return night_weather;
	}

	public void setNight_weather(String night_weather) {
		this.night_weather = night_weather;
	}

	public String getNight_wind_power() {
		return night_wind_power;
	}

	public void setNight_wind_power(String night_wind_power) {
		this.night_wind_power = night_wind_power;
	}

	public String getAreaid() {
		return areaid;
	}

	public void setAreaid(String areaid) {
		this.areaid = areaid;
	}

	public String getDay_wind_power() {
		return day_wind_power;
	}

	public void setDay_wind_power(String day_wind_power) {
		this.day_wind_power = day_wind_power;
	}

	public String getDay_weather_code() {
		return day_weather_code;
	}

	public void setDay_weather_code(String day_weather_code) {
		this.day_weather_code = day_weather_code;
	}

	public String getDaytime() {
		return daytime;
	}

	public void setDaytime(String daytime) {
		this.daytime = daytime;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getDay_weather_pic() {
		return day_weather_pic;
	}

	public void setDay_weather_pic(String day_weather_pic) {
		this.day_weather_pic = day_weather_pic;
	}

	public String getNight_air_temperature() {
		return night_air_temperature;
	}

	public void setNight_air_temperature(String night_air_temperature) {
		this.night_air_temperature = night_air_temperature;
	}

	public String getDay_air_temperature() {
		return day_air_temperature;
	}

	public void setDay_air_temperature(String day_air_temperature) {
		this.day_air_temperature = day_air_temperature;
	}

	public String getDay_wind_direction() {
		return day_wind_direction;
	}

	public void setDay_wind_direction(String day_wind_direction) {
		this.day_wind_direction = day_wind_direction;
	}

	public String getNight_weather_pic() {
		return night_weather_pic;
	}

	public void setNight_weather_pic(String night_weather_pic) {
		this.night_weather_pic = night_weather_pic;
	}

	public String getNight_wind_direction() {
		return night_wind_direction;
	}

	public void setNight_wind_direction(String night_wind_direction) {
		this.night_wind_direction = night_wind_direction;
	}

}

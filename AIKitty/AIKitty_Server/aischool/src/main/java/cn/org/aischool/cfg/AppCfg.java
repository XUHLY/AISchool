package cn.org.aischool.cfg;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @description:全局变量配置类
 * @author: huangliyang
 * @Date：2019/01/29
 */
@Component
public class AppCfg {

	//上传录音临时存储位置
	@Value("${test.file.dir}")
	private String tempPath;

	public String getTempPath() {
		return tempPath;
	}

	public void setTempPath(String tempPath) {
		this.tempPath = tempPath;
	}
	
}

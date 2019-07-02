package cn.org.aischool.service;

import org.springframework.stereotype.Service;

import cn.org.aischool.entity.UnitRes;

/**
 * 
 * @author huangliyang
 * @Description 与unit对话的服务接口
 * @Version 
 * @Date 2019年2月19日
 */
@Service
public interface UnitService {

	/**
	 * 
	 * @param query 对话问的文本
	 * @return UnitRes UNIT返回的对话封装
	 */
    public  UnitRes getUnitRes(String query);
}

package cn.org.aischool.dao;

import org.apache.ibatis.annotations.Param;

//@Mapper
public interface BaiDuTokenDao {

	public void updateToken(@Param("token") String token);
}

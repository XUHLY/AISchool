package cn.org.aischool.service.Impl;

import org.springframework.transaction.annotation.Transactional;

import cn.org.aischool.service.BaiDuTokenService;

//@Service
public class BaiDuTokenServiceImpl implements BaiDuTokenService{

//	@Autowired
//	private BaiDuTokenDao baiDuTokenDao; 
	
	@Override
	@Transactional
	public void updateToken(String token) {
//		baiDuTokenDao.updateToken(token);		
	}

}

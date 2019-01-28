package com.xz.myo2o.service;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.xz.myo2o.dto.LocalAuthExecution;
import com.xz.myo2o.entity.LocalAuth;

/**
 * @author 作者
 * @version 创建时间：2019年1月15日 上午11:14:18 类说明
 */
public interface LocalAuthService {
	
	/**
	 * used: 根据用户名和密码获取用户信息
	 * last update time : 2019年1月15日下午2:22:30
	 * return :LocalAuth
	 * @param userName
	 * @param password
	 * @return
	 */
	LocalAuth getLocalAuthByUserNameAndPwd(String userName, String password);

	/**
	 * used: 用户注册 last update time : 2019年1月15日上午11:16:44 return :LocalAuthExecution
	 * 
	 * @param localAuth
	 * @param profileImg
	 * @return
	 * @throws RuntimeException
	 */
	LocalAuthExecution register(LocalAuth localAuth, CommonsMultipartFile profileImg) throws RuntimeException;
	
	
}

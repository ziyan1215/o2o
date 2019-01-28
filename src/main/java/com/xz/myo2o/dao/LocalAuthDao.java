package com.xz.myo2o.dao;

import org.apache.ibatis.annotations.Param;

import com.xz.myo2o.entity.LocalAuth;

/**
* @author 作者
* @version 创建时间：2019年1月15日 上午10:03:38
* 类说明 用户表操作
*/
public interface LocalAuthDao {
	
	 /**
	 * used: 新增用户
	 * last update time : 2019年1月15日上午11:13:26
	 * return :int
	 * @param localAuth
	 * @return
	 */
	//dao层方法只有一个参数时不用加@param，多个的时候要标注
	int insertLocalAuth(LocalAuth localAuth);
	
	
	/**
	 * used: 根据用户名和密码来查询用户信息，可以作为登陆的判断
	 * last update time : 2019年1月15日下午1:47:48
	 * return :LocalAuth
	 * @param userName
	 * @param password
	 * @return
	 * 
	 */
	//用注解来简化xml配置的时候,@Param注解的作用是给参数命名,参数命名后就能根据名字得到参数值,正确的将参数传入sql语句中
	LocalAuth getLocalAuthByUserNameAndPwd(@Param("userName") String userName,
			@Param("password") String password);
	//LocalAuth getLocalAuthByUserNameAndPwd(String userName, String password);
	
}

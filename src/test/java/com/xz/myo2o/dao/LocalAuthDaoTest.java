package com.xz.myo2o.dao;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xz.myo2o.entity.LocalAuth;
import com.xz.myo2o.entity.PersonInfo;
import com.xz.myo2o.BaseTest;
import com.xz.myo2o.dao.LocalAuthDao;
/**
* @author 作者
* @version 创建时间：2019年1月15日 上午10:13:32
* 类说明
*/
public class LocalAuthDaoTest extends BaseTest{
	//自动注入
	@Autowired
	private LocalAuthDao localAuthDao;
	@Autowired
	private PersonInfoDao personInfoDao;
	@Test
	public void test() {
		LocalAuth user =new LocalAuth();
		
		user.setCreateTime(new Date());
		user.setUserName("xuzi");
		user.setPassword("123456");
		//user.setPersonInfo(personInfo);
		
		int id =localAuthDao.insertLocalAuth(user);
		PersonInfo personInfo =new PersonInfo();
		
		personInfo.setEmail("test@xz.com");
		personInfo.setName("昵称xz");
		//必填项设置
		personInfo.setCustomerFlag(1);
		personInfo.setShopOwnerFlag(1);
		personInfo.setAdminFlag(1);
		personInfo.setEnableStatus(1);
		
	}

}

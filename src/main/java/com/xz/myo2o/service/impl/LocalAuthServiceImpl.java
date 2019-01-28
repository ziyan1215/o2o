package com.xz.myo2o.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.xz.myo2o.dao.LocalAuthDao;
import com.xz.myo2o.dao.PersonInfoDao;
import com.xz.myo2o.dto.LocalAuthExecution;
import com.xz.myo2o.entity.LocalAuth;
import com.xz.myo2o.entity.PersonInfo;
import com.xz.myo2o.enums.LocalAuthStateEnum;
import com.xz.myo2o.service.LocalAuthService;
import com.xz.myo2o.util.FileUtil;
import com.xz.myo2o.util.ImageUtil;
import com.xz.myo2o.util.MD5;

/**
* @author 作者
* @version 创建时间：2019年1月15日 上午11:17:53
* 类说明
*/
@Service
public class LocalAuthServiceImpl implements LocalAuthService {
	//自动注入
	@Autowired
	private LocalAuthDao localAuthDao;
	@Autowired
	private PersonInfoDao personInfoDao;
	
	@Override
	@Transactional
	public LocalAuthExecution register(LocalAuth localAuth, CommonsMultipartFile profileImg) throws RuntimeException {
		// TODO Auto-generated method stub
		//用户注册 传入用户对象和头像图片
		//非空判断
		if (localAuth == null || localAuth.getPassword() == null
				|| localAuth.getUserName() == null) {
			return new LocalAuthExecution(LocalAuthStateEnum.NULL_AUTH_INFO);
		}
		try {
			//新建用户业务
			localAuth.setCreateTime(new Date());
			localAuth.setLastEditTime(new Date());
			//加密用户密码
			localAuth.setPassword(MD5.getMd5(localAuth.getPassword()));
			
			if (localAuth.getPersonInfo() != null
					&& localAuth.getPersonInfo().getUserId() == null) {
				if (profileImg != null) {
					localAuth.getPersonInfo().setCreateTime(new Date());
					localAuth.getPersonInfo().setLastEditTime(new Date());
					localAuth.getPersonInfo().setEnableStatus(1);
					try {
						addProfileImg(localAuth, profileImg);
					} catch (Exception e) {
						throw new RuntimeException("addUserProfileImg error: "
								+ e.getMessage());
					}
				}
				try {
					PersonInfo personInfo = localAuth.getPersonInfo();
					personInfo.setAdminFlag(0);
					personInfo.setCustomerFlag(1);
					int effectedNum = personInfoDao
							.insertPersonInfo(personInfo);
					localAuth.setUserId(personInfo.getUserId());
					if (effectedNum <= 0) {
						throw new RuntimeException("添加用户信息失败");
					}
				} catch (Exception e) {
					throw new RuntimeException("insertPersonInfo error: "
							+ e.getMessage());
				}
			}
			int effectedNum = localAuthDao.insertLocalAuth(localAuth);
			if (effectedNum <= 0) {
				throw new RuntimeException("帐号创建失败");
			} else {
				return new LocalAuthExecution(LocalAuthStateEnum.SUCCESS,
						localAuth);
			}
		} catch (Exception e) {
			throw new RuntimeException("insertLocalAuth error: "
					+ e.getMessage());
		}
		
	
	}
	
	//添加头像图片
	private void addProfileImg(LocalAuth localAuth,
			CommonsMultipartFile profileImg) {
		String dest = FileUtil.getPersonInfoImagePath();
		String profileImgAddr = ImageUtil.generateThumbnail(profileImg, dest);
		localAuth.getPersonInfo().setProfileImg(profileImgAddr);
	}

	@Override //查詢不用事務控制
	public LocalAuth getLocalAuthByUserNameAndPwd(String userName, String password) {
		// TODO Auto-generated method stub
		
		return localAuthDao.getLocalAuthByUserNameAndPwd(userName, password);
	}

}

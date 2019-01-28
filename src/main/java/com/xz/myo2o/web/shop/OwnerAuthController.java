package com.xz.myo2o.web.shop;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xz.myo2o.dto.LocalAuthExecution;
import com.xz.myo2o.entity.LocalAuth;
import com.xz.myo2o.entity.PersonInfo;
import com.xz.myo2o.enums.LocalAuthStateEnum;
import com.xz.myo2o.service.LocalAuthService;
import com.xz.myo2o.util.CodeUtil;
import com.xz.myo2o.util.HttpServletRequestUtil;
import com.xz.myo2o.util.MD5;



/**
 * @author 作者
 * @version 创建时间：2019年1月15日 上午9:33:28 类说明 用户相关
 */

@RequestMapping("/shop")
@Controller
public class OwnerAuthController {
	
	private final static Logger logger = LoggerFactory.getLogger(OwnerAuthController.class);
	
	@Autowired
	private LocalAuthService localAuthService;
	
	
	@RequestMapping(value = "/ownerlogincheck", method = RequestMethod.POST)
	@ResponseBody
	private Map<String, Object> ownerlogincheck(HttpServletRequest request) {
		System.out.println("begin");
		Map<String, Object> modelMap = new HashMap<String, Object>();
		boolean needVerify =HttpServletRequestUtil.getBoolean(request, "needVerify");
		//判断验证码
		if (needVerify && !CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "输入了错误的验证码");
			return modelMap;
		}
		
		//获取到ajax的用户名和密码
		String userName = HttpServletRequestUtil.getString(request, "userName");
		String password = HttpServletRequestUtil.getString(request, "password");
		logger.debug("Username:"+userName+" psw:"+password);
		
		if(userName != null && password != null) {
			//获取前端的密码转换成md5密码
			password = MD5.getMd5(password);
			logger.debug("md5ps:"+password);
			//根据用户名和秘密查询数据库进行对比
			LocalAuth localAuth = localAuthService.getLocalAuthByUserNameAndPwd(userName, password);//这两个参数要传到service处进行dao操作的。所以service要接收这两个参数
			if(localAuth != null) {
				//判断用户是否是管理员
				if(localAuth.getPersonInfo().getShopOwnerFlag()==1) {
					modelMap.put("success", true);
					request.getSession().setAttribute("user", localAuth.getPersonInfo());
				}else {
					modelMap.put("success", false);
					modelMap.put("errMsg", "非管理员，没有权限访问");
				}
			}else {
				modelMap.put("success", false);
				modelMap.put("errMsg", "用户名或密码错误");
			}
		}else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "用户名和秘密均不能为空");
		}
		
		
		return modelMap;
	}
	
	@RequestMapping("/ownerregister")
	@ResponseBody
	private Map<String, Object> ownerRegister(HttpServletRequest request) {
		Map<String, Object> modelMap = new HashMap<String, Object>();
		//先检查验证码是否正确
		if (!CodeUtil.checkVerifyCode(request)) {
			modelMap.put("success", false);
			modelMap.put("errMsg", "输入了错误的验证码");
			return modelMap;
		}
		//jackson的对象，可以对象和json互相转换
		ObjectMapper mapper = new ObjectMapper();
		
		LocalAuth localAuth = null;//先定义一个空的用户对象
		
		//	formData.append('localAuthStr', JSON.stringify(localAuth));
		String localAuthStr = HttpServletRequestUtil.getString(request, "localAuthStr");
		
		MultipartHttpServletRequest multipartRequest = null;//上传的文件
		CommonsMultipartFile profileImg = null;
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if (multipartResolver.isMultipart(request)) {
			multipartRequest = (MultipartHttpServletRequest) request;
			profileImg = (CommonsMultipartFile) multipartRequest.getFile("thumbnail");
		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "上传图片不能为空");
			return modelMap;
		}
		try {
			localAuth = mapper.readValue(localAuthStr, LocalAuth.class);
		} catch (Exception e) {
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
			return modelMap;
		}
		if (localAuth != null && localAuth.getPassword() != null && localAuth.getUserName() != null) {
			try {
				PersonInfo user = (PersonInfo) request.getSession().getAttribute("user");
				if (user != null && localAuth.getPersonInfo() != null) {
					localAuth.getPersonInfo().setUserId(user.getUserId());
				}
				localAuth.getPersonInfo().setShopOwnerFlag(1);
				localAuth.getPersonInfo().setAdminFlag(0);
				LocalAuthExecution le = localAuthService.register(localAuth, profileImg);
				if (le.getState() == LocalAuthStateEnum.SUCCESS.getState()) {
					modelMap.put("success", true);
				} else {
					modelMap.put("success", false);
					modelMap.put("errMsg", le.getStateInfo());
				}
			} catch (RuntimeException e) {
				modelMap.put("success", false);
				modelMap.put("errMsg", e.toString());
				return modelMap;
			}

		} else {
			modelMap.put("success", false);
			modelMap.put("errMsg", "请输入注册信息");
		}
		return modelMap;
	}
}

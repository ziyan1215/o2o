package com.xz.myo2o.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @author 作者
 * @version 创建时间：2019年1月15日 上午11:42:01 类说明：检查验证码是否正确
 */
public class CodeUtil {
	public static boolean checkVerifyCode(HttpServletRequest request) {
		String verifyCodeExpected = (String) request.getSession()
				.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		String verifyCodeActual = HttpServletRequestUtil.getString(request, "verifyCodeActual");
		if (verifyCodeActual == null || !verifyCodeActual.equalsIgnoreCase(verifyCodeExpected)) {
			return false;
		}
		return true;
	}
}

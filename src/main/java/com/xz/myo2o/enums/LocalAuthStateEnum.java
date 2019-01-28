package com.xz.myo2o.enums;

/**
 * @author 作者
 * @version 创建时间：2019年1月15日 上午11:12:14 类说明
 */
public enum LocalAuthStateEnum {
	LOGINFAIL(-1, "密码或帐号输入有误"),
	SUCCESS(0, "操作成功"),
	NULL_AUTH_INFO(-1006, "注册信息为空"),
	ONLY_ONE_ACCOUNT(-1007, "最多只能绑定一个本地帐号");

	private int state;

	private String stateInfo;

	private LocalAuthStateEnum(int state, String stateInfo) {
		this.state = state;
		this.stateInfo = stateInfo;
	}

	public int getState() {
		return state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public static LocalAuthStateEnum stateOf(int index) {
		for (LocalAuthStateEnum state : values()) {
			if (state.getState() == index) {
				return state;
			}
		}
		return null;
	}
}

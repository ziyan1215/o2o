package com.xz.myo2o.web.shop;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
* @author 作者
* @version 创建时间：2019年1月22日 下午4:45:04
* 类说明
*/
@Controller
@RequestMapping("/shop")
public class ShopManageController {
	@RequestMapping("/shoplist")
	public Map<String,Object> index() {
		return null;
	}
}

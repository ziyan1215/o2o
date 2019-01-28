package com.xz.myo2o.web.shop;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/shop")
public class AdminController {
	
	@RequestMapping(value = "/register", method = RequestMethod.GET)
	private String register() {
		return "shop/register";
	}
	
	@RequestMapping(value = "/ownerlogin", method = RequestMethod.GET)
	public String ownerLogin(HttpServletRequest request) {
		return "shop/ownerlogin";
	}
	
	@RequestMapping(value = "/shopedit", method = RequestMethod.GET)
	private String shopEdit() {
		return "shop/shopedit";
	}
	
	@RequestMapping(value = "/shopmanage", method = RequestMethod.GET)
	private String shopmanage() {
		return "shop/shopmanage";
	}
	
	@RequestMapping(value = "/shoplist", method = RequestMethod.GET)
	private String shoplist() {
		return "shop/shoplist";
	}
	
}

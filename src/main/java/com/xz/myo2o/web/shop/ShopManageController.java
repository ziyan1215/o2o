package com.xz.myo2o.web.shop;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.xz.myo2o.dto.ShopExecution;
import com.xz.myo2o.entity.PersonInfo;
import com.xz.myo2o.entity.Shop;
import com.xz.myo2o.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
* @author 作者
* @version 创建时间：2019年1月22日 下午4:45:04
* 类说明
*/
@Controller
@RequestMapping("/shop")
public class ShopManageController {

	@Autowired
	private ShopService shopService;

	@RequestMapping(value="/list",method = RequestMethod.GET)
    @ResponseBody
	private Map<String,Object> list(HttpServletRequest request) {
		Map<String,Object> modelMap = new HashMap<String,Object>();
		PersonInfo user =(PersonInfo)request.getSession().getAttribute("user");
		long employeeId =user.getUserId();

		List<Shop> list=new ArrayList();
		try{
			ShopExecution shopExecution=shopService.getByEmployeeId(employeeId);
			list =shopExecution.getShopList();
			modelMap.put("shopList",list);
			modelMap.put("user",user);
			modelMap.put("success",true);
			request.getSession().setAttribute("'shopList",list);
		}catch(Exception e){
			e.printStackTrace();
			modelMap.put("success",false);
			modelMap.put("errMsg",e.toString());
		}
		return modelMap;
	}

	@RequestMapping(value="/shopadmin",method = RequestMethod.GET)
	public String shopmanage(){
		return "superadmin/shopmanage";
	}




}

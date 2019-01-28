package com.xz.myo2o.web.superadmin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xz.myo2o.entity.Area;
import com.xz.myo2o.entity.ConstantForSuperAdmin;
import com.xz.myo2o.service.AreaService;

/**
 * 超级管理员相关的接口
 * 
 * @author 作者
 * @version 创建时间：2019年1月21日 下午3:02:11 类说明
 */
@Controller
@RequestMapping("/superadmin")
public class AreaController {
	@Autowired
	AreaService areaService;

	/**
	 * used: 查询区域列表 last update time : 2019年1月21日下午3:17:26 return
	 * :Map<String,Object>
	 * 
	 * @return 区域列表
	 */
	@RequestMapping(value = "/listarea", method = RequestMethod.POST)
	@ResponseBody
	public Map<String, Object> listarea() {
		// 创建一个modelMap对象
		Map<String, Object> modelMap = new HashMap<String, Object>();
		List<Area> list = new ArrayList<Area>();
		// 查询数据库
		try {
			list = areaService.queryArea();
			// rows [{},{}]
			modelMap.put(ConstantForSuperAdmin.PAGE_SIZE, list);
			// total
			modelMap.put(ConstantForSuperAdmin.TOTAL, list.size());

		} catch (Exception e) {
			e.printStackTrace();
			modelMap.put("success", false);
			modelMap.put("errMsg", e.toString());
		}
		return modelMap;
		// 将值存到modelMap中

	}

}

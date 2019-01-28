package com.xz.myo2o.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xz.myo2o.BaseTest;
import com.xz.myo2o.entity.Area;
import com.xz.myo2o.service.impl.AreaServiceImpl;

/**
* @author 作者
* @version 创建时间：2018年12月6日 下午4:31:19
* 类说明
*/
public class AreaServiceTest extends BaseTest {
	
	@Autowired
	private AreaService areaService;
	@Test
	public void test() {
		
				Area a = new Area();
				a.setAreaName("testArea");
				a.setPriority(0);
				
				areaService.addArea(a);
	}

}

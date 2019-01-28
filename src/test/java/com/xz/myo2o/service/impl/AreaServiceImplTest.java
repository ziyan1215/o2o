package com.xz.myo2o.service.impl;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xz.myo2o.BaseTest;
import com.xz.myo2o.entity.Area;
import com.xz.myo2o.service.AreaService;

/**
* @author 作者
* @version 创建时间：2019年1月21日 下午2:51:53
* 类说明
*/
public class AreaServiceImplTest extends BaseTest{

	@Autowired AreaService areaService;
	@Test
	public void test() {
		
		List<Area> areaList= new ArrayList<>();
		areaList=areaService.queryArea();
		
		System.out.println(areaList.size());
	}

}

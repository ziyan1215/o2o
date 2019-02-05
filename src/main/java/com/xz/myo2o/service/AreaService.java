package com.xz.myo2o.service;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

import com.xz.myo2o.dto.AreaExecution;
import com.xz.myo2o.entity.Area;

public interface AreaService {

	/**
	 * 
	 * @param area
	 * @return
	 */
	//Integer addAreaInt(Area area);
	
	/**
	 * used: 查询区域列表
	 * last update time : 2019年1月21日下午2:50:34
	 * return :List<Area>
	 * @return
	 */
	//List<Area> queryArea();

	//2019年2月3日 00:23:22，重构一下区域的服务接口

	List<Area> getAreaList() throws JsonParseException,JsonMappingException,IOException;

	AreaExecution addArea(Area area);

	AreaExecution modifArea(Area area0);

	AreaExecution removeArea(long aareaaId);

	AreaExecution removeAreaList(List<Long> areaIdList);






}

package com.xz.myo2o.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xz.myo2o.cache.JedisUtil;
import com.xz.myo2o.dto.AreaExecution;
import com.xz.myo2o.enums.AreaStateEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xz.myo2o.dao.AreaDao;
import com.xz.myo2o.entity.Area;
import com.xz.myo2o.service.AreaService;
import org.springframework.transaction.annotation.Transactional;

/**
* @author 作者
* @version 创建时间：2018年12月6日 下午4:17:14
* 类说明
*/
@Service
public class AreaServiceImpl implements AreaService {
	@Autowired
	private JedisUtil.Strings jedisStrings;
	@Autowired
	private JedisUtil.Keys jedisKeys;
	@Autowired
	private AreaDao areaDao;

	private static String AREALISTKEY = "arealist";//这个key存的就是区域下拉数据json对象
	////////////////////////////////////////////////////////////////////
	//2019年2月3日 14:28:01

	@Override
	public List<Area> getAreaList() throws JsonParseException, JsonMappingException, IOException {
		//定义接收查询条件
		String key = AREALISTKEY;
		List<Area> areaList = null;
		ObjectMapper mapper = new ObjectMapper();
		if (!jedisKeys.exists(key)) {
			areaList = areaDao.queryArea();
			String jsonString = mapper.writeValueAsString(areaList);
			jedisStrings.set(key, jsonString);
		} else {
			String jsonString = jedisStrings.get(key);
			JavaType javaType = mapper.getTypeFactory()
					.constructParametricType(ArrayList.class, Area.class);
			areaList = mapper.readValue(jsonString, javaType);
		}
		return areaList;
	}

	@Override
	@Transactional//加入事务控制
	public AreaExecution addArea(Area area) {
		//非空判断
		if(area.getAreaName()!=null&& ! "".equals(area.getAreaName())){
			area.setCreateTime(new Date());
			area.setLastEditTime(new Date());
			try{
				int effectNum = areaDao.insertArea(area);
				if (effectNum>0){
					///删除redis缓存
					String key = AREALISTKEY;
					if(jedisKeys.exists(key)){
						jedisKeys.del(key);
					}
					return new AreaExecution(AreaStateEnum.SUCCESS,area);
				}else{
					return new AreaExecution((AreaStateEnum.INNER_ERROR));
				}
			}catch (Exception e){
				throw new RuntimeException("添加区域信息失败："+e.toString());
			}
		}else{
			return new AreaExecution(AreaStateEnum.EMPTY);
		}
	}

	@Override
	@Transactional
	public AreaExecution modifArea(Area area) {
		//非空判断
		if(area.getAreaId()!=null && area.getAreaId()>0){
			area.setLastEditTime(new Date());
			try{
				int effectNum = areaDao.updateArea(area);
				if (effectNum>0){
					String key = AREALISTKEY;
					if(jedisKeys.exists(key)){
						jedisKeys.del(key);
					}
					return new AreaExecution(AreaStateEnum.SUCCESS,area);
				}else{
					return  new AreaExecution(AreaStateEnum.INNER_ERROR);
				}
			}catch(Exception e){
				throw  new RuntimeException("更新区域信息失败："+e.toString());
			}
		}else{
			return  new AreaExecution(AreaStateEnum.EMPTY);
		}
	}

	@Override
	@Transactional
	public AreaExecution removeArea(long areaId) {
		if (areaId>0){
			try{
				int effectNum = areaDao.deleteArea(areaId);
				if (effectNum>0){
					String key = AREALISTKEY;
					if(jedisKeys.exists(key)){
						jedisKeys.del(key);
					}
					return new AreaExecution(AreaStateEnum.SUCCESS);
				}else{
					return  new AreaExecution(AreaStateEnum.INNER_ERROR);
				}
			}catch(Exception e){
				throw  new RuntimeException("删除区域信息失败："+e.toString());
			}
		}else{
			return  new AreaExecution(AreaStateEnum.EMPTY);
		}
	}

	@Override
	@Transactional
	public AreaExecution removeAreaList(List<Long> areaIdList) {

		if (areaIdList != null && areaIdList.size() > 0) {
			try {
				int effectedNum = areaDao.batchDeleteArea(areaIdList);
				if (effectedNum > 0) {
					String key = AREALISTKEY;
					if (jedisKeys.exists(key)) {
						jedisKeys.del(key);
					}
					return new AreaExecution(AreaStateEnum.SUCCESS);
				} else {
					return new AreaExecution(AreaStateEnum.INNER_ERROR);
				}
			} catch (Exception e) {
				throw new RuntimeException("删除区域信息失败:" + e.toString());
			}
		} else {
			return new AreaExecution(AreaStateEnum.EMPTY);
		}
	}


	////////////////////////////////////////////////////////////////////
	/*@Override
	public Integer addAreaInt(Area area) {
		// TODO Auto-generated method stub
		
		System.out.println(areaDao.addArea(area));
		
		return 1;
	}


	@Override
	public List<Area> queryArea() {
		// TODO Auto-generated method stub
		return areaDao.queryArea();
	}*/

}

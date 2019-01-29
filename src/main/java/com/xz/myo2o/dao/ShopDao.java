package com.xz.myo2o.dao;

import com.xz.myo2o.entity.Shop;

import java.util.List;

/**
* @author 作者
* @version 创建时间：2018年12月18日 下午2:22:17
* 类说明
*/
public interface ShopDao {
	//新增店铺
	int insertShop(Shop shop);
	//更新店铺信息
	int updateShop(Shop shop);
	//通过店员id查询店铺信息
	List<Shop> queryByEmployeeId(long employeeId);
	//通过owner id来查询店铺信息
	Shop queryByshopId(long shopId);
	//删除店铺信息
	int deleteShopByname(String shopName);



}

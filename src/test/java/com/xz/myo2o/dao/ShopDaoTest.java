package com.xz.myo2o.dao;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.xz.myo2o.BaseTest;
import com.xz.myo2o.entity.Area;
import com.xz.myo2o.entity.Shop;
import com.xz.myo2o.entity.ShopCategory;

/**
* @author 作者
* @version 创建时间：2018年12月18日 下午2:36:51
* 类说明
*/
public class ShopDaoTest extends BaseTest{
	@Autowired
	private ShopDao shopDao;
	@Autowired
	private ShopCategoryDao sha;
	@Autowired
	private AreaDao areaDao;
	@Test
	public void test() {
		Shop shop = new Shop();
		shop.setOwnerId(8L);
		//Area area = new Area();
		//area.setAreaId(1L);
		//ShopCategory sc = new ShopCategory();
		//sc.setShopCategoryId(1L);
		shop.setShopName("mytest21");
		shop.setShopDesc("mytest21");
		shop.setShopAddr("testaddr1");
		shop.setPhone("13810524526");
		shop.setShopImg("test1");
		shop.setLongitude(1D);
		shop.setLatitude(1D);
		shop.setCreateTime(new Date());
		shop.setLastEditTime(new Date());
		shop.setEnableStatus(0);
		shop.setAdvice("审核中");
		//shop.setArea(area);
		//shop.setShopCategory(sc);
		//System.out.println(sc);
		//sha.insertShopCategory(sc);
		//areaDao.addArea(area);
		int effectedNum = shopDao.insertShop(shop);
		System.out.println(effectedNum);
	}

	@Test
	public void queryByOwnerIdTest() {
		Shop shop ;
		shop=shopDao.queryByshopId(15L);
		System.out.println(shop.toString());
	}

	@Test
	public void queryByemployeeIdTest() {
		List<Shop> shopList;
		shopList=shopDao.queryByEmployeeId(8L);
		System.out.println(shopList.size());
	}

	@Test
	public void updateShopTest() {
		Shop shop = new Shop();
		shop.setShopId(28L);
		int effNum;
		shop.setLastEditTime(new Date());
		effNum=shopDao.updateShop(shop);
		System.out.println(effNum);
	}

	@Test
	public void deleteShopBynameTest() {
		int effNum;
		effNum=shopDao.deleteShopByname("test");
		System.out.println(effNum);
	}
}

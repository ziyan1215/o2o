package com.xz.myo2o.service.impl;

import java.util.Date;

import com.xz.myo2o.dto.ShopExecution;
import com.xz.myo2o.entity.Shop;
import com.xz.myo2o.service.ShopService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
* @author 作者
* @version 创建时间：2019年1月14日 下午4:14:28
* 类说明
*/
@Service
public class ShopServiceImpl implements ShopService {
	@Override
	public int addshop(Shop shop) {
		return 0;
	}

	@Override
	public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
		return null;
	}

	@Override
	public ShopExecution getByEmployeeId(long employeeId) throws RuntimeException {
		return null;
	}

	@Override
	public Shop getByShopId(long shopId) {
		return null;
	}

	@Override
	public ShopExecution addShopMain(Shop shop, CommonsMultipartFile shopImg) throws RuntimeException {
		return null;
	}

	@Override
	public ShopExecution modifyShop(Shop shop, CommonsMultipartFile shopImg) throws RuntimeException {
		return null;
	}
}

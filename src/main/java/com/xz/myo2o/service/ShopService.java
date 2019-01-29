package com.xz.myo2o.service;

import com.xz.myo2o.dto.ShopExecution;
import com.xz.myo2o.entity.Area;
import com.xz.myo2o.entity.Shop;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
* @author 作者
* @version 创建时间：2018年12月6日 下午4:07:58
* 类说明
*/
public interface ShopService {
	

	/**
	 * used: 创建店铺
	 * last update time : 2019年1月14日下午4:09:38
	 * return :int
	 * @return
	 */
	 int addshop(Shop shop);


	ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize);

	/**
	 * 查询该用户下面的店铺信息
	 *
	 * @param long
	 *            employyeeId
	 * @return List<Shop>
	 * @throws Exception
	 */
	ShopExecution getByEmployeeId(long employeeId) throws RuntimeException;

	/**
	 * 查询指定店铺信息
	 *
	 * @param long
	 *            shopId
	 * @return Shop shop
	 */
	Shop getByShopId(long shopId);

	/**
	 * 创建商铺
	 *
	 * @param Shop
	 *            shop
	 * @return ShopExecution shopExecution
	 * @throws Exception
	 */
	ShopExecution addShopMain(Shop shop, CommonsMultipartFile shopImg) throws RuntimeException;

	/**
	 * 更新店铺信息（从店家角度）
	 *
	 * @param areaId
	 * @param shopAddr
	 * @param phone
	 * @param shopImg
	 * @param shopDesc
	 * @return
	 * @throws RuntimeException
	 */
	ShopExecution modifyShop(Shop shop, CommonsMultipartFile shopImg) throws RuntimeException;

}

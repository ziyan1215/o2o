package com.xz.myo2o.service.impl;

import java.util.Date;
import java.util.List;

import com.xz.myo2o.dao.ShopCategoryDao;
import com.xz.myo2o.dao.ShopDao;
import com.xz.myo2o.dto.ShopExecution;
import com.xz.myo2o.entity.Shop;
import com.xz.myo2o.entity.ShopCategory;
import com.xz.myo2o.enums.ShopStateEnum;
import com.xz.myo2o.service.ShopService;
import com.xz.myo2o.util.FileUtil;
import com.xz.myo2o.util.ImageUtil;
import com.xz.myo2o.util.PageCalculator;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

/**
* @author 作者
* @version 创建时间：2019年1月14日 下午4:14:28
* 类说明
*/
@Service
public class ShopServiceImpl implements ShopService {

	@Autowired
	private ShopDao shopDao;
	@Autowired
	private ShopCategoryDao shopCategoryDao;


	@Override
	public int addshop(Shop shop) {
		return 0;
	}

	@Override
	public ShopExecution getShopList(Shop shopCondition, int pageIndex, int pageSize) {
		//根据传入的条件来查询，将结果分页返回前端
		//limit rowindex pagesize 所以rowindex应该是每页第一行的时候属于第几行
		int rowIndex = PageCalculator.calculateRowIndex(pageIndex,pageSize);
		List<Shop> shopList =shopDao.queryShopList(shopCondition,rowIndex,pageSize);
		int count =shopDao.queryShopCount(shopCondition);
		//将结果传给前端
		ShopExecution se =new ShopExecution();
		if(shopList != null){
			se.setShopList(shopList);
			se.setCount(count);
		}else{
			se.setState(ShopStateEnum.INNER_ERROR.getState());
		}
		return se;
	}

	@Override
	public ShopExecution getByEmployeeId(long employeeId) throws RuntimeException {
		List<Shop> shopList =shopDao.queryByEmployeeId(employeeId);
		ShopExecution se = new ShopExecution();
		se.setShopList(shopList);
		return se;
	}

	@Override
	public Shop getByShopId(long shopId) {
		return shopDao.queryByshopId(shopId);
	}

	@Override
	@Transactional
	/**
	 * 使用注解控制事务方法的优点：
	 * 1.开发团队达成一致约定，明确标注事务方法的编程风格
	 * 2.保证事务方法的执行时间尽可能短，不要穿插其他网络操作，RPC/HTTP请求或者剥离到事务方法外部
	 * 3.不是所有的方法都需要事务，如只有一条修改操作，只读操作不需要事务控制
	 */
	public ShopExecution addShopMain(Shop shop, CommonsMultipartFile shopImg) throws RuntimeException {
		if (shop == null) {
			return new ShopExecution(ShopStateEnum.NULL_SHOP_INFO);

		}
		try {
			shop.setEnableStatus(0);
			shop.setCreateTime(new Date());
			shop.setLastEditTime(new Date());
			// 店铺种类
			if (shop.getShopCategory() != null) {
				Long shopCategoryId = shop.getShopCategory().getShopCategoryId();
				ShopCategory sc = new ShopCategory();
				sc = shopCategoryDao.queryShopCategoryById(shopCategoryId);
				ShopCategory parentCategory = new ShopCategory();
				parentCategory.setShopCategoryId(sc.getParentId());
				shop.setParentCategory(parentCategory);
			}
			int effectNum = shopDao.insertShop(shop);
			if (effectNum <= 0) {
				throw new RuntimeException("创建店铺失败");
			} else {
				try {
					if (shopImg != null) {
						addShopImg(shop, shopImg);
						effectNum = shopDao.updateShop(shop);
						if (effectNum <= 0) {
							throw new RuntimeException("创建图片地址失败");
						}
					}
				} catch (Exception e) {
					throw new RuntimeException("addshopImg error" + e.getMessage());

				}
			}
		}catch (Exception e){
			throw new RuntimeException("insertShop error:"+e.getMessage());
		}

		return null;
	}

	@Override
	@Transactional
	public ShopExecution modifyShop(Shop shop, CommonsMultipartFile shopImg) throws RuntimeException {
		//判断商店为空或者shopid为空
		if (shop== null || shop.getShopId()==null){
			return  new ShopExecution(ShopStateEnum.NULL_SHOPID);
		}else{
			try{
				if (shopImg!=null){

					Shop tempShop =shopDao.queryByshopId(shop.getShopId());
					//假如原先的shop有图片 就删除
					if (tempShop.getShopImg()!=null){
						FileUtil.deleteFile(tempShop.getShopImg());
					}
					addShopImg(shop,shopImg);
				}
				shop.setLastEditTime(new Date());
				int effectNum =shopDao.updateShop(shop);
				if (effectNum<=0){
					return new ShopExecution(ShopStateEnum.INNER_ERROR);
				}else{
					shop=shopDao.queryByshopId(shop.getShopId());
					return new ShopExecution(ShopStateEnum.SUCCESS,shop);
				}
			}catch(Exception e){
				throw new RuntimeException("modifyShop error:"+e.getMessage());
			}
		}


	}

	private void addShopImg(Shop shop,CommonsMultipartFile shopImg){

		String dest = FileUtil.getShopImagePath(shop.getShopId());

		String shopImgAddr = ImageUtil.generateThumbnail(shopImg,dest);

		shop.setShopImg(shopImgAddr);
	}
}

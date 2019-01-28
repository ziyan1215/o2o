package com.xz.myo2o.dto;

import java.util.List;

import com.xz.myo2o.entity.Shop;
import com.xz.myo2o.enums.ShopStateEnum;
 
/**
 * @author xuziyan
 * 封装执行后结果
 *
 */
public class ShopExecution {
	//结果状态
	private int state;
	
	//状态标识
	private String stateInfo;
	
	//店铺数量
	private int count;
	
	//操作的shop(增删改的时候用)
	private Shop shop;
	
	//获取查询的shop列表
	private List<Shop> shopList;
	
	//构造函数
	public ShopExecution() {
		
	};
	
	//操作成功时的构造函数
    public ShopExecution(ShopStateEnum stateEnum,Shop shop) {
			this.state=stateEnum.getState();
			this.stateInfo=stateEnum.getStateInfo();
			this.shop=shop;
		};
		
		//操作成功时的构造函数
	    public ShopExecution(ShopStateEnum stateEnum,List<Shop> shopList) {
				this.state=stateEnum.getState();
				this.stateInfo=stateEnum.getStateInfo();
				this.shopList=shopList;
			}

		public int getState() {
			return state;
		}

		public void setState(int state) {
			this.state = state;
		}

		public String getStateInfo() {
			return stateInfo;
		}

		public void setStateInfo(String stateInfo) {
			this.stateInfo = stateInfo;
		}

		public int getCount() {
			return count;
		}

		public void setCount(int count) {
			this.count = count;
		}

		public Shop getShop() {
			return shop;
		}

		public void setShop(Shop shop) {
			this.shop = shop;
		}

		public List<Shop> getShopList() {
			return shopList;
		}

		public void setShopList(List<Shop> shopList) {
			this.shopList = shopList;
		};
	
}

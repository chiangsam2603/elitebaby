package com.tibame.web.vo;

public class MealOrderDetailVO {


	private Integer mealOrderDetailId;
	private Integer mealOrderId;
	private Integer mealId;
	private Integer orderCount;
	private Integer mealPrice;
	private Integer userId;

	public MealOrderDetailVO() {
		
	}
	
	public MealOrderDetailVO(Integer userId, Integer mealId, Integer orderCount) {
		super();
		this.userId = userId;
		this.mealId = mealId;
		this.orderCount = orderCount;
	}

	@Override
	public String toString() {
		return "MealOrderDetailVO [mealOrderDetailId=" + mealOrderDetailId + ", mealOrderId=" + mealOrderId
				+ ", mealId=" + mealId + ", orderCount=" + orderCount + ", mealPrice=" + mealPrice + ", userId="
				+ userId + "]";
	}

	public Integer getMealOrderDetailId() {
		return mealOrderDetailId;
	}

	public void setMealOrderDetailId(Integer mealOrderDetailId) {
		this.mealOrderDetailId = mealOrderDetailId;
	}

	public Integer getMealOrderId() {
		return mealOrderId;
	}

	public void setMealOrderId(Integer mealOrderId) {
		this.mealOrderId = mealOrderId;
	}

	public Integer getMealId() {
		return mealId;
	}

	public void setMealId(Integer mealId) {
		this.mealId = mealId;
	}

	public Integer getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(Integer orderCount) {
		this.orderCount = orderCount;
	}

	public Integer getMealPrice() {
		return mealPrice;
	}
	
	public void setMealPrice(Integer mealPrice) {
		this.mealPrice = mealPrice;
	}
	
	public Integer getUserId() {
		return userId;
	}
	
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
}

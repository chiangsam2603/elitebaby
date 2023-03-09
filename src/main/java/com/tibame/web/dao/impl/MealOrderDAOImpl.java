package com.tibame.web.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.tibame.web.dao.MealOrderDAO;
import com.tibame.web.vo.MealOrderVO;

public class MealOrderDAOImpl implements MealOrderDAO {

	private DataSource ds;

	public MealOrderDAOImpl() {
		try {
			ds = (DataSource) new InitialContext().lookup("java:/comp/env/jdbc/example");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	@Override
	public int insert(MealOrderVO MealOrderVO) {
		String sql = "insert into MEAL_ORDER(USER_ID, ORDER_PAYMENT, ORDER_NOTES) values (?,?,?);";

		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, MealOrderVO.getUserId());
			ps.setInt(2, MealOrderVO.getOrderPayment());
			ps.setString(3, MealOrderVO.getOrderNotes());
			return ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public int update(MealOrderVO MealOrderVO) {
		String sql = "UPDATE MEAL_ORDER set ORDER_PAYMENT=?, ORDER_STATUS=?, ORDER_NOTES=? where MEAL_ORDER_ID = ?";

		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, MealOrderVO.getOrderPayment());
			ps.setInt(2, MealOrderVO.getOrderStatus());
			ps.setString(3, MealOrderVO.getOrderNotes());
			ps.setInt(4, MealOrderVO.getMealOrderId());
			return ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;

	}

	@Override
	public int delete(Integer mealOrderId) {
		String sql = "delete from MEAL_ORDER where MEAL_ORDER_ID = ?;";

		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
			ps.setInt(1, mealOrderId);
			return ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return -1;
	}

	@Override
	public MealOrderVO findByPrimaryKey(Integer id) {
		String sql = "SELECT * FROM MEAL_ORDER WHERE MEAL_ORDER_ID = ?;";

		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			ps.setInt(1, id);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					Integer mealOrderId = rs.getInt(1);
					Integer userId = rs.getInt(2);
					Integer orderPayment = rs.getInt(3);
					Integer orderStatus = rs.getInt(4);
					Timestamp timestamp = rs.getTimestamp(5);
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String orderDate = dateFormat.format(timestamp);
					String orderNotes = rs.getString(6);

					MealOrderVO mealorder = new MealOrderVO();
					mealorder.setMealOrderId(mealOrderId);
					mealorder.setUserId(userId);
					mealorder.setOrderPayment(orderPayment);
//					mealorder.setStrPayment(String.valueOf(orderPayment));
					mealorder.setOrderStatus(orderStatus);
					mealorder.setOrderDate(orderDate);
					mealorder.setOrderNotes(orderNotes);
					System.out.println(mealorder);
					return mealorder;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<MealOrderVO> getAll() {
		String sql = "SELECT * FROM MEAL_ORDER;";
		List<MealOrderVO> list = new ArrayList<MealOrderVO>();

		try (Connection con = ds.getConnection(); PreparedStatement ps = con.prepareStatement(sql);) {
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					MealOrderVO mealOrder = new MealOrderVO();
					Integer mealOrderId = rs.getInt(1);
					Integer userId = rs.getInt(2);
					Integer orderPayment = rs.getInt(3);
					Integer orderStatus = rs.getInt(4);
					Timestamp timestamp = rs.getTimestamp(5);
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String orderDate = dateFormat.format(timestamp);
					String orderNotes = rs.getString(6);
					System.out.println(mealOrderId + ", " + userId + ", " + orderPayment + ", " + orderStatus + ", "
							+ orderDate + ", " + orderNotes);

					mealOrder.setMealOrderId(mealOrderId);
					mealOrder.setUserId(userId);
//					mealOrder.setOrderPayment(orderPayment);
					if (orderPayment == 1) {
						mealOrder.setStrPayment("現金");
					} else if (orderPayment == 2) {
						mealOrder.setStrPayment("信用卡");
					} else {
						mealOrder.setStrPayment("LinePay");
					}
//					mealOrder.setStrPayment(String.valueOf(orderPayment));
					mealOrder.setOrderStatus(orderStatus);
					mealOrder.setOrderDate(orderDate);
					mealOrder.setOrderNotes(orderNotes);
					list.add(mealOrder);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list != null ? list : null;
	}

}

package nz.ac.unitec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import nz.ac.unitec.vo.Calorie;

public class CalorieDAOImpI implements ICalorieDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;

	public CalorieDAOImpI(Connection conn) {
		this.conn = conn;
	}

	@Override
	public boolean UploadCalorie(Calorie calorie) throws Exception {
		boolean flag = false;
		try {
			String sql = "select * from calorie where userID=? and inputDate=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, calorie.getUserID());
			pstmt.setDate(2, calorie.getInputDate());
			ResultSet rSet = pstmt.executeQuery();
			if (rSet.next()) {
				calorie.setCalorieID(rSet.getInt(1));
				int tempCalorie = rSet.getInt(3);
				tempCalorie += calorie.getCalorieAmount();
				String updateSql = "update calorie set calorieAmount = ? where calorieID=?";

				pstmt = conn.prepareStatement(updateSql);
				pstmt.setInt(1, tempCalorie);
				pstmt.setInt(2, calorie.getCalorieID());
				pstmt.executeUpdate();
				flag = true;
			} else {
				String insertSql = "insert into calorie (userID, calorieAmount, inputDate) values (?,?,?)";

				pstmt = conn.prepareStatement(insertSql);
				pstmt.setInt(1, calorie.getUserID());
				pstmt.setInt(2, calorie.getCalorieAmount());
				pstmt.setDate(3, calorie.getInputDate());
				pstmt.executeUpdate();
				flag = true;
			}
			rSet.close();

		} catch (Exception e) {
			throw e;
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
					throw e;
				}
			}
		}
		return flag;
	}

	@Override
	public List<Calorie> GetCalorie(Calorie calorie) throws Exception {

		Calorie returnCalorie = new Calorie();
		List<Calorie> calorieList = new ArrayList<Calorie>();

		try {
			for (int i = 0; i < 7; i++) {
				returnCalorie = new Calorie();
				Calendar cNow = Calendar.getInstance();
				int minusi = i * -1;
				cNow.add(Calendar.DATE, minusi);
				java.util.Date dNow = cNow.getTime();
				java.sql.Date sqlDate = new java.sql.Date(dNow.getTime());

				String sql = "select * from calorie where userID=? and inputDate=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, calorie.getUserID());
				pstmt.setDate(2, sqlDate);
				ResultSet rSet = pstmt.executeQuery();
				if (rSet.next()) {
					returnCalorie.setCalorieID(rSet.getInt(1));
					returnCalorie.setUserID(rSet.getInt(2));
					returnCalorie.setCalorieAmount(rSet.getInt(3));
					returnCalorie.setInputDate(rSet.getDate(4));
				} else {
					returnCalorie.setCalorieAmount(0);
					returnCalorie.setInputDate(sqlDate);
				}
				calorieList.add(returnCalorie);
				rSet.close();
			}
		} catch (Exception e) {
			throw e;
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (Exception e) {
					throw e;
				}
			}
		}

		return calorieList;
	}
}

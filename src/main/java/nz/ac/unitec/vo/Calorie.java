package nz.ac.unitec.vo;

import java.sql.Date;

public class Calorie {
	private int calorieID;
	private int userID;
	private int calorieAmount;
	private Date inputDate;
	
	public int getCalorieID() {
		return calorieID;
	}
	public void setCalorieID(int calorieID) {
		this.calorieID = calorieID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getCalorieAmount() {
		return calorieAmount;
	}
	public void setCalorieAmount(int calorieAmount) {
		this.calorieAmount = calorieAmount;
	}
	public Date getInputDate() {
		return inputDate;
	}
	public void setInputDate(Date inputDate) {
		this.inputDate = inputDate;
	}
}

package nz.ac.unitec.dao;

import java.util.List;

import nz.ac.unitec.vo.Calorie;

public interface ICalorieDAO {

	public boolean UploadCalorie(Calorie calorie) throws Exception;
	public List<Calorie> GetCalorie(Calorie calorie) throws Exception;
}

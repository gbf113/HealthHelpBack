package nz.ac.unitec.dao.proxy;

import java.util.List;

import nz.ac.unitec.dao.CalorieDAOImpI;
import nz.ac.unitec.dao.ICalorieDAO;
import nz.ac.unitec.dao.IUserDAO;
import nz.ac.unitec.dao.UserDAOImpI;
import nz.ac.unitec.dbc.DatabaseConnection;
import nz.ac.unitec.vo.Calorie;
import nz.ac.unitec.vo.User;

public class CalorieDAOProxy implements ICalorieDAO {
	private DatabaseConnection dbc = null;
	private ICalorieDAO dao = null;

	public CalorieDAOProxy() {
		try {
			dbc = new DatabaseConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		dao = new CalorieDAOImpI(dbc.getConnection());
	}

	@Override
	public boolean UploadCalorie(Calorie calorie) throws Exception {
		boolean flag = false;
		try {
			flag = dao.UploadCalorie(calorie);
		} catch (Exception e) {
			throw e;
		} finally {
			dbc.close();
		}
		return flag;
	}
	
	@Override
	public List<Calorie> GetCalorie(Calorie calorie) throws Exception {
		List<Calorie> returnCalorieList;
		try {
			returnCalorieList = dao.GetCalorie(calorie);
		} catch (Exception e) {
			throw e;
		} finally {
			dbc.close();
		}
		return returnCalorieList;
	}

}

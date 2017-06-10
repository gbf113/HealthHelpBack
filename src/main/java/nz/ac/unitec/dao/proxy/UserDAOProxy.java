package nz.ac.unitec.dao.proxy;

import nz.ac.unitec.dao.IUserDAO;
import nz.ac.unitec.dao.UserDAOImpI;
import nz.ac.unitec.dbc.DatabaseConnection;
import nz.ac.unitec.vo.User;

public class UserDAOProxy implements IUserDAO {
	private DatabaseConnection dbc = null;
	private IUserDAO dao = null;

	public UserDAOProxy() {
		try {
			dbc = new DatabaseConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		dao = new UserDAOImpI(dbc.getConnection());

	}

	@Override
	public boolean findLogin(User user) throws Exception {
		boolean flag = false;
		try {
			flag = dao.findLogin(user);
		} catch (Exception e) {
			throw e;
		} finally {
			dbc.close();
		}
		return flag;
	}
	
	@Override
	public boolean isUserExist(User user) throws Exception {
		boolean flag = false;
		try {
			flag = dao.isUserExist(user);
		} catch (Exception e) {
			throw e;
		} finally {
			dbc.close();
		}
		return flag;
	}
	
	@Override
	public boolean insertUser(User user) throws Exception {
		boolean flag = false;
		try {
			flag = dao.insertUser(user);
		} catch (Exception e) {
			throw e;
		} finally {
			dbc.close();
		}
		return flag;
	}

}

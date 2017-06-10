package nz.ac.unitec.dao;

import nz.ac.unitec.vo.User;

public interface IUserDAO {

	public boolean findLogin(User user) throws Exception;
	public boolean isUserExist(User user) throws Exception;
	public boolean insertUser(User user) throws Exception;
}

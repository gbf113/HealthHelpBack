package nz.ac.unitec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import nz.ac.unitec.vo.User;

public class UserDAOImpI implements IUserDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;

	public UserDAOImpI(Connection conn) {
		this.conn = conn;
	}

	@Override
	public boolean findLogin(User user) throws Exception {
		boolean flag = false;
		try {
			String sql = "select name from user where name=? and password=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getPassword());
			ResultSet rSet = pstmt.executeQuery();
			if (rSet.next()) {
				user.setName(rSet.getString(1));
				flag = true;
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
		return flag;
	}

	@Override
	public boolean isUserExist(User user) throws Exception {
		boolean flag = false;
		try {
			String sql = "select userid, name from user where name=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getName());
			ResultSet rSet = pstmt.executeQuery();
			if (rSet.next()) {
				user.setUserid(rSet.getInt(1));
				user.setName(rSet.getString(2));
				flag = true;
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
		return flag;
	}

	@Override
	public boolean insertUser(User user) throws Exception {
		boolean flag = false;
		try {
			String sql = "insert into user (name, password) values (?,?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getName());
			pstmt.setString(2, user.getPassword());
			pstmt.executeUpdate();
			flag = true;
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
}

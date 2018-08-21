package pro.sunhao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import pro.sunhao.domain.User;
import pro.sunhao.exception.MsgException;
import pro.sunhao.util.JDBCUtils;

/**
 * UserDao的实现类
 * @author Administrator
 *
 */
public class UserDaoImpl implements UserDao {

	@Override
	public boolean getUserByUsername(String username) {
		boolean flag = false;
		String sql = "select * from user where username = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			rs = ps.executeQuery();
			if(rs.next()) {			// 用户名存在
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(conn, ps, rs);
		}
		return flag;
	}

	@Override
	public boolean insertUser(User user) {
		boolean flag = false;
		String sql = "insert into user value(null, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = JDBCUtils.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getNickname());
			ps.setString(4, user.getEmail());
			int line = ps.executeUpdate();		// 影响行数
			if(line > 0) {
				flag = true;;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(conn, ps, rs);
		}
		return flag;
	}

	@Override
	public User getUserByUsernameAndPassword(String username, String password) throws MsgException {
		String sql = "select * from user where username = ? and password = ?";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		User user = null;
		try {
			conn = JDBCUtils.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			rs = ps.executeQuery();
			if(rs.next()) {				// 查询到结果，登录成功
				user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("nickname"), rs.getString("email"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MsgException("数据库连接异常，稍后重试");
		} finally {
			JDBCUtils.close(conn, ps, rs);
		}
		return user;
	}

}

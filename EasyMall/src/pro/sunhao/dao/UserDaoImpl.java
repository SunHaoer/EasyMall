package pro.sunhao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import pro.sunhao.domain.User;
import pro.sunhao.exception.MsgException;
import pro.sunhao.util.JDBCUtils;
import pro.sunhao.util.TransactionManager;

/**
 * 为用户提供数据库查询的dao
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
	public boolean insertUser(User user) throws MsgException {
		boolean flag = false;
		String sql = "insert into user value(null, ?, ?, ?, ?)";
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = TransactionManager.getConn();
			ps = conn.prepareStatement(sql);
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getNickname());
			ps.setString(4, user.getEmail());
			int line = ps.executeUpdate();		// 影响行数
			if(line > 0) {
				flag = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new MsgException("注册异常");
		} finally {
			JDBCUtils.close(null, ps, rs);
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

	@Override
	public boolean createCart(User user) throws MsgException {
		String sql = "create table " + user.getUsername() + "_cart" + " (id int primary key auto_increment, pid int, pname varchar(100), pnum int)";
		Connection conn = null;
		PreparedStatement ps = null;
		boolean isCreate = false;
		try {										// 这里可能有问题
			conn = TransactionManager.getConn();
			ps = conn.prepareStatement(sql);
			//ps.setString(1, user.getUsername() + "_cart");
			//ps.executeUpdate();
			//boolean isCreate = ps.execute();
			
			//System.out.println(ps.executeUpdate());
			//System.out.println(isCreate);
			int line = ps.executeUpdate();
			if(line == 0) isCreate = true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new MsgException("注册异常");
		} finally {
			JDBCUtils.close(null, ps, null);
		}
		return isCreate;
	}
}

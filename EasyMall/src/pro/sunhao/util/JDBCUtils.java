package pro.sunhao.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 提供数据库操作的工具
 * @author Administrator
 *
 */

public class JDBCUtils {
	private static ComboPooledDataSource ds = new ComboPooledDataSource();
	/**
	 * 通过数据库连接池获取连接的方法
	 * @return 连接成功true 连接失败false
	 */
	public static Connection getConn() {
		//System.out.println("获取连接");
		Connection conn = null;
		try {
			conn = ds.getConnection();		// 通过数据库连接池获取连接对象
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;		// 将获取到的连接返回给用户
	}
	
	public static void close(Connection conn, PreparedStatement ps, ResultSet rs) {
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				conn = null;
			}
		}
		if(ps != null) {
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				ps = null;
			}
		}
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				rs = null;
			}
		}
	}
}

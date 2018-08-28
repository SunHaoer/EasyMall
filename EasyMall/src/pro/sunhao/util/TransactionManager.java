package pro.sunhao.util;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * 为数据库添加事务的工具类
 * @author Administrator
 *
 */
public class TransactionManager {		// 单例
	private static TransactionManager tm = new TransactionManager();
	
	private TransactionManager() {
	}
	
	public static TransactionManager getInstance() {
		return tm;
	}
	
	// 每个线程携带一个Map集合，用于保存本线程使用的变量，程序员不能够直接操作该Map，因此通过ThreadLocal工具类管理线程
	private static ThreadLocal<Connection> t = new ThreadLocal<Connection>();
	
	/**
	 * 开启事务
	 */
	public static void startTransaction() {
		Connection conn = JDBCUtils.getConn();
		try {				// 开启连接，并将其放入当前线程的map
			conn.setAutoCommit(false);
			System.out.println(conn.getAutoCommit());
			t.set(conn);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 提交事务 
	 */
	public static void commitTransaction() {
		try {
			System.out.println("提交");
			t.get().commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 回滚事务
	 */
	public static void rollbackTransaction() {
		try {
			t.get().rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取数据库连接
	 * @return 数据库连接
	 */
	public static Connection getConn() {
		return t.get();
	}
	
	/**
	 * 关闭数据库连接
	 */
	public static void closeConn() {
		if(t.get() != null) {		// t.get() == Connection
			try {
				if(!t.get().isClosed()) {
					t.get().close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				t.set(null);
			}
		}
		t.remove();
	}
}

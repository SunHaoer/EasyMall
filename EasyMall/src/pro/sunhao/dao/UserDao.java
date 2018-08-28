package pro.sunhao.dao;

import pro.sunhao.domain.User;
import pro.sunhao.exception.MsgException;

public interface UserDao{
	
	/**
	 * 根据username查找数据库中是否已存在该用户名
	 * @param username
	 * @return true(存在) || false(不存在)
	 */
	boolean getUserByUsername(String username);
	
	/**
	 * 向数据库中添加用户user信息
	 * @param user
	 * @return true(添加成功) || false(添加失败)
	 */
	boolean insertUser(User user);
	
	/**
	 * 
	 * 通过用户名和密码查询用户信息
	 * @param username
	 * @param password
	 * @return user(查询成功) || null(查询失败)
	 * @throws MsgException
	 */
	User getUserByUsernameAndPassword(String username, String password)  throws MsgException ;


}


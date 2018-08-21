package pro.sunhao.service;

import pro.sunhao.domain.User;
import pro.sunhao.exception.MsgException;

/**
 * 提供业务逻辑抽象方法的接口
 * @author Administrator
 *
 */
public interface UserService {
	
	/**
	 * 用来判断用户名是否存在的方法，注册时用
	 * @param username 用户名
	 * @return true(用户名存在) || false(用户名不存在)
	 */
	boolean hasUsername(String username);
	
	/**
	 * 用来将注册信息user存入数据库的方法
	 * @param user 封装用户信息的JavaBean
	 * @return true(注册成功) || false(注册失败)
	 */
	boolean registerUser(User user);
	
	/**
	 * 判断用户是否可以登录(用户名密码是否匹配)的方法
	 * @param username 用户名
	 * @param password 密码
	 * @return user 封装当前登录用户的信息 || null 登录失败
	 * @throws MsgException
	 */
	User login(String username, String password) throws MsgException;
}

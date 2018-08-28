package pro.sunhao.service;

import pro.sunhao.dao.UserDao;
import pro.sunhao.dao.UserDaoImpl;
import pro.sunhao.domain.User;
import pro.sunhao.exception.MsgException;
import pro.sunhao.factory.BaseFactory;
import pro.sunhao.factory.UserDaoFactory;
import pro.sunhao.factory.UserServiceFactory;
import pro.sunhao.util.TransactionManager;

/**
 * 为User处理业务逻辑
 * @author Administrator
 *
 */
public class UserServiceImpl implements UserService {
	//private UserDao dao = new UserDaoImpl();
	//private UserDao dao = UserDaoFactory.getFactory().getInstance();
	private UserDao dao = BaseFactory.getFactory().getInstance(UserDao.class);
	
	@Override
	public boolean hasUsername(String username) {
		boolean flag = dao.getUserByUsername(username);
		return flag;
	}

	@Override
	public boolean registerUser(User user) {
		boolean flag = false;
		
		try {
			TransactionManager.startTransaction();
			boolean flag1 = false, flag2 = false;
			flag1 = dao.insertUser(user);
			flag2 = dao.createCart(user);	
			System.out.println(flag1 + " " + flag2);
			//TransactionManager.commitTransaction();
			flag = flag1 && flag2;
			TransactionManager.commitTransaction();
//			if(flag) {
//				TransactionManager.commitTransaction();
//			}
		} catch (Exception e) {
			e.printStackTrace();
			TransactionManager.rollbackTransaction();
		} finally {
			TransactionManager.closeConn();
		}
		return flag;
	}

	@Override
	public User login(String username, String password) throws MsgException {
		User user = dao.getUserByUsernameAndPassword(username, password);
		return user;
	}
}

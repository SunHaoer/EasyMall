package pro.sunhao.service;

import pro.sunhao.dao.UserDao;
import pro.sunhao.dao.UserDaoImpl;
import pro.sunhao.domain.User;
import pro.sunhao.exception.MsgException;
import pro.sunhao.factory.BaseFactory;
import pro.sunhao.factory.UserDaoFactory;
import pro.sunhao.factory.UserServiceFactory;

/**
 * UserService接口的实现类
 * @author Administrator
 *
 */
public class UserServiceImpl implements UserService {
	//private UserDao dao = new UserDaoImpl();
	//private UserDao dao = UserDaoFactory.getFactory().getInstance();
	private UserDao dao = BaseFactory.getFactory().GetInstance(UserDao.class);
	
	@Override
	public boolean hasUsername(String username) {
		boolean flag = dao.getUserByUsername(username);
		return flag;
	}

	@Override
	public boolean registerUser(User user) {
		boolean flag = dao.insertUser(user);
		return flag;
	}

	@Override
	public User login(String username, String password) throws MsgException {
		User user = dao.getUserByUsernameAndPassword(username, password);
		return user;
	}
}

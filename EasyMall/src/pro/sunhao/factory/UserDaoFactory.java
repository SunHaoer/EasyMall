package pro.sunhao.factory;

import java.io.FileInputStream;
import java.util.Properties;

import pro.sunhao.dao.UserDao;

public class UserDaoFactory {
	private Properties prop = new Properties();			// 读取配置文件工具prop
	private UserDaoFactory() {
		ClassLoader loader = UserDaoFactory.class.getClassLoader();			// 加载classloader
		String path = loader.getResource("config.properties").getPath();	// 获取配置文件绝对路径
		try {
			prop.load(new FileInputStream(path));		// 读取配置文件内容
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static UserDaoFactory factory = new UserDaoFactory();		// 私有静态本类唯一实例
	
	public static UserDaoFactory getFactory() {			// 公有的返回本类本类唯一实例的方法
		return factory;
	}
	
	public UserDao getInstance() {						// 创建实例
		UserDao dao = null;					
		String value = prop.getProperty("UserDao");		// 获取配置文件实现的类名，包名
		try {
			Class c = Class.forName(value);				// 加载该实现类
			if(c != null) {	
				dao = (UserDao) c.newInstance();		// 通过反射实现实例
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dao;
	}
}

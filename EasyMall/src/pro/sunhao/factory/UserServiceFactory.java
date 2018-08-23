package pro.sunhao.factory;

import java.io.FileInputStream;
import java.util.Properties;

import javax.imageio.stream.FileImageInputStream;

import pro.sunhao.service.UserService;

public class UserServiceFactory {

	// 单例模式
	private UserServiceFactory() {		// 私有构造器，禁止在类外new实例，故tomcat启动后只该构造方法只运行一次
		ClassLoader loader = UserServiceFactory.class.getClassLoader();		// 获取classloader对象
		String path = loader.getResource("config.properties").getPath();		// 获取classpath下的配置文件的绝对路径
		try {
			prop.load(new FileInputStream(path));			// 加载配置文件
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static UserServiceFactory factory = new UserServiceFactory();		// 私有静态本类唯一实例
	
	public static UserServiceFactory getUserServiceFactory() {		// 公有静态方法获取factory
		//System.out.println("2222");
		return factory;
	}
	
	// 读取配置文件的工具类
	private Properties prop = new Properties();
	
	public UserService getInstance() {
		//System.out.println("33333");
		UserService service = null;
		String value = prop.getProperty("UserService");		// 获取配置文件中配置的实现类的包名，类名
		Class c = null;
		try {
			c = Class.forName(value);			// 加载实现类
			if(c != null) {
				return (UserService) c.newInstance();		// 通过反射创建该类实例
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return service;
	}
	
}

package pro.sunhao.factory;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * 通过泛型和发射生成对应类的实例的factory
 * @author Administrator
 *
 */
public class BaseFactory {
	// 单例
	private Properties prop = new Properties();		// 读取配置文件的工具
	// 1.私有化的构造器
	private BaseFactory() {
		ClassLoader loader = BaseFactory.class.getClassLoader();		// 加载类加载器loader
		String path = loader.getResource("config.properties").getPath();		// 获取配置文件路径
		try {
			prop.load(new FileInputStream(path));		// 读取配置文件
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	// 2.私有静态的本类唯一实例
	private static BaseFactory factory = new BaseFactory();		// 私有静态实例(唯一)
	
	public static BaseFactory getFactory() {			// 公有的返回本类唯一实例的方法
		return factory;
	}
	
	/**
	 * 根据传入的接口类型创建配置文件中所配置的该接口的实现类
	 * @param infc 接口的class对象
	 * @return 该接口实现类的实例 || null
	 */
	public <T> T getInstance(Class<T> infc) {
		T t = null;
		String value = prop.getProperty(infc.getSimpleName());	// 获取配置文件中配置的实现类的包名，类名
		System.out.println("++" + value);
		try {
			Class c = Class.forName(value);
			if(c != null) {
				t = (T) c.newInstance();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}
}

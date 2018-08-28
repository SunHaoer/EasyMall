package pro.sunhao.common;

import java.io.File;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.junit.Test;

/**
 * 想提取出encode，反而更麻烦了。。。
 * @author Administrator
 *
 */

public class InitServlet {			
	public static String encode;
	
	static {
		init();
	}
	
	public static void init() {
		try {
			File file = new File("I:\\MyJavaspace\\EasyMall\\WebRoot\\WEB-INF\\web.xml");
			System.out.println(file.exists());
			SAXReader reader = new SAXReader();
			Document doc = reader.read(file);
			Element rootEle = doc.getRootElement();
			System.out.println(rootEle.getName());
			List<Element> contextParamEle = rootEle.elements("context-param");
			System.out.println(contextParamEle.size());
			Element paramValueEle = contextParamEle.get(0).element("param-value");
			encode = paramValueEle.getText();
			System.out.println(encode);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

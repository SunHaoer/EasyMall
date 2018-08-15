package pro.sunhao.util;

/**
 * 供servlet使用的工具类
 * @author Administrator
 *
 */
public class WebUtils {
	
	/**
	 * 用来判断字符串是否为null或者trim之后是空串的方法
	 * @param str 被判断的字符串
	 * @return 是空的 true, 非空false
	 */
	public static boolean isEmpty(String str) {			// 判断字符串是否为空
		if(str == null || "".equals(str.trim())) {	
			return true;
		} else {
			return false;
		}
	}
}

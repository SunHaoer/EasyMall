package pro.sunhao.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pro.sunhao.util.VerifyCode;

/**
 * 接收用户请求
 * 调用工具类，生成验证码图片
 * 将生成的图片响应到浏览器
 * @author Administrator
 *
 */
public class ValiImageServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("pragma", "no-cathe");
		response.setHeader("cache-control", "no-cathe");
		VerifyCode vc = new VerifyCode();				// 创建实例
		vc.drawImage(response.getOutputStream());		// 生成图片，给response应答实体
		String text = vc.getCode();			// 生成验证码文本
		//System.out.println("cv=" + text);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
}

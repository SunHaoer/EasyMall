package pro.sunhao.web;

import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 根据前台JSP请求，响应生成对应图片
 * @author Administrator
 *
 */
public class ProdImageServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String imgurl = request.getParameter("imgurl");		// 获取请求参数imgurl
		FileInputStream in = new FileInputStream(this.getServletContext().getRealPath(imgurl));
		ServletOutputStream out = response.getOutputStream();
		byte[] arr = new byte[100];
		int len = in.read(arr);
		while(len != -1) {
			out.write(arr, 0, len);
			len = in.read(arr);
		}
		in.close();		// 关闭输入流，输出流由web容器自动关闭
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

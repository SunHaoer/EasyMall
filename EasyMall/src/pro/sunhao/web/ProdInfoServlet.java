package pro.sunhao.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import pro.sunhao.domain.Prod;

/**
 * 商品详情页面的servlet
 * @author Administrator
 *
 */
public class ProdInfoServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Prod prod = new Prod();
		String strs[] = request.getParameter("prod").split(",");

		prod.setId(Integer.parseInt(strs[0].split("=")[1]));
		prod.setName(strs[1].split("=")[1]);
		prod.setPrice(Double.parseDouble(strs[2].split("=")[1]));
		prod.setCid(Integer.parseInt(strs[3].split("=")[1]));
		prod.setPnum(Integer.parseInt(strs[4].split("=")[1]));
		prod.setImgurl(strs[5].split("=")[1]);
		prod.setDescription(strs[6].split("=")[1]);
		prod.setCname(strs[7].split("=")[1].substring(0, strs[7].split("=")[1].length() - 2));
		System.out.println(prod.toString());
		request.setAttribute("prod", prod);

		request.getRequestDispatcher("/prodinfo/prodInfo.jsp").include(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

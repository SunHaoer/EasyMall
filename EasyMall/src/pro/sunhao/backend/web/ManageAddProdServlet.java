package pro.sunhao.backend.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import pro.sunhao.domain.Prod;
import pro.sunhao.factory.BaseFactory;
import pro.sunhao.service.ProdService;

/**
 * 后台管理商品的servelet
 * @author Administrator
 *
 */
public class ManageAddProdServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext sc = this.getServletContext();
		String encode = sc.getInitParameter("encode");
		Map<String, String> paramMap = new HashMap<String, String>();	// map[name] = value
		// 1. 获取请求参数
		String uploadPath = "/WEB-INF/upload";
		String tempPath = "/WEB-INF/temp";
		DiskFileItemFactory factory = new DiskFileItemFactory(1024, new File(sc.getRealPath(tempPath)));	// FileItem工厂类，参数为缓冲区大小和临时文件夹对象
		ServletFileUpload fileUpload = new ServletFileUpload(factory);		// FileUpload的核心工具类
		if (!fileUpload.isMultipartContent(request)) {						// 判断文件上传的表单
			throw new RuntimeException("请使用正确的文件上传表单");
		}
		fileUpload.setHeaderEncoding(encode);				// 处理文件名乱码
		fileUpload.setFileSizeMax(1024 * 1024);				// 设置单个文件夹大小上限
		fileUpload.setSizeMax(1024 * 1024 * 10);			// 设置一次请求上传总文件的大小上限
		
		try {
			List<FileItem> list = fileUpload.parseRequest(request);
			if(list != null) {
				for(FileItem fileItem : list) {
					if(fileItem.isFormField()) {			// 普通表单
//						String name =  fileItem.getFieldName();
//						String value = fileItem.getString(encode);
//						System.out.println(name + " " + value);
						paramMap.put(fileItem.getFieldName(), fileItem.getString(encode));
					} else {				// 上传图片
						String fileName = fileItem.getName();		// 图片文件名
						//fileName = fileName.substring(fileName.lastIndexOf("\\") + 1);		// 处理ie的bug
						String saveName = UUID.randomUUID().toString() + "_" + fileName;	// 解决文件乱码问题
						String hashStr = Integer.toHexString(saveName.hashCode());			// 解决单个文件夹下文件过多问题
						while(hashStr.length() < 8) {				// 不足8位补足
							hashStr = "0" + hashStr;
						}
						String midPath = "/";		// 中间路径
						for(int i = 0; i < hashStr.length(); i++) {
							midPath = midPath + hashStr.charAt(i) + "/";
						}
						
						String imageUrl = uploadPath + midPath + saveName;		// 保存在数据库的url
						paramMap.put("imgurl", imageUrl);
						String savePath = sc.getRealPath(uploadPath + midPath);		// 获取真实保存路径
						new File(savePath).mkdirs();		// 在硬盘上创建对应的目录结构
						InputStream in = fileItem.getInputStream();		// 获取图片输入流
						System.out.println(savePath + " " + saveName);
						//System.out.println("------------");
						FileOutputStream out = new FileOutputStream(savePath + "/" + saveName);		// 图片输出流
						byte[] array = new byte[100];
						int len = in.read(array);
						while(len != -1) {
							out.write(array, 0, len);
							len = in.read(array);
						}
						in.close();
						out.close();
						fileItem.delete();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 2. 表单验证
		
		// 3. 把参数封装成Prod对象
		//System.out.println(paramMap.get("price"));
		Prod prod = new Prod();
		prod.setName(paramMap.get("name"));
		prod.setPrice(Double.parseDouble(paramMap.get("price")));
		prod.setCname(paramMap.get("cname"));
		prod.setPnum(Integer.parseInt(paramMap.get("pnum")));
		prod.setImgurl(paramMap.get("imgurl"));
		prod.setDescription(paramMap.get("description"));
		// 4. service执行逻辑
		//System.out.println(ProdServiceImpl.class);
		ProdService service = BaseFactory.getFactory().getInstance(ProdService.class);
		boolean isAdd = false;		// 表示商品是否添加成功
		try {
			isAdd = service.addProd(prod);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 5. 根据结果转发对应图示
		if(isAdd) {
			response.getWriter().write("添加成功");
			response.setHeader("refresh", "2;url=" + request.getContextPath() + "/backend/_right.jsp");
		} else {
			response.getWriter().write("添加失败");
			response.setHeader("refresh", "2;rul=" + request.getContextPath() + "/backend/manageAddProd.jsp");
		}
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}

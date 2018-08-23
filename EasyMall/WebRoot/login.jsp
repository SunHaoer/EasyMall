<%@page import="java.net.URLDecoder"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
	<head>
		<%
    		String path = request.getContextPath();
    		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
		%>
		<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" href="<%=basePath%>/css/login.css"/>
		<title>EasyMall欢迎您登录</title>
	</head>
	<body>
		
		<%
			Cookie[] cs = request.getCookies();		// 获取用户携带的cookie
			Cookie findC = null;
			if(cs != null) {			// 查看cookie中是否包含username
				for(Cookie c : cs) {
					//System.out.println(c.getPath());
					if("remname".equals(c.getName())) {		
						findC = c;
						break;
					}
				}
			}
			String username = "";
			if(findC != null) {			// 找到remname, 添加到username
				username = URLDecoder.decode(findC.getValue(), "utf-8");
			}
	 	%>
		<h1>欢迎登陆EasyMall</h1>
		<form action=<%=request.getContextPath() + "/servlet/LoginServlet"%> method="POST">
			<table>
				<tr>
					<td colspan="2">
						<%--<%=request.getAttribute("msg")==null ? "" : request.getAttribute("msg") --%>
						${requestScope.msg }
					</td>
				</tr>
				<tr>
					<td class="tdx">用户名：</td>
					<td><input type="text" name="username" value="<%=username%>"/></td>
				</tr>
				<tr>
					<td class="tdx">密&nbsp;&nbsp; 码：</td>
					<td><input type="password" name="password"/></td>
				</tr>
				<tr>
					<td colspan="2">
						<input type="checkbox" name="remname" value="true" 
						<c:if test="${empty cookie.remname}">""</c:if>
						<c:if test="${not empty cookie.remname}">checked="checked"</c:if> 
						/>记住用户名
						<input type="checkbox" name="autologin" value="true"/>30天内自动登录
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align:center">
						<input type="submit" value="登录"/>
					</td>
				</tr>
			</table>
		</form>		
	</body>
</html>

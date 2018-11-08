<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<link rel="stylesheet" href="${app}/css/head.css"/>
<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />

<div id="common_head">
	<div id="line1">
		<div id="content">
		<%--
			<%  
				// 获取session
				HttpSession session = request.getSession();
				if(session == null || session.getAttribute("user") == null) {
			%>
			<a href="login.jsp">登录</a>&nbsp;&nbsp;|&nbsp;&nbsp;
			<a href="regist.jsp">注册</a>			
			<%	
				} else {
			%>
			欢迎回来<%=request.getSession().getAttribute("user") %>
			<a href=<%=request.getContextPath() + "/servlet/LogoutServlet"%>>注销</a>
			<%
				}
		 	%>			
		 --%>
			<c:if test="${empty sessionScope.user}">
				<a href="login.jsp">登录</a>&nbsp;&nbsp;|&nbsp;&nbsp;
				<a href="regist.jsp">注册</a>
			</c:if>
			<c:if test="${not empty sessionScope.user}">
				欢迎回来,${sessionScope.user.username }
				<a href=<%=request.getContextPath() + "/servlet/LogoutServlet"%>>&nbsp;&nbsp;|&nbsp;&nbsp;注销</a>				
			</c:if>
			
    	</div>
	</div>
	<div id="line2">
		<img id="logo" src="${app}/img/head/logo.jpg"/>
		<input type="text" name=""/>
		<input type="button" value="搜 索"/>
		<span id="goto">
			<a id="goto_order" href="#">我的订单</a>
			<a id="goto_cart" href="${app }/servlet/UserCartServlet">我的购物车</a>
		</span>
		<img id="erwm" src="${app}/img/head/qr.jpg"/>
	</div>
	<div id="line3">
		<div id="content">
			<ul>
				<li><a href="${app}/index.jsp">首页</a></li>
				<li><a href="${app}/servlet/ProdListServlet">全部商品</a></li>
				<li><a href="${app}/servlet/ProdListServlet">全部商品</a></li>
				<li><a href="${app}/servlet/ProdListServlet">全部商品</a></li>
				<li><a href="${app}/servlet/ProdListServlet">全部商品</a></li>
				<li><a href="${app}/servlet/ProdListServlet">全部商品</a></li>
				<li><a href="${app}/backend/manage.jsp">后台管理</a></li>
				<li><a href="${app}/backend/manage.jsp">后台管理</a></li>
				<li><a href="${app}/backend/manage.jsp">后台管理</a></li>
				<li><a href="${app}/backend/manage.jsp">后台管理</a></li>
			</ul>
		</div>
	</div>
</div>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML>
<html>
	<head>
		<link href="${app }/css/cart.css" rel="stylesheet" type="text/css">
		<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
	</head>
	<body>
		<div class="warp">
			<!-- 标题信息 -->
			<div id="title">
				<input name="allC" type="checkbox" value="" onclick=""/>
				<span id="title_checkall_text">全选</span>
				<span id="title_name">商品</span>
				<span id="title_price">单价（元）</span>
				<span id="title_buynum">数量</span>
				<span id="title_money">小计（元）</span>
				<span id="title_del">操作</span>
			</div>
			<!-- 购物信息 -->
			<c:forEach items="${prods}" var="prod">
			<div id="prods">		
				<input name="prodC" type="checkbox" value="" onclick=""/>
				<img src="${app}/servlet/ProdImageServlet?imgurl=${prod.imgurl}" width="90" height="90" />
				<span id="prods_name">${prod.name }</span>
				<span id="prods_price">${prod.price }</span>
				<span id="prods_buynum"> 
					<a href="javascript:void(0)" id="delNum" >-</a>
					<input id="buyNumInp" type="text" value="1" >
					<a href="javascript:void(0)" id="addNum" >+</a>
				</span>
				<span id="prods_money">1024</span>
				<span id="prods_del"><a href="#">删除</a></span>
			
			</div>
			</c:forEach>
			<!-- 总计条 -->
			<div id="total">
				<div id="total_1">
					<input name="allC" type="checkbox" value=""/> 
					<span>全选</span>
					<a id="del_a" href="#">删除选中的商品</a>
					<span id="span_1">总价：</span>
					<span id="span_2">￥1024</span>
				</div>
				<div id="total_2">	
					<a id="goto_order" href="#">去结算</a>
				</div>
			</div>
		</div>
	</body>
</html>
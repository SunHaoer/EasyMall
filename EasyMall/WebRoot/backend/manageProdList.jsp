<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
  <head>
  	<style type="text/css">
  		body{
  			text-align: center;
  		}
		table {
			text-align: center;
		}
		th{
			background-color: silver;
		}
  	</style>
  	<script type="text/javascript" src="${app}/js/jquery-1.4.2.js"></script>
  	<script type="text/javascript">
  		function changeNum(inp,pid){
  			// 通过方法传参获取到了当前用户修改的input对应的pid和pnum
  			// 请求发送的url
  			var url="${app}/servlet/ManageUpdatePnumServlet";
  			// 发送AJAX请求，修改该商品的数量
  			$.post(url,{"pid":pid,"pnum":inp.value},function(result){
  				// 获取服务器返回的信息，显示在页面上
  				alert(result);
  			});
  		}
  	</script>
  </head>
  <body>
  	<h1>商品管理</h1>
  	<a href="${pageContext.request.contextPath }/backend/manageAddProd.jsp">添加商品</a>
  	<hr>
  	<table align="center" bordercolor="black" border="1" width="90%" cellspacing="0px" cellpadding="5px">
  	<tr>
  		<th>商品图片</th>
  		<th>商品id</th>
  		<th>商品名称</th>
		<th>商品种类</th>
		<th>商品单价</th>
		<th>库存数量</th>
		<th>描述信息</th>
		<th>删除</th>
  	</tr>
  	<c:forEach items="${requestScope.prods }" var="prod" >
  		<tr>
  			<td><img width="120px" height="120px" src="${app}/servlet/ProdImageServlet?imgurl=${prod.imgurl}" /></td>
  			<td>${prod.id }</td>
  			<td>${prod.name }</td>
  			<td>${prod.cname }</td>
  			<td>${prod.price }</td>
  			<td><input type="text" value="${prod.pnum }" style="width:40px" onblur="changeNum(this,${prod.id})"></input></td>
  			<td>${prod.description }</td>
  			<td><a style="text-decoration: none" href="${app}/servlet/ManageDeleteProdServlet?pid=${prod.id}">删除</a></td>
  		</tr>
  	</c:forEach>
  	</table>
  </body>
</html>

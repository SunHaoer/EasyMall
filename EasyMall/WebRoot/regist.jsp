<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
	<head>
		<title>欢迎注册EasyMall</title>
		<%
    		String path = request.getContextPath();
    		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
		%>
		<base href="<%=basePath%>">
		<meta http-equiv="Content-type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" href="<%=basePath%>/css/regist.css" />
		<script type="text/javascript" src="<%=basePath%>/js/jquery-1.4.2.js"></script>
		<script type="text/javascript">
			var formObj = {
				"checkForm": function() {
					var canSubmit = true; // 控制表单是否提交, 默认true
					// 非空验证
					canSubmit = this.checkNull("username", "用户名不能为空") && canSubmit;
					canSubmit = this.checkNull("password", "密码不能为空") && canSubmit;
					canSubmit = this.checkNull("password2", "确认密码不能为空") && canSubmit;
					canSubmit = this.checkNull("nickname", "昵称不能为空") && canSubmit;
					canSubmit = this.checkNull("email", "邮箱不能为空") && canSubmit;
					canSubmit = this.checkNull("valistr", "验证码不能为空") && canSubmit;
					// 2次密码一致验证
					canSubmit = this.checkPassword("password", "两次密码应该一致") && canSubmit;
					// 邮箱格式验证
					canSubmit = this.checkEmail("email", "邮箱格式不正确") && canSubmit;		
					return true;
				},
				"checkEmail": function(name, msg) {		// 邮箱格式验证
					var email = $("input[name='"+name+"']").val();		// 获取用户输入的邮箱内容
					if($.trim(email) != "") {		// 判断邮箱格式
						var regex=/^\w+@\w+(\.\w+)+$/;// 用于验证邮箱的正则
						if(regex.test(email)) {
							this.setMsg(name, "");
							return true;
						} else {
							this.setMsg(name, msg);
							return false;
						}
					}
					return false;
				},
				"checkPassword": function(name, msg) {		// 检查2次密码是否一致
					var psd1 = $("input[name='"+name+"']").val();
					var psd2 = $("input[name='"+name+"2']").val();
					if($.trim(psd1) != "" && $.trim(psd2) != "") {		// 2个密码不为空再进行比较
						if(psd1 != psd2) {		// 两次密码不一致
							this.setMsg(name + "2", msg);
							return false;
						} else {				// 2次密码一致
							this.setMsg(name + "2", "");
							return true;
						}
					} 			
					return false;	// 存在空字符串
					
				},	
				"checkNull": function(name, msg) {			// 检查是否为空
					// 通过name获取对应的input输入框, 再调用.val方法获取输入框的值
					var value = $("input[name='" + name + "']").val();
					if($.trim(value) == "") {		// 设置错误提示信息
						this.setMsg(name, msg);
						return false;
					} else {				// 清空之前设置的错误提示信息
						this.setMsg(name, "");
						return true;
					}
				},
				"setMsg": function(name, msg) {			// 获取name对应的input后面的span
					$("input[name='" + name + "']").nextAll("span").html(msg).css("color", "red");
				}
			};

			// 添加一个文档就绪事件
			$(function() {
				$("input[name='username']").blur(function() {			// 添加input失去鼠标焦点事件
					formObj.checkNull("username", "用户名不能为空");
				});
				$("input[name='password']").blur(function() {
					formObj.checkNull("password", "密码不能为空");
				});
				$("input[name='password2']").blur(function() {
					formObj.checkNull("password2", "确认密码不能为空");
					formObj.checkPassword("password", "两次密码应该一致");
				});
				$("input[name='nickname']").blur(function() {
					formObj.checkNull("nickname", "昵称不能为空");
				});
				$("input[name='email']").blur(function() {
					formObj.checkNull("email", "邮箱不能为空");
					formObj.checkEmail("email", "邮箱格式不正确");
				});
				$("input[name='valistr']").blur(function() {
					formObj.checkNull("valistr", "验证码不能为空");
				});
				// 为验证码图片标签添加一个click事件
				$("#vali_img").click(function(){
					// 每次点击获取当前时间的毫秒值
					var dateStr = new Date().getTime();
					// 修改src属性值
					//$(this).attr("src", "/EasyMall/servlet/ValiImageServlet?time="+dateStr);
					$(this).attr("src", "<%=request.getContextPath()%>/servlet/ValiImageServlet?time=" + dateStr);
				});
			});
		</script>
	</head>

	<body>
		<form action=<%=request.getContextPath() + "/servlet/RegistServlet"%> method="POST" onSubmit="return formObj.checkForm()" >
			<h1>欢迎注册EasyMall</h1>
			<table>
				<tr>
					<td colspan="2">
					<%=request.getAttribute("msg")==null?"":request.getAttribute("msg") %>
				</tr>
				<tr>
					<td class="tds">用户名：</td>
					<td>
						<input type="text" name="username" value='<%=request.getParameter("username")==null?"":request.getParameter("username") %>' /><span></span>
					</td>
				</tr>
				<tr>
					<td class="tds">密码：</td>
					<td>
						<input type="password" name="password"/><span></span>
					</td>
				</tr>
				<tr>  
					<td class="tds">确认密码：</td>
					<td>
						<input type="password" name="password2"/><span></span>
					</td>
				</tr>
				<tr>
					<td class="tds">昵称：</td>
					<td>
						<input type="text" name="nickname" value='<%=request.getParameter("nickname")==null?"":request.getParameter("nickname") %>'/><span></span>
					</td>
				</tr>
				<tr>
					<td class="tds">邮箱：</td>
					<td>
						<input type="text" name="email" value='<%=request.getParameter("email")==null?"":request.getParameter("email") %>'/><span></span>
					</td>
					
				</tr>
				<tr>
					<td class="tds">验证码：</td>
					<td>
						<input type="text" name="valistr" value='<%=request.getParameter("valistr")==null?"":request.getParameter("valistr") %>'/>
						<img id="vali_img" src=<%=request.getContextPath() + "/servlet/ValiImageServlet"%> width="" height="" alt="" /><span></span>
					</td>
				</tr>
				<tr>
					<td class="sub_td" colspan="2" class="tds">
						<input type="submit" value="注册用户"/>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>

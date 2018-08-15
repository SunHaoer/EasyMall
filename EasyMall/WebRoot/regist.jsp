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
					var isSubmit = true; // 控制表单是否提交, 默认true
					// 非空验证
					isSubmit = this.checkNull("username", "用户名不能为空") && isSubmit;
					isSubmit = this.checkNull("password", "密码不能为空") && isSubmit;
					isSubmit = this.checkNull("password2", "确认密码不能为空") && isSubmit;
					isSubmit = this.checkNull("nickname", "昵称不能为空") && isSubmit;
					isSubmit = this.checkNull("email", "邮箱不能为空") && isSubmit;
					isSubmit = this.checkNull("valistr", "验证码不能为空") && isSubmit;
					// 2次密码一致验证
					isSubmit = this.checkPassword("password", "两次密码应该一致") && isSubmit;
					// 邮箱格式验证
					isSubmit = this.checkEmail("email", "邮箱格式不正确") && flag;
					return isSubmit;
				},
				"checkEmail": function(name, msg) {
					// 获取用户输入的邮箱内容
					var email = $("input[name='"+name+"']").val();
					// 判断邮箱格式
					if($.trim(email) != "") {
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
				"checkPassword": function(name, msg) {
					// 比较2次输入的密码
					var psd1 = $("input[name='"+name+"']").val();
					var psd2 = $("input[name='re"+name+"']").val();
					// 2个密码不为空再进行比较
					if($.trim(psd1) != "" && $.trim(psd2) != "") {
						if(psd1 != psd2) {		// 两次密码不一致
							this.setMsg("re" + name, msg);
							return false;
						} else {
							this.setMsg("re" + name, "");
							return true;
						}
					}
					return false;
				},
				"checkNull": function(name, msg) {
					// 通过name获取对应的input输入框, 再调用.val方法获取输入框的值
					var value = $("input[name='" + name + "']").val();
					if($.trim(value) == "") {
						// 设置错误提示信息
						this.setMsg(name, msg);
						return false;
					} else {
						// 清空之前设置的错误提示信息
						this.setMsg(name, "");
						return true;
					}
				},
				"setMsg": function(name, msg) {
					// 获取name对应的input后面的span
					$("input[name='" + name + "']").nextAll("span").html(msg).css("color", "red");
				}
			};

			// 添加一个文档就绪事件
			$(function() {
				// 添加input失去鼠标焦点事件
				$("input[name='username']").blur(function() {
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
			});
		</script>
	</head>

	<body>
		<form action="/EasyMall/servlet/RegistServlet" method="POST" >
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
						<input type="text" name="valistr" value='<%=request.getParameter("valistr")==null?"":request.getParameter("valistr") %>'/><span></span>
						<img src="img/regist/yzm.jpg" width="" height="" alt="" />
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

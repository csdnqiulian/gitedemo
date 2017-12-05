<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>系统登录</title>
<meta charset="UTF-8" />
<meta name="Designer" content="xx1">
<meta name="Author" content="111">
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" type="text/css" href="css/structure.css">
</head>

<body>
	<form class="box login" action="${pageContext.request.contextPath}/login" method="post">
		<fieldset class="boxBody">
			<label>用户:</label> <input type="text" tabindex="1" id="userName" name="userName" placeholder="用户"
				required> <label><a href="#" class="rLink"
				tabindex="5">忘记密码?</a> 密码</label> 
			<input type="password" id="password" name="password" tabindex="2" required>
		</fieldset>
		<footer>
			<label><input type="checkbox" tabindex="3">保存</label> <input
				type="submit" class="btnLogin" value="登录" tabindex="4">
		</footer>
	</form>
	<footer id="main">
		<a href="http://wwww.cssjunction.com">相关联系人</a>
	</footer>
</body>
</html>

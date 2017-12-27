<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>首页</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<!-- basic styles -->
		<link href="${ctx}/plug/ace/assets/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="${ctx}/plug/ace/assets/css/ace.min.css" />
		<!-- 自定义 styles -->
		<link rel="stylesheet" href="${ctx}/plug/custom/css/main.css" />
	</head>
<body>
	<div class="container">
		<div class="starter-template">
			<h1>欢迎登录${fns:getConfig('productName')}</h1>
			<p class="lead">
				新框架：testweb</br>
				首页还需要改造：333</br>
			</p>
		</div>
	</div>
</body>
</html>

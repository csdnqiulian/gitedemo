<%@ page contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>系统主界面</title>
<link rel="stylesheet" type="text/css" href="<%=path%>/plug/easyui/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/plug/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="<%=path%>/plug/easyui/demo.css">
<script type="text/javascript" src="<%=path%>/plug/easyui/jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/plug/easyui/jquery.easyui.min.js"></script>
</head>
<body class="easyui-layout">
	<div data-options="region:'north',border:false"
		style="height: 60px; background: #B3DFDA; padding: 10px">
		<div class="logo" >日了狗</div>
	</div>
	<div data-options="region:'west',split:true,title:'West'"
		style="width: 150px; padding: 10px;">west content
		
	</div>
	<div data-options="region:'south',border:false"
		style="height: 50px; background: #A9FACD; padding: 10px;">
		版权
	</div>
	<div data-options="region:'center',title:'Center'"></div>
</body>
</html>
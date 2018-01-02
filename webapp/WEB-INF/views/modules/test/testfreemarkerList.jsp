<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>测试成才管理</title>
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link rel="stylesheet" href="${ctxStatic}/plug/ace/assets/css/bootstrap.min.css" />
		<link rel="stylesheet" href="${ctxStatic}/plug/ace/assets/css/font-awesome.min.css" />
		<link rel="stylesheet" href="${ctxStatic}/plug/ace/assets/css/ui.jqgrid.css" />
		<link rel="stylesheet" href="${ctxStatic}/plug/ace/assets/css/ace.min.css" />
		<!-- 自定义 styles -->
		<link rel="stylesheet" href="${ctxStatic}/plug/custom/css/main.css" />
		<!-- basic scripts -->
		<!--[if IE]>
		<script src="${ctxStatic}/plug/ace/assets/js/jquery-1.10.2.min.js"></script>
		<![endif]-->
		<!--[if !IE]> -->
		<script src="${ctxStatic}/plug/ace/assets/js/jquery-2.0.3.min.js"></script>
		<!-- <![endif]-->
		<script src="${ctxStatic}/plug/ace/assets/js/bootstrap.min.js"></script>
		<script src="${ctxStatic}/plug/ace/assets/js/jqGrid/jquery.jqGrid.min.js"></script>
		<script src="${ctxStatic}/plug/ace/assets/js/jqGrid/i18n/grid.locale-cn.js"></script>
		<script src="${ctxStatic}/plug/custom/js/common.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
		function toAdd(){
			var url = "${ctx}/test/testfreemarker/form";
			window.location.href = url;
		}
	</script>
</head>
<body>
	<form:form id="searchForm" modelAttribute="testfreemarker" action="${ctx}/test/testfreemarker/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btn" class="btn btn-primary" type="button" onclick="toAdd();" value="添加"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名字</th>
				<th>备注</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="testfreemarker">
			<tr>
				<td><a href="${ctx}/test/testfreemarker/form?id=${testfreemarker.id}">
					${testfreemarker.name}
				</a></td>
				<td>
					${testfreemarker.remark}
				</td>
				<td>
    				<a href="${ctx}/test/testfreemarker/form?id=${testfreemarker.id}">修改</a>
					<a href="${ctx}/test/testfreemarker/delete?id=${testfreemarker.id}" onclick="return confirmx('确认要删除该测试成才吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
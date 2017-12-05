<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>测试管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		function addtest(){
			//传入开盘名称
			var url = "${ctx}/test/test/add";					 
			window.location.href = url;
		}
		function page(n,s){
			if(n) $("#pageNo").val(n);
			if(s) $("#pageSize").val(s);
			$("#searchForm").attr("action","${ctx}/test/test/list");
			$("#searchForm").submit();
	    	return false;
	    }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/test/test/list">测试列表</a></li>
	</ul>
	<div class="btn-group" role="group">
	  	<button type="button" class="btn btn-primary" onclick="addtest()">新增</button>
	</div>
	<form:form id="searchForm" modelAttribute="test" action="${ctx}/test/test/list" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<sys:tableSort id="orderBy" name="orderBy" value="${page.orderBy}" callback="page();"/>
		<label>名字：</label><form:input id="type" htmlEscape="false" path="s_name" maxlength="50" class="input-medium"></form:input>
		&nbsp;&nbsp;<label>开始时间 ：</label><form:input path="s_time" htmlEscape="false" class="input-medium" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
		&nbsp;&nbsp;<label>结束时间 ：</label><form:input path="e_time" htmlEscape="false" class="input-medium" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})"/>
		&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>
	</form:form>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>名字</th>
				<th>开始时间</th>
				<th>结束时间</th>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="test">
			<tr>
				<td>${test.name}</td>
				<td><fmt:formatDate value="${test.start_time}" type="both"/></td>
				<td><fmt:formatDate value="${test.endd_time}" type="both" pattern="yyyy-MM-dd"/></td>
				<td>
    				<a href="${ctx}/test/test/add?id=${test.id}">修改</a>
					<a href="${ctx}/test/test/delete?id=${test.id}" onclick="return confirmx('确认要删除该数据吗？', this.href)">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>
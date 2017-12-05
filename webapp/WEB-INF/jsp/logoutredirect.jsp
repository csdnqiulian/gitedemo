<%@ page session="false" language="java" pageEncoding="GBK"%>
<%
	//解决 测评公司提出的风险问题 
	String redirect = request.getContextPath() + "/login.jsp";
	String target = request.getParameter("_redirecttarget");
	if(target == null || target.trim().equals(""))
		target = "top";
	String alertMsg = request.getParameter("_alertMsg");
	
	
%>
<script language="javascript">

<%
if(alertMsg != null )
{
	%>
	alert("对不起，当前用户在其他地方登陆，您将退出系统，请重新登录！")
	<%
}
if(target.equals("location")){%>
	window.location = "<%=redirect%>";
	<%}
	else
	{%>
		window.<%=target%>.location = "<%=redirect%>";
	<%}%>
</script>


<%@ page session="false" language="java" pageEncoding="GBK"%>
<%
	//��� ������˾����ķ������� 
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
	alert("�Բ��𣬵�ǰ�û��������ط���½�������˳�ϵͳ�������µ�¼��")
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


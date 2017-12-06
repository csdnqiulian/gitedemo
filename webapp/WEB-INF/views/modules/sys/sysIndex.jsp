<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>${fns:getConfig('productName')}</title>
	<meta name="decorator" content="blank"/>
	<style type="text/css">
		#main {padding:0;margin:0;} #main .container-fluid{padding:0 4px 0 6px;}
		#header {margin:0 0 8px;position:static;} #header li {font-size:14px;_font-size:12px;}
		#header .brand {font-family:Helvetica, Georgia, Arial, sans-serif, 黑体;font-size:26px;padding-left:33px;}
		#footer {margin:8px 0 0 0;padding:3px 0 0 0;font-size:11px;text-align:center;border-top:2px solid #0663A2;}
		#footer, #footer a {color:#999;} #left{overflow-x:hidden;overflow-y:auto;height: 550px;} #left .collapse{position:static;}
		#userControl>li>a{/*color:#fff;*/text-shadow:none;} #userControl>li>a:hover, #user #userControl>li.open>a{background:transparent;}
		
	</style>
	<script type="text/javascript">
	
	</script>
</head>
<body>
<div id="main">
  <div id="header" class="navbar navbar-fixed-top">
	<div class="navbar-inner">
  		<div class="brand"><span id="productName">${fns:getConfig('productName')}</span></div>
  		<ul id="userControl" class="nav pull-right">
			<li><a href="${pageContext.request.contextPath}${fns:getFrontPath()}/index-${fnc:getCurrentSiteId()}.html" target="_blank" style="color:white;"  title="访问网站主页"><i class="icon-home"></i></a></li>
			<li id="themeSwitch" class="dropdown">
				<a class="dropdown-toggle" data-toggle="dropdown" style="color:white;"  href="#" title="主题切换"><i class="icon-th-large"></i></a>
				<ul class="dropdown-menu">
					<c:forEach items="${fns:getDictList('theme')}" var="dict"><li><a href="#" onclick="location='${pageContext.request.contextPath}/theme/${dict.value}?url='+location.href">${dict.label}</a></li></c:forEach>
					<li><a style="color:white;"  href="javascript:cookie('tabmode','${tabmode eq '1' ? '0' : '1'}');location=location.href">${tabmode eq '1' ? '关闭' : '开启'}页签模式</a></li>
				</ul>
				<!--[if lte IE 6]><script type="text/javascript">$('#themeSwitch').hide();</script><![endif]-->
			</li>
			<li id="userInfo" class="dropdown">
				<a class="dropdown-toggle" data-toggle="dropdown" href="#" style="color:white;" title="个人信息">您好, ${fns:getUser().name}&nbsp;<span id="notifyNum" class="label label-info hide"></span></a>
				<ul class="dropdown-menu">
					<li><a href="${ctx}/sys/user/info" target="mainFrame"><i class="icon-user"></i>&nbsp; 个人信息</a></li>
					<li><a href="${ctx}/sys/user/modifyPwd" target="mainFrame"><i class="icon-lock"></i>&nbsp;  修改密码</a></li>
				</ul>
			</li>
			<li><a href="${ctx}/logout" style="color:white;"  title="退出登录">退出</a></li>
			<li>&nbsp;</li>
		</ul>
  	</div>
	<div class="container-fluid">
			<div id="content" class="row-fluid">
				<div id="left">
					<div class="accordion" id="menu-${param.parentId}">
						<c:set var="menuList" value="${fns:getMenuList()}"/>
						<c:set var="firstMenu" value="true"/>
						  <c:forEach items="${menuList}" var="menu" varStatus="idxStatus">
						  	<c:if test="${menu.parent.id eq (not empty param.parentId ? param.parentId:1)&&menu.isShow eq '1'}">
								<div class="accordion-group">
								    <div class="accordion-heading">
								    	<a class="accordion-toggle" data-toggle="collapse" data-parent="#menu-${param.parentId}" data-href="#collapse-${menu.id}" href="#collapse-${menu.id}" title="${menu.remarks}"><i class="icon-chevron-${not empty firstMenu && firstMenu ? 'down' : 'right'}"></i>&nbsp;${menu.name}</a>
								    </div>
								    <div id="collapse-${menu.id}" class="accordion-body collapse ${not empty firstMenu && firstMenu ? 'in' : ''}">
										<c:forEach items="${menuList}" var="menu2">
										<c:if test="${menu2.parent.id eq menu.id&&menu2.isShow eq '1'}">
										<div class="accordion-inner">
											<ul class="nav nav-list">
													<li class="menu">
														<a class="menu" data-toggle="collapse" data-href="#menu3-${menu2.id}" href="#menu3-${menu2.id}" >
															<i class="accordion-toggle" class="icon-${not empty menu2.icon ? menu2.icon : 'circle-arrow-right'}"></i>&nbsp;${menu2.name}</a>
															
													</li>
													
											</ul>
										</div>
										<div id="menu3-${menu2.id}" class="accordion-body collapse">
													<div class="accordion-innerq">
														<ul class="navq nav-listq">
															<c:forEach items="${menuList}" var="menu3">
																<c:if test="${menu3.parent.id eq menu2.id&&menu3.isShow eq '1'}">
													
																<li class="accordion-toggle" class="menu3-${menu2.id}"><a class="menu" href="${fn:indexOf(menu3.href, '://') eq -1 ? ctx : ''}${not empty menu3.href ? menu3.href : '/404'}" target="${not empty menu3.target ? menu3.target : 'mainFrame'}" ><i class="icon-${not empty menu3.icon ? menu3.icon : 'circle-arrow-right'}"></i>&nbsp;${menu3.name}</a></li>
															 </c:if>
									   						 </c:forEach>
														</ul>
								   		 	</div>
										  </div>
										<c:set var="firstMenu" value="false"/>
										</c:if>
										</c:forEach>
								   	 </div>
									</div>
							</c:if>
							</c:forEach>
						</div>
					
					<%-- 
					<iframe id="menuFrame" name="menuFrame" src="" style="overflow:visible;" scrolling="yes" frameborder="no" width="100%" height="650"></iframe> --%>
				</div>
				<div id="openClose" class="close" style="height: 550px;">&nbsp;</div>
				<div id="right">
					<iframe id="mainFrame" name="mainFrame" src="" style="overflow:visible;" scrolling="yes" frameborder="no" width="100%" height="550"></iframe>
				</div>
			</div>
		   	<div id="footer" class="row-fluid">
	            Copyright &copy; 2012-${fns:getConfig('copyrightYear')} ${fns:getConfig('productName')} - Powered By <a href="http://jeesite.com" target="_blank">testweb</a> ${fns:getConfig('version')}
			</div>
	 </div>
  </div>
</div>
<script type="text/javascript"> 
		var leftWidth = 160; // 左侧窗口大小
		var tabTitleHeight = 33; // 页签的高度
		var htmlObj = $("html"), mainObj = $("#main");
		var headerObj = $("#header"), footerObj = $("#footer");
		var frameObj = $("#left, #openClose, #right, #right iframe");
		function wSize(){
			var minHeight = 500, minWidth = 980;
			var strs = getWindowSize().toString().split(",");
			htmlObj.css({"overflow-x":strs[1] < minWidth ? "auto" : "hidden", "overflow-y":strs[0] < minHeight ? "auto" : "hidden"});
			mainObj.css("width",strs[1] < minWidth ? minWidth - 10 : "auto");
			$("#openClose").height($("#openClose").height() - 5);
			wSizeWidth();
		} 
		function wSizeWidth(){
			if (!$("#openClose").is(":hidden")){
				var leftWidth = ($("#left").width() < 0 ? 0 : $("#left").width());
				$("#right").width($("#content").width()- leftWidth - $("#openClose").width() -5);
			}else{
				$("#right").width("100%");
			}
		} 
	</script>
<script src="${ctxStatic}/common/wsize.js" type="text/javascript"></script>
</body>
</html>
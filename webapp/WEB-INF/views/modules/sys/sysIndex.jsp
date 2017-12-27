<%@page import="java.util.ArrayList"%>
<%@page import="com.common.util.MenuTreeUtil"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.modules.sys.entity.Menu"%>
<%@page import="java.util.List"%>
<%@page import="com.common.util.UserUtils"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%
	List<Menu> menuList = new ArrayList<Menu>();
	List<Menu> menuBeanList = UserUtils.getMenuList();
	if(menuBeanList != null){
		for(Menu bean : menuBeanList){
			Menu menuBean = new Menu();
			menuBean.setIcon(bean.getIcon());
			menuBean.setId(bean.getId());
			menuBean.setName(bean.getName());
			menuBean.setTarget(bean.getTarget());
			menuBean.setHref(bean.getHref());
			menuBean.setIsShow(bean.getIsShow());
			Menu parentMenu = new Menu();
			if(bean.getParent()!=null){
				parentMenu  = bean.getParent();
			} 
			menuBean.setParent(parentMenu);
			menuList.add(menuBean);
		}
	}
	MenuTreeUtil menuTreeUtil = new MenuTreeUtil();
    menuList = menuTreeUtil.buildMenuTree(menuList);
%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>${fns:getConfig('productName')}</title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link href="${ctxStatic}/plug/ace/assets/css/bootstrap.min.css" rel="stylesheet" />
		<link rel="stylesheet" href="${ctxStatic}/plug/ace/assets/css/font-awesome.min.css" />
		<link rel="stylesheet" href="${ctxStatic}/plug/ace/assets/css/jquery.gritter.css" />
		<!-- ace styles -->
		<link rel="stylesheet" href="${ctxStatic}/plug/ace/assets/css/ace.min.css" />
		<link rel="stylesheet" href="${ctxStatic}/plug/ace/assets/css/ace-rtl.min.css" />
		<link rel="stylesheet" href="${ctxStatic}/plug/ace/assets/css/ace-skins.min.css" />
		<!-- 自定义 styles -->
		<link rel="stylesheet" href="${ctxStatic}/plug/custom/css/main.css" />
		<!-- basic scripts -->
		<!--[if IE]>
		<script src="resources/ace/assets/js/jquery-1.10.2.min.js"></script>
		<![endif]-->
		<!--[if !IE]> -->
		<script src="${ctxStatic}/plug/ace/assets/js/jquery-2.0.3.min.js"></script>
		<!-- <![endif]-->
		<script src="${ctxStatic}/plug/ace/assets/js/bootstrap.min.js"></script>
		<script src="${ctxStatic}/plug/ace/assets/js/bootbox.min.js"></script>
		<script src="${ctxStatic}/plug/ace/assets/js/ace.min.js"></script>
		<script src="${ctxStatic}/plug/ace/assets/js/ace-extra.min.js"></script>
		<script src="${ctxStatic}/plug/ace/assets/js/typeahead-bs2.min.js"></script>
		<script src="${ctxStatic}/plug/ace/assets/js/jquery-ui-1.10.3.custom.min.js"></script>

		<!-- ace scripts -->
		<script src="${ctxStatic}/plug/ace/assets/js/ace-elements.min.js"></script>
		<script src="${ctxStatic}/plug/ace/assets/js/jquery.gritter.min.js"></script>
		<script src="${ctxStatic}/plug/custom/js/model.js"></script>
		<script src="${ctxStatic}/plug/custom/js/progress.js"></script>

		<script type="text/javascript">
			function iFrameHeight() {
				debugger;
				var ifm = document.getElementById("iframepage"); 
				try{
					ifm.scrolling = "no";
		        	var subWeb = document.frames ? document.frames["iframepage"].document : ifm.contentDocument;   
		        	if(ifm != null && subWeb != null) {
		        	   ifm.height = subWeb.body.scrollHeight;
		        	}   
				}catch(e){//跨域的情况下
					ifm.height = "510";
					ifm.scrolling = "yes";
				}
	        	
	       	}
			var jqgridheight = "";
			$(function(){
				jqgridheight = $(window).innerHeight();//窗口的文档显示区的高度
			});
			function menuClick(url,menu_names,activeId,parent_activeId,parent_parent_activeId,parent_parent_parent_activeId){
				if(typeof(url) != "undefined" && url != null && url != "" && url != "null"){
					$('#iframepage').attr("src",url);
		        	if(menu_names != ''){
		        		$("#breadcrumb").empty(); //删除所有li
		        		var innerHtml = "<li><i class=\"icon-home home-icon\"></i>"+
		        			"<a href=\"${ctx}/sys/user/innerindex\">首页</a></li>";
		                var menu_name_arr = menu_names.split("|");
		        		for (var i=0,len=menu_name_arr.length; i<len; i++){
		                	if(i == len-1){
		                		innerHtml = innerHtml + "<li class='active'>"+menu_name_arr[i]+"</li>";
		                	}else{
		                		innerHtml = innerHtml + "<li><a href='#'>"+menu_name_arr[i]+"</a></li>";
		                	}
		        		}
		        		$("#breadcrumb").append(innerHtml);
		        	}
				}
				try{
					$('.active').each(function(){
						$(this).removeClass("active");
					 });
				}catch(e){}
				$('#'+parent_parent_parent_activeId).addClass("active");
				$('#'+parent_parent_activeId).addClass("active");
				$('#'+parent_activeId).addClass("active");
				$('#'+activeId).addClass("active");
	        }
			
			/*  function savepwd(){
				var oldPassword = $('#oldPassword').val();
				var newPassword = $('#newPassword').val();
				var confirmNewPassword = $('#confirmNewPassword').val();
				if(oldPassword == ""){
					bootbox.alert("请输入新密码！");
					return;
				}
				if(newPassword != confirmNewPassword){
					bootbox.alert("两次密码不一致,请重新输入！");
					return;
				}
				$.ajax({
	        		url : "${ctx}/sys/user/modifyPwd.do?oldPassword="+oldPassword+"&newPassword="+newPassword",
	        		type : 'POST',
	        		dataType : 'text',
	        		data : '',
	        		success : function(result) {
	        			var resultJson = JSON.parse(result);
	        			if(resultJson.code == '0'){
	        				$('#modal-form').modal('hide');
	        				bootbox.alert({
	        		            message: "密码修改成功，请重新登录！",  
	        		            callback: function() {  
	        		            	location.href = '${pageContext.request.contextPath}/logout.do';
	        		            }
	           				});
	        			}else{
	        				bootbox.alert("操作失败：【"+resultJson.description+"】");
	        			}
	        		},
	        		error : function(jqXHR, textStatus, errorThrown) {
	        			bootbox.alert(jqXHR+textStatus+errorThrown+ "发生异常，操作失败");
	        		}
	        	}); 
			}  */
			
		</script>
	</head>

	<body>
	<!-- start  必须建遮罩div pop1，弹出div pop2  -->
	<div id="pop1" style="z-index:2147483646;background-color:#000 ;opacity:0.5;filter:alpha(opacity=50);width:100%;height:100%;position:absolute;left:0px;top:0px;display:none">   
	</div>   
	<div id="pop2" style="z-index:2147483647;background-size:auto;filter:alpha(opacity=80);left:35%;top:40%;width:450px;height:65px;display:none;position:absolute;text-align: center;">  
		<div style="color:#FFFFFF" id="remindtitle">正在加载中，请稍后。。。</div>
		<div class="progress progress-striped" data-percent="0%">
		<div class="progress-bar progress-bar-success" style="z-index:2000;width: 0%;text-align: center;"></div>
	</div>
	</div>  
	<!-- end -->
		<div class="navbar navbar-default" id="navbar">
			<script type="text/javascript">
				try{ace.settings.check('navbar' , 'fixed');}catch(e){}
			</script>

			<div class="navbar-container" id="navbar-container">
				<div class="navbar-header pull-left">
					<a href="#" class="navbar-brand">
						<small>
						<i class="icon-windows"></i>
							${fns:getConfig('productName')}
						</small>
					</a><!-- /.brand -->
				</div><!-- /.navbar-header -->

				<div class="navbar-header pull-right" role="navigation">
					<ul class="nav ace-nav">
						<li class="light-blue">
							<a data-toggle="dropdown" href="#" class="dropdown-toggle">
								<img class="nav-user-photo" src="${ctxStatic}/plug/ace/assets/avatars/avatar2.png" alt="Jason's Photo" />
								<span class="user-info">
									<small>欢迎光临,</small>
										${fns:getUser().name}
								</span>
								<i class="icon-caret-down"></i>
							</a>
							<ul class="user-menu pull-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
								<!-- <li>
									<a href="#control-panel" data-toggle="modal"><i class="icon-cog"></i>控制面板</a>
								</li> -->
							    <li>
									<a href="#modal-form" data-toggle="modal"><i class="icon-key"></i>密码修改</a>
								</li>
								<li class="divider"></li>
								<li>
									<a href="${ctx}/logout"><i class="icon-off"></i>退出</a>
								</li>
							</ul>
						</li>
					</ul><!-- /.ace-nav -->
				</div><!-- /.navbar-header -->
			</div><!-- /.container -->
		</div>

		<div class="main-container" id="main-container">
			<script type="text/javascript">
				try{ace.settings.check('main-container' , 'fixed');}catch(e){}
			</script>

			<div class="main-container-inner">
				<a class="menu-toggler" id="menu-toggler" href="#">
					<span class="menu-text"></span>
				</a>

				<div class="sidebar" id="sidebar">
					<script type="text/javascript">
						try{ace.settings.check('sidebar' , 'fixed');}catch(e){}
					</script>

					<div class="sidebar-shortcuts" id="sidebar-shortcuts">
						<div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
							<button class="btn btn-success" onclick="location.reload();" title="首页">
								<i class="icon-home  btn-lg no-padding no-margin"></i>
							</button>

							<button class="btn btn-info" onclick="" title="软件下载">
								<i class="icon-download-alt  btn-lg no-padding no-margin"></i>
							</button>

							<button class="btn btn-warning" title="帮助">
								<i class="icon-question-sign  btn-lg no-padding no-margin"></i>
							</button>

							<button class="btn btn-danger" href="#control-panel" data-toggle="modal" title="控制面板">
								<i class="icon-cogs  btn-lg no-padding no-margin"></i>
							</button>
						</div>

						<div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
							<span class="btn btn-success"></span>

							<span class="btn btn-info"></span>

							<span class="btn btn-warning"></span>

							<span class="btn btn-danger"></span>
						</div>
					</div><!-- #sidebar-shortcuts -->

					<ul class="nav nav-list">
						<%if(menuList != null) {
				    		int i = 0;
				    		for(Menu bean : menuList){
				    			String menu_num = bean.getId();
				    		%>
				    			<%if(bean.getSubMenuList().size()>0){//第一级有子菜单 %>
				    			<li id="li_<%=menu_num %>">
					    			<a href="#<%=menu_num %>" class="dropdown-toggle" onclick="menuClick()">
										<i class="<%=bean.getIcon() %>"></i>
										<span class="menu-text"><%=bean.getName()%> </span>
		
										<b class="arrow icon-angle-down"></b>
									</a>
					    			<ul class="submenu">
					    				<%for(Menu sub1bean : bean.getSubMenuList()){ %>
					    					<%if(sub1bean.getSubMenuList().size() > 0) {//第二级有子菜单%>
					    					<li id="li_<%=sub1bean.getId() %>">
								    			<a href="#<%=sub1bean.getId() %>" class="dropdown-toggle">
													<i class="<%=sub1bean.getIcon() %>"></i>
													<span class="menu-text"><%=sub1bean.getName() %> </span>
													<b class="arrow icon-angle-down"></b>
												</a>
								    			<ul class="submenu">
								    				<%for(Menu sub2bean : sub1bean.getSubMenuList()){  %>
								    					<%if(sub2bean.getSubMenuList().size() > 0) {//第三级有子菜单%>
								    						<li id="li_<%=sub2bean.getId() %>">
												    			<a href="#<%=sub2bean.getId() %>" class="dropdown-toggle">
																	<i class="<%=sub2bean.getIcon() %>"></i>
																	<span class="menu-text"><%=sub2bean.getName() %> </span>
																	<b class="arrow icon-angle-down"></b>
																</a>
												    			<ul class="submenu">
												    				<%for(Menu sub3bean : sub2bean.getSubMenuList()){  %>
													    					<li id="li_<%=sub3bean.getId() %>">
																				<a href="javascript:void(0)" onclick="menuClick('${ctx}<%=sub3bean.getHref()%>','<%=bean.getName() %>|<%=sub1bean.getName() %>|<%=sub2bean.getName() %>|<%=sub3bean.getName()%>','li_<%=sub3bean.getName() %>','li_<%=sub2bean.getName() %>','li_<%=sub1bean.getName() %>','li_<%=menu_num %>')">
																					<i class="<%=sub3bean.getIcon() %>"></i>
																					<%=sub3bean.getName() %>
																				</a>
																			</li>
												    				<%} %>
												    			</ul>
												    		</li>
								    					
								    					<%}else{ %>
								    				
									    				 <li id="li_<%=sub2bean.getId() %>">
																<a href="javascript:void(0)" onclick="menuClick('${ctx}<%=sub2bean.getHref() %>','<%=bean.getName() %>|<%=sub1bean.getName() %>|<%=sub2bean.getName()%>','li_<%=sub2bean.getId() %>','li_<%=sub1bean.getId() %>','li_<%=menu_num %>')">
																	<i class="<%=sub2bean.getIcon() %>"></i>
																	<%=sub2bean.getName() %>
																</a>
															</li>  
								    				<%}} %>
								    			</ul>
								    		</li>
					    				<%}else{ %>
				    					  <li id="li_<%=sub1bean.getId() %>">
											<a href="javascript:void(0)" onclick="menuClick('${ctx}<%=sub1bean.getHref() %>','<%=bean.getName() %>|<%=sub1bean.getName()%>','li_<%=sub1bean.getId() %>','li_<%=menu_num %>')">
												<i class="<%=sub1bean.getIcon() %>"></i>
												<%=sub1bean.getName() %>
											</a>
										</li> 
										<%}
					    			} %>
									</ul>
								</li>
				    			<%}else{ //没有子菜单%>
				    				  <li id="li_<%=bean.getId() %>">
										<a href="javascript:void(0)" onclick="menuClick('${ctx}<%=bean.getHref() %>','<%=bean.getName() %>','li_<%=bean.getId() %>','li_<%=bean.getId() %>')">
											<i class="<%=bean.getIcon() %>"></i>
											<span class="menu-text"> <%=bean.getName() %> </span>
										</a>
									</li>  
				    			<%} %>
				    		<%i++;}
				    	}%>
					</ul><!-- /.nav-list -->

					<div class="sidebar-collapse" id="sidebar-collapse">
						<i class="icon-double-angle-left" data-icon1="icon-double-angle-left" data-icon2="icon-double-angle-right"></i>
					</div>

					<script type="text/javascript">
						try{ace.settings.check('sidebar' , 'collapsed');}catch(e){}
					</script>
				</div>

				<div class="main-content">
					<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed');}catch(e){}
						</script>

						<ul class="breadcrumb" id="breadcrumb">
							<li>
								<i class="icon-home home-icon"></i>
								<a href="${ctxStatic}/sys/user/index">首页</a>
							</li>
						</ul><!-- .breadcrumb -->
					</div>

					<div class="page-content">
						<!-- PAGE CONTENT BEGINS -->
						<iframe id="iframepage" name="iframepage" width="100%" src="${ctx}/sys/user/innerindex"
						onLoad="iFrameHeight()" frameborder="no" border="0" marginwidth="0" 
						marginheight="0" scrolling="no" allowtransparency="yes"></iframe>
						<!-- PAGE CONTENT ENDS -->
					</div><!-- /.page-content -->
				</div><!-- /.main-content -->
				
				  <div id="modal-form" class="modal" tabindex="-1">
					<div class="modal-dialog">
						<div class="modal-content">
							<div class="modal-header" style="background-color:#438eb9;color:#fff;">
								<button type="button" style="color: #fff;opacity: 100;" class="close" data-dismiss="modal">&times;</button>
								<h4 class="white bigger">请输入新密码</h4>
							</div>
							<div class="modal-body overflow-visible">
								<div class="form-group">
										旧密码：
				                     <input type="password" id="oldPassword" name="oldPassword" 
				                     class="form-control input-sm" id="pwd1" placeholder="旧密码">
				                </div>
								<div class="form-group">
										新密码：
				                     <input type="password" id="newPassword" name="newPassword" 
				                     class="form-control input-sm" id="pwd1" placeholder="新密码">
				                </div>
				                <div class="form-group">
				                	重复新密码：
				                	<input type="password" id="confirmNewPassword" name="confirmNewPassword"
				                	 class="form-control input-sm" id="pwd1" placeholder="重复新密码">
				                </div>
							</div>
							<div class="modal-footer">
								<button class="btn btn-sm" data-dismiss="modal">
									<i class="icon-remove"></i>取消
								</button>
								<button class="btn btn-sm btn-primary" onclick="savepwd()">
									<i class="icon-ok"></i>确定
								</button>
							</div>
						</div>
					</div>
				</div>	
				
				<div id="control-panel" class="modal" tabindex="-1">
				<div class="modal-dialog">
				<div class="modal-content">
						<div class="modal-header" style="background-color:#438eb9;color:#fff;">
							<button type="button" style="color: #fff;opacity: 100;" class="close" data-dismiss="modal">&times;</button>
							<h4 class="white bigger">控制面板</h4>
						</div>
						<div class="modal-body overflow-visible">
							<!-- <div class="form-group">
									<select id="skin-colorpicker" class="hide">
										<option data-skin="default" value="#438EB9">#438EB9</option>
										<option data-skin="skin-1" value="#222A2D">#222A2D</option>
										<option data-skin="skin-2" value="#C6487E">#C6487E</option>
										<option data-skin="skin-3" value="#D0D0D0">#D0D0D0</option>
									</select>
								<span>&nbsp; 选择皮肤</span>
							</div> -->
	
							<div class="form-group">
								<input type="checkbox" class="form-control ace ace-checkbox-2" id="ace-settings-navbar" />
								<label class="lbl" for="ace-settings-navbar"><span style="margin-left:15px;">固定导航条</span></label>
							</div>
	
							<div class="form-group">
								<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-sidebar" />
								<label class="lbl" for="ace-settings-sidebar"><span style="margin-left:15px;">固定滑动条</span></label>
							</div>
	
							<div class="form-group">
								<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-breadcrumbs" />
								<label class="lbl" for="ace-settings-breadcrumbs"><span style="margin-left:15px;">固定面包屑</span></label>
							</div>
							<div class="form-group">
								<input type="checkbox" class="ace ace-checkbox-2" id="ace-settings-add-container" />
								<label class="lbl" for="ace-settings-add-container"><span style="margin-left:15px;">切换窄屏</span>
									<b></b>
								</label>
							</div>
							
							<div class="form-group  clearfix">
								<button class="btn btn-sm btn-primary pull-right" data-dismiss="modal">
									<i class="icon-ok"></i>确定
								</button>
							</div>
						</div>
				</div><!-- /.modal-body overflow-visible -->
				</div><!-- /.modal-content -->
				</div><!-- /.modal-dialog -->
				</div><!-- /.control-panel -->

				<!-- <div class="ace-settings-container" id="ace-settings-container">
					<div class="btn btn-app btn-xs btn-warning ace-settings-btn" id="ace-settings-btn">
						<i class="icon-cog bigger-150"></i>
					</div>
			</div>/.main-container-inner -->

			<a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
				<i class="icon-double-angle-up icon-only bigger-110"></i>
			</a>
		</div><!-- /.main-container -->
		
		<div id="modal-form1" class="modal" tabindex="-1" style="display:none;">
		<div class="modal-dialog" id="modal-dialog1">
			<div class="modal-content" style="width: 860px;">
				<div class="modal-header" style="background-color:#438eb9;color:#fff;">
					<button type="button" style="color: #fff;opacity: 100;" class="close" data-dismiss="modal">&times;</button>
					<h4 class="white bigger" id="modaltitle">管理</h4>
				</div>
				<div class="modal-body overflow-visible">
					<iframe id="iframepage1" name="iframepage1" width="830px"  height="550px" src=""
					onLoad="" frameborder="no" border="0" marginwidth="0" 
					marginheight="0" scrolling="yes" allowtransparency="yes"></iframe> 
				</div>
			</div>
		</div>
	</div>
</body>
</html>

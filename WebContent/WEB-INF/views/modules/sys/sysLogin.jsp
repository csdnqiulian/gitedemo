<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page
	import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>${fns:getConfig('productName')}</title>
<meta name="decorator" content="blank"/>
<!-- 页面基本设置禁止随意更改 -->
<meta charset="utf-8">
<meta name="author" content="forework">
<meta name="format-detection" content="telephone=no">
<meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<meta name="apple-mobile-web-app-capable" content="yes">
<meta name="mobile-web-app-capable" content="yes">
<meta name="apple-mobile-web-app-status-bar-style" content="black">
<meta name="msapplication-tap-highlight" content="no">
<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
<!-- 页面基本设置禁止随意更改 -->
<!-- 基础CSS类库可随意更改 -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/login/css/less.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/static/login/css/basic.css">
<!--[if IE 8]>
<link rel="stylesheet" type="text/css" href="css/ie8.css">
<![endif]-->
<!--[if gte IE 9]> 
<link rel="stylesheet" type="text/css" href="css/ie.css"> 
<![endif]-->
<!-- 基础CSS类库可随意更改 -->
<!-- 基础js类库可随意更改 -->
<style type="text/css">
label.error{
background:none;font-weight:normal;color:inherit;margin:0;font: bold;color: red;
}
</style>
<!-- 基础js类库可随意更改 -->
<script type="text/javascript">
$(document).keydown(function() {
    var elementObj;
    if (window.event) {
        elementObj = event.srcElement
    } else {
        elementObj = event.target;
    }
    if (event.keyCode == 13) {
    	onSubmit();
    }
});

$(document).ready(function() {
	$("#loginForm").validate({
		rules: {
			validateCode: {remote: "${pageContext.request.contextPath}/servlet/validateCodeServlet"}
		},
		messages: {
			username: {required: "请填写用户名."},password: {required: "请填写密码."},
			validateCode: {remote: "验证码不正确.", required: "请填写验证码."}
		},
		errorLabelContainer: "#messageBox",
		errorPlacement: function(error, element) {
			error.appendTo($("#loginError").parent());
		} 
	});
});
// 如果在框架或在对话框中，则弹出提示并跳转到首页
if(self.frameElement && self.frameElement.tagName == "IFRAME" || $('#left').length > 0 || $('.jbox').length > 0){
	alert('未登录或登录超时。请重新登录，谢谢！');
	top.location = "${ctx}";
}

/**
 * 提交
 */
function onSubmit(){
	$("#loginForm").submit();
}
</script>
</head>
<body>
<form id="loginForm"  action="${ctx}/login" method="post">
	<div class="wrapper" style="background-color: white1;">
		<div class="login-top">
			<div style="height: 20px; background-color: white;">
				<%-- <div style="margin-left: 160px;">
					<img
						src="${pageContext.request.contextPath}/static/login/images/log_1.jpg" />
				</div> --%>
				<div
					style="float: right; margin-top: 10px; width: 360px; font-size: 12px;">
					<span>返回首页</span> <span>|</span> <span>登录帮助</span> <span>|</span> <span>在线答疑</span>
				</div>
			</div>
			<div class="login-topBg">
				<div class="login-topBg">
					<div class="login-topStyle">
						<!--在点击注册时出现样式login-topStyle3登录框，而login-topStyle2则消失-->
						<div class="login-topStyle3" id="loginStyle"
							style="margin-top: 15px;">
							<div id="messageBox" class="alert alert-error ${empty message ? 'hide' : ''}"><button data-dismiss="alert" class="close">×</button>
								<label id="loginError" class="error">${message}</label>
							</div>
							<h3>用户平台登录</h3>
							<!--输入错误提示信息，默认是隐藏的，把display:none，变成block可看到-->
							<!-- <div class="error-information" style="display: none;">您输入的密码不正确，请重新输入</div> -->
							<div class="ui-form-item loginUsername">
								<input type="text" name="username" id="username" placeholder="用户名/手机号/密码"  class="required" value="thinkgem"/>
							</div>
							<div class="ui-form-item">
								<input type="password" id="password" name="password" placeholder="请输入密码" class="required" value="admin">
							</div>
							<div class="ui-form-item">
								<c:if test="${isValidateCodeLogin}">
								    <div class="validateCode">
										<label class="input-label mid" for="validateCode">验证码</label>
										<sys:validateCode name="validateCode" inputCssStyle="margin-bottom:0;"/>
									</div>
								</c:if>
							</div>
							<div class="login_reme">
								<label for="rememberMe">
									<input type="checkbox" style="margin-top: -1px;" id="rememberMe" name="rememberMe" ${rememberMe ? 'checked' : ''}/> 记住账号
									<a class="reme2" href="password.html">忘记密码?</a>
								</label>
							</div>
							<!-- <span class="error_xinxi" style="display: none;">您输入的密码不正确，请重新输入</span> -->
							<a class="btnStyle btn-register" onclick="onSubmit();" style="cursor:hand"> 立即登录</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="loginCen">
			<div class="login-center">
				<div class="loginCenter-moudle">
					<div class="loginCenter-moudleLeft" style="margin-right: 60px;">
						&nbsp;</div>
					<div class="loginCenter-moudleRight" style="width: 1067px;">
						<!--第一个-->
						<a class="loginCenter-mStyle loginCenter-moudle1"
							id="moudleRemove" style="display: block; width: 340px;"> <span
							class="moudle-img"><img src="${pageContext.request.contextPath}/static/login/images/login_bg_01.png"></span>
							<span class="moudle-text"> <span class="moudle-text1"
								style="font-family: '微软雅黑'">一体化战略预算 </span> <span
								class="moudle-text2" style="font-family: '微软雅黑'">Integrated
									strategic budget</span>
						</span>
						</a>
						<!--第二个-->
						<a class="loginCenter-mStyle loginCenter-moudle2"
							style="display: block; width: 357px;" id="moudleRemove2"> <span
							class="moudle-img"><img src="${pageContext.request.contextPath}/static/login/images/login_bg_02.png"></span>
							<span class="moudle-text"> <span class="moudle-text1"
								style="font-family: '微软雅黑'"> 精细化财务核算 </span> <span
								class="moudle-text2" style="font-family: '微软雅黑'">Refined
									financial accounting</span>
						</span>
						</a>
						<!--第三个-->
						<a class="loginCenter-mStyle loginCenter-moudle3"
							style="display: block;" id="moudleRemove3"> <span
							class="moudle-img"><img src="${pageContext.request.contextPath}/static/login/images/login_bg_03.png"></span>
							<span class="moudle-text"> <span class="moudle-text">
									<span class="moudle-text1" style="font-family: '微软雅黑'">独立化自主经营
								</span> <span class="moudle-text2" style="font-family: '微软雅黑'">Independent
										operation</span>
							</span>
						</span>
						</a>

					</div>
				</div>
			</div>
		</div>
		<div class="footer">
			<span class="footerText">测试版本</span>
		</div>
	</div>
 </form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
    <title>河南省税务管理系统</title>
    <link href="static/css/base.css" rel="stylesheet">
    <link href="static/css/login/login.css" rel="stylesheet">
    <link href="static/easyui/uimaker/easyui.css" rel="stylesheet">
    	
    <script type="text/javascript" src="static/jquery/jquery.min.js"></script>
	<script type="text/javascript" src="static/easyui/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="static/easyui/easyui-lang-zh_CN.js"></script>
</head>
<body>
<div class="login-hd">
		<div class="left-bg"></div>
		<div class="right-bg"></div>
		<div class="hd-inner">
			<span class="sys-name">xx省国税税源信息管理系统</span>
		</div>
	</div>
	<div class="login-bd">
		<div class="bd-inner">
			<div class="inner-wrap">
				<div class="lg-zone">
					<div class="lg-box">
						<div class="lg-label"><h4>用户登录</h4></div>
						<div id="error" class="alert alert-error">
			              <i class="iconfont">&#xe62e;</i>
			              <span id="info">请输入用户名</span>
			            </div>
						<form method="post">
							<div class="lg-username input-item clearfix">
								<i class="iconfont">&#xe60d;</i>
								<input type="text" name="username" id="username" placeholder="系统账号">
							</div>
							<div class="lg-password input-item clearfix">
								<i class="iconfont">&#xe634;</i>
								<input type="password" name="password" id="password" placeholder="系统密码">
							</div>
							<div class="lg-check clearfix">
								<div class="input-item">
									<i class="iconfont">&#xe633;</i>
									<input type="text" name="captcha" id="captcha" placeholder="验证码">
								</div>
								<span class="check-code"><img id="captchaimg" src="kaptcha" title="点击刷新"></span>
							</div>
							<div class="tips clearfix">
								<label><input type="checkbox" id="rem" name="remUser" checked="checked">记住用户名</label>
								<a href="javascript:;" class="forget-pwd">忘记密码？</a>
							</div>
							<div class="enter">
								<a href="javascript:void(0);" class="supplier">登录</a>
								<a href="javascript:void(0);" class="purchaser">重置</a>
							</div>
						</form>
					</div>
				</div>
				<div class="lg-poster"></div>
			</div>
		</div>
	</div>
	<div class="login-ft">
		<div class="ft-inner">
			<div class="about-us">
				<a href="javascript:;">关于我们</a>
				<a href="javascript:;">法律声明</a>
				<a href="javascript:;">服务条款</a>
				<a href="javascript:;">联系方式</a>
			</div>
			<div class="address">地址：湖北省洪山区光谷国际广场B座24楼&nbsp;邮编：430070&nbsp;&nbsp;Copyright&nbsp;©&nbsp;2015&nbsp;-&nbsp;2017&nbsp;专注系统集成&nbsp;版权所有</div>
			<div class="other-info">建议使用IE8及以上版本浏览器&nbsp;京ICP备&nbsp;17003078号&nbsp;E-mail：service@zhidisoft.com</div>
		</div>
	</div>
	<div  id="dialog" style="padding:20px;line-height: 1.5em;"></div>
</body>
<script type="text/javascript">

	$("#captchaimg").bind("click", function(){
		changeCaptch();
	})

	var changeCaptch = function () {
        $("#captchaimg").attr("src", "kaptcha?date="+new Date());
    }
	
	var login = function(){
		var username = $("#username").val();
		var password = $("#password").val();
		var captcha = $("#captcha").val();
		var rem = $("#rem").val();
		
		if (!username) {
			$("#info").html("请输入用户名");
			return;
		} else {
			$("#info").html("")
		}
		
		if (!password) {
			$("#info").html("请输入密码");
			return;
		} else {
			$("#info").html("");
		}
		
		if (!captcha) {
			$("#info").html("请输入验证码");
			return;
		} else {
			$("#info").html("");
		}

		//验证验证码ajax请求
		//回调函数的参数代表
		//js代码中json格式的数据就认为是一个对象
		var validateCaptcha = $.get("validateCaptcha", {"captcha":captcha}, function (result) {
		    //验证失败
			if (!result.success) {
                $("#info").html("验证码错误");
                changeCaptch();
			}
        }, "json")

		//如果验证码正确，进行登录的ajax请求
		$.when(validateCaptcha).done(function (result) {
			if (result.success) {
			    $.post("login", {"username":username, "password":password, "rem":rem}, function (result) {
					if (result.success) {
					    location.href = "manage/index.jsp";
					} else {
                        $("#info").html(result.msg);
					}
                })
			}
        })
	}

	$(function () {
        var username = document.cookie.split("=")[1]
        //放入cookie值
        $("#username").val(username)
    })

    $(".supplier").bind("click", function () {
		login();
    })

    $("form").keydown(function() {
        if (event.keyCode == "13") {//keyCode=13是回车键
            login();
        }
    });

</script>
</html>
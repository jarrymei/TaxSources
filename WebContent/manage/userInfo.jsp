<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2017/6/9
  Time: 0:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>个人信息</title>
    <link rel="stylesheet" href="static/css/base.css">
    <link rel="stylesheet" href="static/css/platform.css">
    <link rel="stylesheet" href="static/easyui/uimaker/easyui.css">
</head>
<body>
<style>
    #edit_password_form {
        width: 400px;
        height: 200px;
        position: absolute;
        top: 50%;
        left: 50%;
        margin-top: -70px;
        margin-left: -173px;
    }

    #edit_password_form .form_contant {
        width: 100%;
        height: 26px;
        margin-top: 10px;
    }


    #edit_password_form .form_contant label {
        width: 20%;
        text-align: right;
        /*lable标签默认情况下width是不可用的，需要设置*/
        display:inline-block;
    }

    #edit_password_form .form_contant input {
        width: 50%;
        margin-left: 10px;
    }
    #edit_password_form .form_contant span {
        width: 30%;
        color: red;
    }

</style>
<form id="edit_password_form">
    <div class="form_contant">
        <label>用户名:</label><input type="text" name="username" value="${user.username}">
    </div>
    <div class="form_contant">
        <label>email:</label><input type="text" name="email" value="${user.email}" >
    </div>
</form>
</body>
</html>

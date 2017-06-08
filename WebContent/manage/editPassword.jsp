<%--
  Created by IntelliJ IDEA.
  User: lx
  Date: 2017/6/8
  Time: 13:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" type="text/css" href="static/easyui/uimaker/easyui.css">
    <link rel="stylesheet" type="text/css" href="static/easyui/uimaker/icon.css">
    <script type="text/javascript" src="static/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="static/easyui/jquery.easyui.min.js"></script>
    <style>
        #edit_password_form {
            width: 400px;
            height: 20px;
            position: absolute;
            top: 50%;
            left: 50%;
            margin-top: -70px;
            margin-left: -99px
        }

    </style>
</head>
<body>
<form id="edit_password_form">
    <div>
        <label>原密码:</label><input class="easyui-validatebox" type="password" id="password" name="password" placeholder="原密码"><span></span>
    </div>
    <div>
        <label>新密码:</label><input class="easyui-validatebox" type="password" id="newPassword" name="newPassword" placeholder="新密码"><span></span>
    </div>
    <div>
        <label>确认新密码:</label><input class="easyui-validatebox" type="password" id="validatePassword" name="validatePassword" placeholder="确认新密码" ><span></span>
    </div>
    <div>
        <button type="button">提交</button>
    </div>

</form>
<script>
    $("#edit_password_form button").bind("click", function(){
        var password = $("#password").val();
        var newPassword = $("#newPassword").val();
        var validatePassword = $("#validatePassword").val();
        if (!password) {
            showValidate("password", "请填写密码")
            return;
        } else {
            hideValidate("password");
        }
        if (!newPassword) {
            showValidate("newPassword", "请填写新密码")
            return;
        } else {
            hideValidate("newPassword");
        }
        if (validatePassword != newPassword) {
            showValidate("validatePassword", "确认密码不同");
            return;
        } else {
            hideValidate("validatePassword");
        }

        var validatePwd = $.post("validatePassword.do", {"password" : password}, function (result) {
            if (!result.success) {
                showValidate("password", "密码错误");
            }
        })

        $.when(validatePwd).done(function (result) {
            if (result.success) {
                $.post("editPassword.do", {"newPassword":newPassword}, function (result) {
                    if (result.success) {
                        parent.alert(result.msg);
                        parent.$("#topWindow").window("close")
                        location.href = "logout.do";
                    } else {
                        parent.alert(result.msg);
                    }
                })
            }
        })

    })

    var showValidate = function(id, msg) {
        $("#"+id+"").next().html(msg).css("color", "red");
    }

    var hideValidate = function (id, msg) {
        $("#"+id+"").next().html("");
    }


</script>
</body>
</html>

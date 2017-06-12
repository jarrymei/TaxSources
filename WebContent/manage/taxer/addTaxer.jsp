<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2017/6/10
  Time: 17:14
  To change this template use File | Settings | File Templates.
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>添加办税专员</title>
    <link rel="stylesheet" type="text/css" href="static/css/base.css" >
    <link rel="stylesheet" type="text/css" href="static/easyui/uimaker/easyui.css">
    <link rel="stylesheet" type="text/css" href="static/easyui/uimaker/icon.css">
    <link rel="stylesheet" type="text/css" href="static/css/edit.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.bootcss.com/webuploader/0.1.1/webuploader.css">
</head>
<body>
<script type="text/javascript" src="static/jquery/jquery.min.js"></script>
<script type="text/javascript" src="static/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="static/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="static/js/calendar.js"></script>
<div class="container">
    <div class="content">
        <div title="办税专员信息" data-options="closable:false" class="basic-info">
            <div class="column"><span class="current">添加办税专员信息</span></div>
            <form id="addForm" method="post">
                <table class="kv-table" id="addPayer">
                    <tbody>
                    <tr>
                        <td class="kv-label">办税专员编号</td>
                        <td class="kv-content"><input type="text" name="taxerCode" class="easyui-numberbox" data-options="required:true,min:1000,max:999999" placeholder="办税专员编号"></td>
                        <td class="kv-label">办税专员名称</td>
                        <td class="kv-content"><input type="text" name="taxerName" class="easyui-validatebox" data-options="required:true,validType:'maxLength[4]'" placeholder="办税专员名称"></td>
                    </tr>
                    <tr>
                        <td class="kv-label">手机号</td>
                        <td class="kv-content"><input type="text" name="mobile" class="easyui-validatebox" data-options="required:true,validType:'mobile'" placeholder="手机号"></td>
                        <td class="kv-label">地址</td>
                        <td class="kv-content"><input type="text" name="address" class="easyui-validatebox" placeholder="地址" data-options="validType:'maxLength[64]'"></td>
                    </tr>
                    <tr>
                        <td class="kv-label">性别</td>
                        <td class="kv-content">
                            <input type="radio" name="sex" class="easyui-validatebox" placeholder="男" checked value="1"> 男
                            <input type="radio" name="sex" class="easyui-validatebox" placeholder="女" value="0"> 女
                        </td>
                        <td class="kv-label">生日</td>
                        <td class="kv-content"><input type="text" name="birthday" class="easyui-validatebox" placeholder="生日" data-options="validType:'date'"></td>
                    </tr>
                    <tr>
                        <td class="kv-label">Email</td>
                        <td class="kv-content"><input type="text" name="email" class="easyui-validatebox" placeholder="Email" data-options="validType:'email'"></td>
                        <td class="kv-label">所属税务机关</td>
                        <td class="kv-content">
                            <select name="organId" id="selectOrgan" class="easyui-validatebox" data-options="validType:'choose'">
                                <option value="-1">请选择税务机关</option>
                                <c:forEach items="${requestScope.list }" var="organ">
                                    <option value="${organ.id }">${organ.organName }</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    </tbody>

                </table>
            </form>
        </div>
        <div class="btn-selection">
            <a href="javascript:void(0);" class="easyui-linkbutton save-btn" id="savePayer" data-options="selected:true">保存</a>
            <a href="javascript:void(0);" class="easyui-linkbutton reset-btn" id="reset" data-options="selected:true">重置</a>
        </div>
    </div>
</div>
<script>

    $.extend($.fn.validatebox.defaults.rules, {
        choose: {
            validator: function(value,param){
                return value != -1;
            },
            message: '请选择税务机关'
        }
    });

    $("#savePayer").bind("click", function () {
        var state = $("#addForm").form('validate');
        $('#addForm').form('submit', {
            url: 'taxer/addTaxer.do',
            onSubmit: function(){
                var isValid = $("#addForm").form('validate');
                if (!isValid){
                    $.messager.progress('close');
                }
                return isValid;
            },
            success: function(data){
                $.messager.alert('My Title',"添加成功",'info', function () {
                    parent.$("#topWindow").window("close")
                    $('#dg').datagrid('reload');
                });
            }
        });
    })


    
</script>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: lx
  Date: 2017/6/8
  Time: 12:31
  To change this template use File | Settings | File Templates.
--%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>纳税人管理</title>

    <link href="static/css/base.css" rel="stylesheet">
    <link rel="stylesheet" href="static/easyui/uimaker/easyui.css">
    <link rel="stylesheet" type="text/css" href="static/easyui/uimaker/icon.css">
    <link rel="stylesheet" href="static/css/taxpayer.css">
</head>
<body>
<div class="container">
    <table id="dg" style="width:100%;"></table>
    <div id="tb" style="padding:0 30px;">
        纳税人识别号: <input class="easyui-textbox" type="text" name="payerCode" style="width:166px;height:35px;line-height:35px;"/>
        纳税人名称: <input class="easyui-textbox" type="text" name="payerName" style="width:166px;height:35px;line-height:35px;"/>
        <a href="javascript:void(0);" id="searchBtn" class="easyui-linkbutton" iconCls="icon-search" data-options="selected:true">查询</a>
        <a href="javascript:void(0);" id="setBtn" class="easyui-linkbutton" iconCls="icon-reload">重置</a>
        <a href="javascript:void(0);" id="addBtn" class="easyui-linkbutton" iconCls="icon-add">添加纳税人</a>
    </div>
</div>
<script type="text/javascript" src="static/jquery/jquery.min.js"></script>
<script type="text/javascript" src="static/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="static/easyui/easyui-lang-zh_CN.js"></script>
<script type="text/javascript">

    $("#dg").datagrid({
        url:"",
        columns:[[
            {field:'code',title:'Code',width:100},
            {field:'name',title:'Name',width:100},
            {field:'price',title:'Price',width:100,align:'right'}
        ]]
    })


</script>
<script type="text/javascript">
    //为搜索按钮添加事件处理函数
    //为重置按钮添加事件处理函数
    //为添加纳税人添加事件处理函数
    $(function(){
        $("#addBtn").on("click",function(e){
            openTopWindow({
                width : 750,
                height : 600,
                title : "新增纳税人",
                url : "manage/addTaxpayer.jsp"
            });
        });

        /**
         *打开在父窗口中打开window
         */
        function openTopWindow(options){
            options = !options ? {} :options;
            options.width = !options.width ? 500 : options.width;
            options.height = !options.height ? 400 : options.height;
            options.url = !options.url ? "404.html" : options.url;
            options.title = !options.title ? "" : options.title;

            parent.$("#topWindow").window({
                title : options.title,
                width: options.width,
                height: options.height,
                content : "<iframe scrolling='no' frameborder='0' border='0' height='100%' width='100%' src='"+options.url+"'></iframe>",
                modal:true,
                resizable:false,
                collapsible:false
            });
        }

    });
</script>
</body>
</html>

<%--
  Created by IntelliJ IDEA.
  User: 梅佳杰
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
        纳税人识别号: <input type="text" name="payerCode" id="payerCode" style="width:166px;height:35px;line-height:35px;"/>
        纳税人名称: <input type="text" name="payerName" id="payerName" style="width:166px;height:35px;line-height:35px;"/>
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
        url:"taxpayer/listTaxpayer.do",
        loadMsg:'数据加载中...',
        pagination:'true',
        toolbar: '#tb',
        columns:[[
            {field:'payerCode',title:'纳税人识别号',width:100},
            {field:'payerName',title:'纳税人名称',width:100},
            {field:'taxOrganId',title:'所属税务机关',width:100},
            {field:'industryId',title:'行业',width:100},
            {field:'legalPerson',title:'法人代表',width:100},
            {field:'legalIdCard',title:'身份证号码',width:130},
            {field:'finaceName',title:'财务人员',width:100},
            {field:'finaceIdCard',title:'财务人员身份号码',width:130},
            {field:'taxerName',title:'办税人员',width:100},
            {field:'recordDate',title:'录入日期',width:100},
            {field:'option',title:'操作',width:150,align:'center',
                formatter: function(value,row,index){
                    return "<a href='javascript:void(0)' onclick='info("+row.id+")'>查看</a>&nbsp;" +
                        "<a href='javascript:void(0)' onclick='edit("+row.id+")'>修改</a>&nbsp;" +
                        "<a href='javascript:void(0)' onclick='del("+row.id+")'>删除</a>&nbsp;" +
                        "<a href='javascript:void(0)' onclick='addTask("+row.id+")'>新增任务</a>";
                }
            }
        ]]
    })

    //点击查询
    $("#searchBtn").bind("click", function () {
        $("#dg").datagrid("load", {
            "payerCode":$("input[name='payerCode']").val(),
            "payerName":$("input[name='payerName']").val()
        })
    })
    
    //重置
    $("#setBtn").bind("click", function () {
        $("#payerCode").val("");
        $("#payerName").val("");
    })

    //添加纳税人
    $("#addBtn").bind("click", function () {
        openTopWindow({"title":"添加纳税人", "url":"taxpayer/toAddTaxpayer.do", "width":"800", "height":"600"})
    })

    //修改纳税人
    var edit = function (id) {
        openTopWindow({"title":"修改纳税人信息" , "url":"taxpayer/toEditTaxPayer.do?id="+id, "width":"800", "height":"600"})
    }

    //新建任务
    function addTask(id){
        parent.addTab("调查任务录入","taxsource/toAddTask.do?id="+id)
    }

    var del = function (id) {
        $.messager.confirm("提示信息", "确定删除?", function (r) {
            if (r) {
                $.post("taxpayer/deleteTaxPayer.do", {"id":id}, function (result) {
                    if (result.success) {
                        $.messager.alert('提示信息',"删除成功",'info', function () {
                            $('#dg').datagrid('reload');
                        });
                    }
                }, "json")
            }
        })
    }

    //为搜索按钮添加事件处理函数
    //为重置按钮添加事件处理函数
    //为添加纳税人添加事件处理函数

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

</script>
</body>
</html>

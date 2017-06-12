<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <base href="<%=basePath%>">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>修改纳税人</title>
    <link href="static/css/base.css" rel="stylesheet">
    <link rel="stylesheet" href="static/easyui/uimaker/easyui.css">
    <link rel="stylesheet" type="text/css" href="static/easyui/uimaker/icon.css">
    <link href="static/css/edit.css" rel="stylesheet">
    <script type="text/javascript" src="static/jquery/jquery.min.js"></script>
    <script type="text/javascript" src="static/easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="static/js/calendar.js"></script>
    <script type="text/javascript" src="static/easyui/easyui-lang-zh_CN.js"></script>
</head>
<body>
<form id="editForm">
    <div class="container">
        <div class="content">
            <div title="纳税人信息" data-options="closable:false" class="basic-info">
                <div class="column"><span class="current">修改纳税人信息</span></div>
                <table class="kv-table">
                    <tbody>
                    <tr>
                        <input type="hidden" name="id" value="${taxPayer.id}" >
                        <td class="kv-label">纳税人识别号</td>
                        <td class="kv-content"><input type="text" name="payerCode" value="${taxPayer.payerCode}"  class="easyui-validatebox" data-options="required:true" placeholder="纳税人识别号" readonly></td>
                        <td class="kv-label">纳税人名称</td>
                        <td class="kv-content"><input type="text" name="payerName" value="${taxPayer.payerName}"  class="easyui-validatebox" data-options="required:true" placeholder="纳税人名称" readonly></td>
                    </tr>
                    <tr>
                        <td class="kv-label">生产经营地址</td>
                        <td class="kv-content"><input type="text" name="bizAddress" value="${taxPayer.bizAddress}"  class="easyui-validatebox" data-options="required:true" placeholder="生产经营地址"></td>
                        <td class="kv-label">生成经营地电话</td>
                        <td class="kv-content"><input type="text" name="bizAddressPhone" value="${taxPayer.bizAddressPhone}"   data-options="required:true" placeholder="生产经营地电话"></td>
                    </tr>
                    <tr>
                        <td class="kv-label">所属税务机关</td>
                        <td class="kv-content">
                            <select name="taxOrganId" class="easyui-validatebox" data-options="validType:'choose'">
                                <option value="-1">请选择所属税务机关</option>
                                <c:forEach items="${taxOrganList}" var="taxOrgan">
                                    <option value="${taxOrgan.id}" <c:if test="${taxPayer.taxOrganId eq taxOrgan.id}">selected</c:if>>${taxOrgan.organName}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td class="kv-label">行业</td>
                        <td class="kv-content">
                            <select name="industryId" class="easyui-validatebox" data-options="validType:'choose'">
                                <option value="-1">请选择纳税人行业</option>
                                <c:forEach items="${industryList}" var="industry">
                                    <option value="${industry.id}" <c:if test="${taxPayer.industryId eq industry.id}"> selected</c:if>>${industry.industryName} </option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="kv-label">生产经营范围</td>
                        <td class="kv-content">
                            <input type="text" name="bizScope" value="${taxPayer.bizScope}"  class="easyui-validatebox" data-options="required:true" placeholder="生产经营范围">
                        </td>
                        <td class="kv-label">票种核定</td>
                        <td class="kv-content" >
                            <select name="invoiceType" class="easyui-validatebox" data-options="validType:'choose'">
                                <option value="-1">请选择发票种类</option>
                                <option value="1" <c:if test="${taxPayer.invoiceType eq '1'}">selected</c:if>>增值税普通发票</option>
                                <option value="2" <c:if test="${taxPayer.invoiceType eq '2'}">selected</c:if>>增值税专用发票</option>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="kv-label">法人代表人</td>
                        <td class="kv-content">
                            <input type="text" name="legalPerson" value="${taxPayer.legalPerson}" placeholder="法人姓名">
                        </td>
                        <td class="kv-label">法人身份证号</td>
                        <td class="kv-content">
                            <input type="text" name="legalIdCard" value="${taxPayer.legalIdCard}" placeholder="法人代表身份证号码">
                        </td>
                    </tr>
                    <tr>
                        <td class="kv-label">主管财务</td>
                        <td class="kv-content">
                            <input type="text" name="finaceName" value="${taxPayer.finaceName}" placeholder="主管财务">
                        </td>
                        <td class="kv-label">财务身份证号</td>
                        <td class="kv-content">
                            <input type="text" name="finaceIdCard" value="${taxPayer.finaceIdCard}" placeholder="财务身份证号">
                        </td>
                    </tr>
                    <tr>
                        <td class="kv-label">办税专员</td>
                        <td class="kv-content">
                            <select name="taxerName" class="easyui-validatebox" data-options="validType:'choose'">
                                <option value="-1">请选择办税专员</option>
                                <c:forEach items="${taxerList}" var="taxer">
                                    <option value="${taxer.taxerName}" <c:if test="${taxPayer.taxerName eq taxer.taxerName}">selected</c:if>>${taxer.taxerName}</option>
                                </c:forEach>
                            </select>
                        </td>
                        <td class="kv-label">录入日期</td>
                        <td class="kv-content">${taxPayer.recordDate} </td>
                        <input type="hidden" name="recordDate" value="${taxPayer.recordDate}">
                    </tr>
                    </tbody>
                </table>
            </div>
            <div class="btn-selection">
                <a href="javascript:void(0);" class="easyui-linkbutton save-btn" id="savaBtn" data-options="selected:true">保存</a>
                <a href="javascript:void(0);" class="easyui-linkbutton reset-btn" data-options="selected:true">重置</a>
            </div>
        </div>
    </div>
</form>
    <script>
        $.extend($.fn.validatebox.defaults.rules, {
            choose: {
                validator: function(value,param){
                    return value != -1;
                },
                message: '请选择'
            }
        });
        
        $("#savaBtn").bind("click", function () {
            var state = $("#editForm").form("validate");
            if (state) {
                $.post("taxpayer/editTaxPayer.do", $("#editForm").serialize(), function (result) {
                    if (result.success) {
                        $.messager.alert("提示信息", ""+ result.msg +"", "info", function () {
                            parent.$("#topWindow").window("close");
                        })
                    } else {
                        $.messager.alert("提示信息", ""+ result.msg +"", "info");
                    }
                }, "json")
            }
        })
        
    </script>
</body>
</html>
<%--

<script type="text/javascript">
    $("input[name=executeTime]").datebox({
        formatter: easyUIFormater,
        parser: easyUIparser
    });
</script>
--%>

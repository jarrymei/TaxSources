<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <title>任务录入</title>
    <link rel="stylesheet" type="text/css" href="static/css/base.css" >
    <link rel="stylesheet" type="text/css" href="static/easyui/uimaker/easyui.css">
    <link rel="stylesheet" type="text/css" href="static/easyui/uimaker/icon.css">
    <link rel="stylesheet" type="text/css" href="static/css/edit.css">
</head>
<body>
<div class="container">
    <div class="content">
        <div title="纳税人信息" data-options="closable:false" class="basic-info">
            <div class="column"><span class="current">纳税人基本信息</span></div>
            <table class="kv-table">
                <tbody>
                <tr>
                    <td class="kv-label">纳税人识别号</td>
                    <td class="kv-content"><input type="text" name="payerCode" id="payerCode" value="${result.payerCode}"
                                                  placeholder="请输入纳税人识别号，获取纳税人信息"></td>
                    <td class="kv-label">纳税人名称</td>
                    <td class="kv-content" id="payerName">${result.payerName}</td>
                    <td class="kv-label">生产经营地址</td>
                    <td class="kv-content" id="bizAddress">${result.bizAddress}区</td>
                </tr>
                <tr>
                    <td class="kv-label">所属税务机关</td>
                    <td class="kv-content" id="organName">${result.organName}</td>
                    <td class="kv-label">行业</td>
                    <td class="kv-content" id="industryName">${result.industryName}</td>
                    <td class="kv-label">经营范围</td>
                    <td class="kv-content" id="bizScope">${result.bizScope}</td>
                </tr>
                <tr>
                    <td class="kv-label">票种核定</td>
                    <td class="kv-content" id="invoiceType">${result.invoiceType}</td>
                    <td class="kv-label">法人代表人</td>
                    <td class="kv-content" id="legalPerson">${result.legalPerson}</td>
                    <td class="kv-label">法人身份证号</td>
                    <td class="kv-content" id="legalIdCard">${result.legalIdCard}</td>
                </tr>
                <tr>
                    <td class="kv-label">主管财务</td>
                    <td class="kv-content" id="finaceName">${result.finaceName}</td>
                    <td class="kv-label">财务身份证号</td>
                    <td class="kv-content" id="finaceIdCard">${result.finaceIdCard}</td>
                    <td class="kv-label">税收管理员</td>
                    <td class="kv-content" id="shuishou">${result.taxerName}</td>
                </tr>
                <tr>
                    <td class="kv-label">办税人员</td>
                    <td class="kv-content" id="taxerName">${result.taxerName}</td>
                    <td class="kv-label">录入日期</td>
                    <td class="kv-content" id="recordDate">${result.recordDate}</td>
                    <td class="kv-label">录入人</td>
                    <td class="kv-content" id="username">${result.username}</td>
                </tr>
                </tbody>
            </table>
            <div class="column"><span class="current">任务信息</span></div>
            <table class="kv-table">
                <tbody>
                <tr>
                    <td class="kv-label">任务名称</td>
                    <td class="kv-content"><input type="text" name="taskName" placeholder="请输入任务名称"></td>
                    <td class="kv-label">下达部门</td>
                    <td class="kv-content"><input type="text" name="publishOrgan" placeholder="请输入下达部门"></td>
                    <td class="kv-label">批准人</td>
                    <td class="kv-content"><input type="text" name="approver" placeholder="请输入批准人"></td>
                </tr>
                <tr>
                    <td class="kv-label">执行人</td>
                    <td class="kv-content"><input type="text" name="executer" placeholder="请输入执行人"></td>
                    <td class="kv-label">执行时间</td>
                    <td class="kv-content"><input type="text" name="executeTime"></td>
                    <td class="kv-label">风险登记</td>
                    <td class="kv-content">
                        <select>
                            <option>请选择</option>
                            <option>高</option>
                            <option>中</option>
                            <option>低</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td class="kv-label">任务执行情况</td>
                    <td class="kv-content">
                        <textarea rows="3" style="width: 90%;"></textarea>
                    </td>
                    <td class="kv-label">调查结论和意见</td>
                    <td class="kv-content" colspan="3">
                        <textarea rows="3" style="width: 90%;"></textarea>
                    </td>
                </tr>
                </tbody>
            </table>
            <div class="column">
                <span class="current">图片信息</span>
            </div>
            <table class="kv-table">
                <tbody>
                <tr>
                    <td class="kv-label">图片信息</td>
                    <td class="kv-content">
                        <input type="file" name="images">
                    </td>
                    <td class="kv-label">图片说明</td>
                    <td class="kv-content" colspan="3">
                        <input type="text" name="imageNote" style="width:80%" placeholder="请输入图片描述信息">
                    </td>
                </tr>
                <tr>
                    <td class="kv-label">图片信息</td>
                    <td class="kv-content">
                        <input type="file" name="images">
                    </td>
                    <td class="kv-label">图片说明</td>
                    <td class="kv-content" colspan="3">
                        <input type="text" name="imageNote" style="width:80%" placeholder="请输入图片描述信息">
                    </td>
                </tr>
                <tr>
                    <td class="kv-label">图片信息</td>
                    <td class="kv-content">
                        <input type="file" name="images">
                    </td>
                    <td class="kv-label">图片说明</td>
                    <td class="kv-content" colspan="3">
                        <input type="text" name="imageNote" style="width:80%" placeholder="请输入图片描述信息">
                    </td>
                </tr>
                </tbody>
            </table>
            <div class="column">
                <span class="current">视频信息</span>
            </div>
            <table class="kv-table">
                <tbody>
                <tr>
                    <td class="kv-label">视频信息</td>
                    <td class="kv-content">
                        <input type="file" name="vedio">
                    </td>
                    <td class="kv-label">视频说明</td>
                    <td class="kv-content" colspan="3">
                        <input type="text" name="imageNote" style="width:80%" placeholder="请输入视频描述信息">
                    </td>
                </tr>
                <tr>
                    <td class="kv-label">视频信息</td>
                    <td class="kv-content">
                        <input type="file" name="vedio">
                    </td>
                    <td class="kv-label">视频说明</td>
                    <td class="kv-content" colspan="3">
                        <input type="text" name="imageNote" style="width:80%" placeholder="请输入视频描述信息">
                    </td>
                </tr>
                <tr>
                    <td class="kv-label">视频信息</td>
                    <td class="kv-content">
                        <input type="file" name="vedio">
                    </td>
                    <td class="kv-label">视频说明</td>
                    <td class="kv-content" colspan="3">
                        <input type="text" name="imageNote" style="width:80%" placeholder="请输入视频描述信息">
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="btn-selection">
            <a href="javascript:void(0);" class="easyui-linkbutton save-btn" data-options="selected:true">保存</a>
            <a href="javascript:void(0);" class="easyui-linkbutton reset-btn" data-options="selected:true">重置</a>
        </div>
    </div>
</div>

</body>
</html>
<script type="text/javascript" src="static/jquery/jquery.min.js"></script>
<script type="text/javascript" src="static/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="static/js/calendar.js"></script>
<script type="text/javascript">

    $("#payerCode").bind("change", function () {
        reload();
    })


    var reload = function () {
        var payerCode = $("#payerCode").val();
        if (!payerCode) {
            return;
        }
        $.post("taxsource/ajaxTaxPayer.do", {"payerCode":payerCode}, function (result) {
            if (result) {
                $("#payerName").html(result.payerName);
                $("#bizAddress").html(result.bizAddress);
                $("#organName").html(result.organName);
                $("#industryName").html(result.industryName);
                $("#bizScope").html(result.bizScope);
                $("#legalPerson").html(result.legalPerson);
                $("#legalIdCard").html(result.legalIdCard);
                $("#finaceName").html(result.finaceName);
                $("#finaceIdCard").html(result.finaceIdCard);
                $("#shuishou").html(result.taxerName);
                $("#taxerName").html(result.taxerName);
                $("#recordDate").html(result.recordDate);
                $("#username").html(result.username);
            } else {
                $.messager.alert('提示信息','没有找到记录！');
                $("#payerName").html(result.payerName);
                $("#bizAddress").html("");
                $("#organName").html("");
                $("#industryName").html("");
                $("#bizScope").html("");
                $("#legalPerson").html("");
                $("#legalIdCard").html("");
                $("#finaceName").html("");
                $("#finaceIdCard").html("");
                $("#shuishou").html("");
                $("#taxerName").html("");
                $("#recordDate").html("");
                $("#username").html("");
            }

        }, "json");
    }



    $("input[name=executeTime]").datebox({
        formatter: easyUIFormater,
        parser: easyUIparser
    });
</script>

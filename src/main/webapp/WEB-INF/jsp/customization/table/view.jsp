<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../../common/head.jsp"/>
    <link href="${ctx}/resources/css/customization/table/customization.table.css" rel="stylesheet">
    <link href="${ctx}/resources/css/customization/table/table.css" rel="stylesheet">
    <script type="text/javascript" src="${ctx}/resources/js/customization/table/customization.table.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/customization/table/table.js"></script>
    <title>查询界面</title>
</head>
<body>
<input hidden view-id="${viewId}" id="viewId">
<div class="table-container">
    <div class="table-search-container">
        <div class="field">
            用户名称：<input>
        </div>
        <div class="field">
            用户名称：<input>
        </div>
        <div class="field">
            用户名称：<input>
        </div>
        <div class="field">
            用户名称：<input>
        </div>
        <div class="field">
            用户名称：<input>
        </div>
        <div class="field">
            用户名称：<input>
        </div>
        <div class="btn">
            <button class="layui-btn layui-btn-sm">查询</button>
            <button class="layui-btn layui-btn-sm">重置</button>
        </div>

    </div>
    <div class="table-tools">
        <i title="新增" class="layui-icon layui-icon-add-circle-fine"
           style="color:#009688;font-size: 30px;margin-right: 10px;cursor: pointer;"></i>
        <i title="删除" class="layui-icon layui-icon-delete"
           style="color:#009688;font-size: 30px;margin-right: 10px;cursor: pointer;"></i>
        <i title="修改" class="layui-icon layui-icon-edit"
           style="color:#009688;font-size: 30px;margin-right: 10px;cursor: pointer;"></i>
        <i title="数据导入" class="layui-icon layui-icon-upload-drag"
           style="color:#009688;font-size: 30px;float: right;margin-right: 10px;cursor: pointer;"></i>
        <i title="数据导出" class="layui-icon layui-icon-download-circle"
           style="color:#009688;font-size: 30px;float: right;margin-right: 10px;cursor: pointer;"></i>
    </div>
    <table class="table-css">
        <thead>
        <tr>
            <th class="checkbox"><input type="checkbox"></th>
            <th>测试Id</th>
            <th>测试名称</th>
            <th>测试编码</th>
            <th>测试时间</th>
            <th>测试排序</th>
            <th>测试联动</th>
        </tr>
        </thead>
        <tbody id="tbody">

        </tbody>
    </table>

    <div id="t-table"></div>
</div>
</body>
</html>

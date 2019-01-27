<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../common/head.jsp"/>
    <link href="${ctx}/resources/css/datainput/datainput.css" rel="stylesheet">
    <script type="text/javascript" src="${ctx}/resources/js/datainput/datainput.js"></script>
    <title>数据导入</title>
</head>
<body>
<div class="default-container">
    <div class="default-search-container">
        <span>表名</span><input id="table-name">
        <span>表描述</span><input id="table-desc">
        <button class="layui-btn layui-btn-sm" id="search-btn">查询</button>
        <button class="layui-btn layui-btn-sm" id="rest-btn">重置</button>
    </div>
    <div class="default-table-tools">
        <i title="新增" class="layui-icon layui-icon-add-circle-fine" id="table-add"></i>
        <i title="删除" class="layui-icon layui-icon-delete" id="table-del"></i>
        <i title="更新" class="layui-icon layui-icon-edit"id="table-update"></i>
    </div>
    <table class="layui-table">
        <colgroup>
            <col width="35px">
            <col width="15%">
            <col width="15%">
            <col width="70%">
            <col width="35px">
        </colgroup>
        <thead>
        <tr>
            <th><input type="checkbox" class="check-all"></th>
            <th>表名</th>
            <th>导入描述</th>
            <th>导入字段</th>
            <th>导入</th>
        </tr>
        </thead>
        <tbody id="tbody">
        <tr>
            <td><input type="checkbox"></td>
            <td>iop_sys_user</td>
            <td>系统用户表</td>
            <td>user_id,username,nickname,password</td>
            <td><i title="数据导入" class="layui-icon layui-icon-upload-drag" input-id="234"></i>
            </td>
        </tr>
        <tr>
            <td><input type="checkbox"></td>
            <td>iop_sys_user</td>
            <td>系统用户表</td>
            <td>user_id,username,nickname,password</td>
            <td><i title="数据导入" class="layui-icon layui-icon-upload-drag" input-id="567"></i>
            </td>
        </tr>
        </tbody>
    </table>
    <div class="table-page" id="page-id" style="text-align: center"></div>
</div>
</body>
</html>

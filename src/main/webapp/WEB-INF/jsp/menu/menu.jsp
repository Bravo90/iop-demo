<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../common/head.jsp"/>
    <link href="${ctx}/resources/css/rbac/rbac.css" rel="stylesheet">
    <script type="text/javascript" src="${ctx}/resources/js/menu/menu.js"></script>
    <title>菜单管理界面</title>
</head>
<body>
<div class="rbac-container">
    <div class="rbac-title">菜单管理</div>
    <button id="menu-add" class="layui-btn layui-btn-sm">新增菜单</button>
    <table class="layui-table">
        <colgroup>
            <col width="20%">
            <col width="20%">
            <col width="30%">
            <col width="30%">
        </colgroup>
        <thead>
        <tr>
            <th>菜单ID</th>
            <th>菜单名称</th>
            <th>菜单地址</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody id="tbody">
        </tbody>
    </table>
</div>
</body>
</html>

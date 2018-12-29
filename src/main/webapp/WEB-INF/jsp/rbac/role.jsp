<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../common/head.jsp"/>
    <link href="${ctx}/resources/css/rbac/rbac.css" rel="stylesheet">
    <script type="text/javascript" src="${ctx}/resources/js/rbac/role.js"></script>
    <title>角色管理界面</title>
</head>
<body>
<div class="rbac-container">
    <div class="rbac-title">角色管理</div>
    <button id="role-add" class="layui-btn layui-btn-sm">新增角色</button>

    <table class="layui-table">
        <colgroup>
            <col width="10%">
            <col width="20%">
            <col width="45%">
            <col width="25%">
        </colgroup>
        <thead>
        <tr>
            <th>角色ID</th>
            <th>角色名称</th>
            <th>角色描述</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody id="tbody">
        </tbody>
    </table>
    <div id="user-page" class="rbac-page"></div>
</div>
</body>
</html>
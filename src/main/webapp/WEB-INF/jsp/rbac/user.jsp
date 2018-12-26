<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../common/head.jsp"/>
    <link href="${ctx}/resources/css/rbac/rbac.css" rel="stylesheet">
    <script type="text/javascript" src="${ctx}/resources/js/rbac/user.js"></script>
    <title>用户管理界面</title>
</head>
<body class="bg-image">
<div class="rbac-container">
    <div class="rbac-title">用户管理</div>
    <button id="user-add" class="layui-btn layui-btn-sm">新增用户</button>
    <table class="layui-table">
        <thead>
        <tr>
            <th>用户ID</th>
            <th>用户名</th>
            <th>昵称</th>
            <th>用密码</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody id="tbody">

        </tbody>
    </table>
</div>
</body>
</html>
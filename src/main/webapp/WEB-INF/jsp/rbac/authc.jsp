<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../common/head.jsp"/>
    <link href="${ctx}/resources/css/rbac/rbac.css" rel="stylesheet">
    <script type="text/javascript" src="${ctx}/resources/js/rbac/authc.js"></script>
    <title>权限管理界面</title>
</head>
<body>
<div class="rbac-container">
    <div class="rbac-title">权限管理</div>
    <button id="authc-add" class="layui-btn layui-btn-sm">新增权限</button>
    <table class="layui-table">
        <colgroup>
            <col width="20%">
            <col width="20%">
            <col width="20%">
            <col width="40%">
        </colgroup>
        <thead>
        <tr>
            <th>权限ID</th>
            <th>权限名称</th>
            <th>权限描述</th>
            <th>操作</th>
        </tr>

        </thead>
        <tbody id="tbody">
        </tbody>
    </table>
</div>

</body>
</html>
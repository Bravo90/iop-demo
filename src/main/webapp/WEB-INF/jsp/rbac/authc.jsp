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
<style>


</style>
<body>
<div class="rbac-container">
    <div class="rbac-title">权限管理</div>
</div>

</body>
</html>
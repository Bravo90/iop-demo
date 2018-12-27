<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="common/head.jsp"/>
    <link href="${ctx}/resources/css/error/error.css" rel="stylesheet">
    <title>错误页面</title>
</head>
<body class="bg-image">
<div class="error-container">
    <h1 class="name-color">系统异常</h1>
    <c:if test="${msg != null}">
        <div class="error-msg">${msg}</div>
    </c:if>
    <div class="error-admin">请联系管理员</div>
    <div class="error-404">404</div>
</div>
</body>

</html>

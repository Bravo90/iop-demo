<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../common/head.jsp"/>
    <link href="${ctx}/resources/css/login/login.css" rel="stylesheet">
    <script type="text/javascript" src="${ctx}/resources/js/login/login.js"></script>
    <title>登录界面</title>
</head>
<body class="login-bg">
<div class="login-div">
    <div class="login-div-title">登录界面</div>
    <form class="login-div-from" action="${ctx}/sublogin">
        <div>用户名</div>
        <input type="text" name="username">
        <div>密码</div>
        <input type="password" name="password">
        <div class="login-err-msg">${msg}</div>
        <input type="submit" value="立即登录">
    </form>
</div>
</body>
</html>

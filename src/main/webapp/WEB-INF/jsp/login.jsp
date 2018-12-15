<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="common/head.jsp"/>
    <link href="${ctx}/resources/css/model/hello/hello.css" rel="stylesheet">
    <title>登录界面</title>
</head>
<body class="page-bg">
登录界面
<form action="${ctx}/sublogin">
    用户名 <input type="text" name="username">
    密码 <input type="password" name="password">
    <input type="submit" value="登录">
    <span>${msg}</span>
</form>
</body>

</html>

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
    <div class="error-msg">
        <c:if test="${msg != null}">
            ${msg},
        </c:if>
        请联系管理员
    </div>
    <div class="error-404"><i class="layui-icon layui-icon-404" style="font-size:400px "></i></div>
</div>
</body>

</html>

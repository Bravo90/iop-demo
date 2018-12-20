<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="common/head.jsp"/>
    <link href="${ctx}/resources/css/index/index.css" rel="stylesheet">
    <script type="text/javascript" src="${ctx}/resources/js/index/index.js"></script>
</head>
<body class="bg-image">
<div class="index-container">
    <ul class="ul-content" style="width: 100%;height: 100%">
        <li class="ul-content" style="width: 50%;height: 50%">
            <div></div>
        </li>
        <li class="ul-content" style="width: 50%;height: 50%">
            <div></div>
        </li>
        <li class="ul-content" style="width: 50%;height: 50%">
            <div></div>
        </li>
        <li class="ul-content" style="width: 50%;height: 50%">
            <div></div>
        </li>
    </ul>
</div>
</body>
</html>

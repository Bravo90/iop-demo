<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../common/head.jsp"/>
    <link href="${ctx}/resources/css/model/hello/hello.css" rel="stylesheet">
    <script type="text/javascript" src="${ctx}/resources/js/model/hello/hello.js"></script>
</head>
<body class="page-bg">
<h2 class="name-color">${hw.name}</h2>
<div class="panel">
    <h3>项目介绍</h3>
    <div>${hw.description}</div>
</div>
<div class="panel">
    <button id="team-btn">开发团队</button>
    <c:forEach var="it" items="${hw.developers}">
        <span class="team-span">${it}</span>
    </c:forEach>
</div>
<div class="bottom-panel">
    <span class="cmp-span">公司：${hw.company}</span>
    <span class="cmp-span">联系电话：${hw.tel}</span>
    <span class="cmp-span">电子邮箱：${hw.eMail}</span>
</div>
</body>
</html>

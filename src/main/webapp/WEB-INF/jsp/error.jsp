<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <link href="${pageContext.request.contextPath}/resources/css/model/hello/hello.css" rel="stylesheet">
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/js/lib/jquery/jquery-1.11.3.min.js"></script>
    <title>错误页面</title>
</head>
<body class="page-bg">
<h2 class="name-color">错误页面</h2>
<div>错误信息:${msg}</div>
</body>

</html>

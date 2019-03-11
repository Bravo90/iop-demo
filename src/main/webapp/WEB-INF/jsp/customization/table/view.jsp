<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../../common/head.jsp"/>
    <link href="${ctx}/resources/css/customization/table/customization.table.css" rel="stylesheet">
    <link href="${ctx}/resources/css/customization/table/table.css" rel="stylesheet">
    <script type="text/javascript" src="${ctx}/resources/lib/table2excel/jquery.table2excel.min.js"></script>
    <script type="text/javascript" src="${ctx}/resources/lib/My97DatePicker/WdatePicker.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/customization/table/customization.table.js"></script>
    <script type="text/javascript" src="${ctx}/resources/js/customization/table/table.js"></script>
    <title>查询界面</title>
</head>
<body>
<input hidden view-id="${viewId}" id="viewId">
<div class="table-container">
    <div id="t-table"></div>
</div>
</body>
</html>

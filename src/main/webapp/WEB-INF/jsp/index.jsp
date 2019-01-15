<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="common/head.jsp"/>
    <link href="${ctx}/resources/css/index/index.css" rel="stylesheet">
    <link href="${ctx}/resources/css/customization/table.css" rel="stylesheet">
    <script type="text/javascript" src="${ctx}/resources/js/index/index.js"></script>
</head>
<body class="bg-image">
<div class="index-container">

    <ul class="ul-content" style="width: 100%;height: 100%">
        <li class="ul-content" style="width: 80%;height: 8%">
            <div style="line-height: 40px;color: yellow;padding-left: 50%;border-top-left-radius: 30px">
                IOP-DEMO运营系统
            </div>
        </li>
        <li class="ul-content" style="width: 15%;height: 8%">
            <div style="text-align: center;line-height: 40px;color: yellow;">
                2018-12-21 13:55:21
            </div>
        </li>
        <li class="ul-content" style="width: 5%;height: 8%">
            <div style="text-align:center;line-height: 40px;color: yellow;">
                <a class="site-fork">退出</a>
            </div>
        </li>

        <li class="ul-content" style="width: 20%;height: 12%">
            <div></div>
        </li>
        <li class="ul-content" style="width: 20%;height: 12%">
            <div></div>
        </li>
        <li class="ul-content" style="width: 20%;height: 12%">
            <div></div>
        </li>
        <li class="ul-content" style="width: 20%;height: 12%">
            <div></div>
        </li>
        <li class="ul-content" style="width: 20%;height: 12%">
            <div></div>
        </li>
        <li class="ul-content" style="width: 50%;height: 40%">
            <div class="layui-carousel" id="test1">
                <div carousel-item>
                    <div style="background-color: transparent;text-align: center">
                        <img src="${ctx}/resources/images/login.jpg"
                             style="width: 100%;height: 100%;">
                    </div>
                    <div style="background-color: transparent">
                        <img src="${ctx}/resources/images/timg.jpg"
                             style="width: 100%;height: 100%;">
                    </div>
                    <div style="background-color: transparent" id="index-msg">
                        <table class="table-css">
                            <thead>
                            <tr>
                                <th>测试Id</th>
                                <th>测试名称</th>
                                <th>测试编码</th>
                                <th>测试时间</th>
                                <th>测试排序</th>
                                <th>测试联动</th>
                            </tr>
                            </thead>
                            <tbody id="tbody">

                            </tbody>
                        </table>
                    </div>
                    <div style="background-color: transparent"></div>
                    <div style="background-color: transparent"></div>
                </div>
            </div>
        </li>
        <li class="ul-content" style="width: 50%;height: 40%">
            <div>

            </div>
        </li>
        <li class="ul-content" style="width: 50%;height: 40%">
            <div></div>
        </li>
        <li class="ul-content" style="width: 50%;height: 40%">
            <div></div>
        </li>
    </ul>
</div>
</body>
</html>

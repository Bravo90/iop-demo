<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>
    <jsp:include page="../common/head.jsp"/>
    <link href="${ctx}/resources/css/datainput/datainput.css" rel="stylesheet">
    <title>数据导入</title>
</head>
<body>
<div class="default-container">
    <%-- <div class="layui-upload layui-form">
         <input type="radio" name="file-type" value="文本" title="文本">
         <input type="radio" name="file-type" value="Excel" title="Excel" checked>
         <button type="button" class="layui-btn layui-btn-normal" id="test8">选择文件</button>
         <button type="button" class="layui-btn" id="test9">开始上传</button>
     </div>--%>
    <div class="default-search-container">
        <span>表名</span><input>
        <span>表描述</span><input>
        <button class="layui-btn layui-btn-sm">查询</button>
    </div>
    <div class="default-table-tools">
        <i title="新增" class="layui-icon layui-icon-add-circle-fine"></i>
        <i title="删除" class="layui-icon layui-icon-delete"></i>
        <i title="更新" class="layui-icon layui-icon-edit"></i>
    </div>
    <table class="layui-table">
        <colgroup>
            <col width="35px">
            <col width="15%">
            <col width="15%">
            <col width="70%">
            <col width="35px">
        </colgroup>
        <thead>
        <tr>
            <th><input type="checkbox"></th>
            <th>表名</th>
            <th>导入描述</th>
            <th>导入字段</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody id="tbody">
        <tr>
            <td><input type="checkbox"></td>
            <td>iop_sys_user</td>
            <td>系统用户表</td>
            <td>user_id,username,nickname,password</td>
            <td><i title="数据导入" class="layui-icon layui-icon-upload-drag" input-id="234"></i>
            </td>
        </tr>
        <tr>
            <td><input type="checkbox"></td>
            <td>iop_sys_user</td>
            <td>系统用户表</td>
            <td>user_id,username,nickname,password</td>
            <td><i title="数据导入" class="layui-icon layui-icon-upload-drag" input-id="567"></i>
            </td>
        </tr>
        </tbody>
    </table>
</div>

</body>
<script>
    $(function () {
        DataInput.init();

    });
    var DataInput = {
        init: function () {
            DataInput.method.datainput();
        },
        method: {
            datainput: function () {
                $(document).on('click', '.layui-icon-upload-drag', function () {
                    var inputId = $(this).attr('input-id');
                    layer.open({
                        type: 1,
                        title: '文件上传',
                        anim: 1,
                        skin: 'layui-layer-molv',
                        area: ['300px', '220px'], //宽高
                        content: '<div class="upload-dialog-container">' +
                        '<div class="layui-form btn-container">' +
                        '<div class="btn-container-div">文件类型</div>' +
                        '<input type="radio" name="file-type" value="1" title="文本"checked>' +
                        '<input type="radio" name="file-type" value="2" title="Excel">' +
                        '</div><div class="btn-container">' +
                        '<button type="button" class="layui-btn" id="file-add">选择文件</button>' +
                        '<button type="button" class="layui-btn" id="data-input">开始导入</button>' +
                        '</div></div>'
                    });
                    var loading;
                    layui.form.render();
                    layui.upload.render({
                        elem: '#file-add',
                        url: Globals.contextPath() + '/input/upload',
                        auto: false,
                        accept: 'file',
                        bindAction: '#data-input',
                        data: {
                            inputId: inputId,
                            fileType: function () {
                                return $('input[name="file-type"]:checked').val();
                            }
                        },
                        before: function (res) {
                            console.log(res);
                            loading = layer.load(2, {
                                shade: [0.1, '#fff'] //0.1透明度的白色背景
                            })
                        },
                        done: function (res) {
                            layer.close(loading);
                            layer.msg('数据导入成功', {icon: 1});
                        }
                    });
                });
            }
        }
    };
</script>
</html>

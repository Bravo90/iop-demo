$(function () {
    DataInput.init();
});
var DataInput = {
    URL: {
        tables: function () {
            return Globals.contextPath() + '/input/table'
        }
    },
    init: function () {
        DataInput.method.renderTable(10, 1);
        DataInput.method.dataInput();
        DataInput.method.events();
    },
    method: {
        events: function () {
            //查询
            $('#search-btn').on('click', function () {
                DataInput.method.renderTable(10, 1);
            });
            //重置
            $('#rest-btn').on('click', function () {
                $('#table-name').val('');
                $('#table-desc').val('')
                DataInput.method.renderTable(10, 1);
            });
            //新增
            $('#table-add').on('click', DataInput.method.tableAdd);
            //删除
            $('#table-del').on('click', DataInput.method.tableDel);
            //修改
            $('#table-update').on('click', DataInput.method.tableUpdate);
        },
        renderTable: function (pageSize, pageNum) {
            var tableName = $('#table-name').val();
            var tableDesc = $('#table-desc').val();
            $.get(DataInput.URL.tables(), {
                'tableName': tableName,
                'tableDesc': tableDesc,
                'pageSize': pageSize,
                'pageNum': pageNum
            }, function (result) {
                var list = result.data.list;
                DataInput.method.buildBody(list);
                layui.laypage.render({
                    elem: 'page-id',
                    count: result.data.total,
                    limits: [5, 10, 50],
                    limit: result.data.pageSize,
                    curr: result.data.pageNum,
                    layout: ['count', 'prev', 'page', 'next', 'limit', 'skip'],
                    jump: function (obj) {
                        if (obj.curr != result.data.pageNum ||
                            obj.limit !== result.data.pageSize) {
                            DataInput.method.renderTable(obj.limit, obj.curr);
                        }
                    }
                });
            });
        },
        buildBody: function (list) {
            $('#tbody').empty();
            $(list).each(function () {
                $('#tbody').append('<tr>' +
                    '<td><input type="checkbox" id="' + this.inputTableId + '"></td>' +
                    '<td>' + this.tableName + '</td>' +
                    '<td>' + this.tableDesc + '</td>' +
                    '<td>' + this.tableFields + '</td>' +
                    '<td>' +
                    '<i class="layui-icon layui-icon-upload-drag" input-id="' + this.inputTableId + '"></i>' +
                    '</td>' +
                    '</tr>');
            });
        },
        tableAdd: function () {
            layer.open({
                type: 1,
                title: '新增上传表',
                anim: 1,
                skin: 'layui-layer-molv',
                area: ['265px', '270px'], //宽高
                content: '<div class="dialog-container"> ' +
                '<div><span>表名称</span><input></div>' +
                '<div><span>表描述</span><input></div>' +
                '<div><span>数据库类型</span><select>' +
                '<option value="1">mysql</option>' +
                '<option value="2">oracle</option>' +
                '<option value="3">内存库</option>' +
                '<option value="4">其他</option>' +
                '</select></div>' +
                '<div><span>字段选择</span><input></div>' +
                '<div><button class="layui-btn layui-btn-sm">确定</button></div>' +
                '</div>'
            });
        },
        tableDel: function () {

        },
        tableUpdate: function () {
            layer.open({
                type: 1,
                title: '新增上传表',
                anim: 1,
                skin: 'layui-layer-molv',
                area: ['265px', '270px'], //宽高
                content: '<div class="dialog-container"> ' +
                '<div><span>表名称</span><input></div>' +
                '<div><span>表描述</span><input></div>' +
                '<div><span>数据库类型</span><select>' +
                '<option value="1">mysql</option>' +
                '<option value="2">oracle</option>' +
                '<option value="3">内存库</option>' +
                '<option value="4">其他</option>' +
                '</select></div>' +
                '<div><span>字段选择</span><input></div>' +
                '<div><button class="layui-btn layui-btn-sm">确定</button></div>' +
                '</div>'
            });
        },
        dataInput: function () {
            $(document).on('click', '.layui-icon-upload-drag', function () {
                var inputId = $(this).attr('input-id');
                layer.open({
                    type: 1,
                    title: '文件上传',
                    anim: 1,
                    skin: 'layui-layer-molv',
                    area: ['300px', '250px'], //宽高
                    content: '<div class="dialog-container">' +
                    '<div class="layui-form btn-container">' +
                    '<div class="btn-container-div">文件类型</div>' +
                    '<input type="radio" name="file-type" value="1" title="文本"checked>' +
                    '<input type="radio" name="file-type" value="2" title="Excel">' +
                    '</div><div class="btn-container">' +
                    '<button type="button" class="layui-btn" id="file-add">选择文件</button><br>' +
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
                        loading = layer.load(2, {
                            shade: [0.1, '#fff'] //0.1透明度的白色背景
                        });
                    },
                    done: function (result) {
                        layer.close(loading);
                        var success = result['success'];
                        var msg = result['message'];
                        if (success == 1) {
                            layer.msg(msg, {icon: 1});
                            Role.methods.renderTable();
                        } else {
                            layer.alert(msg, {icon: 2});
                        }
                    }
                });
            });
        }
    }
};
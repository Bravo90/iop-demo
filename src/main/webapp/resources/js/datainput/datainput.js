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
            //全选
            $('.check-all').on('change', function () {
                var checked = $(this).is(':checked');
                $('.checkItem').each(function () {
                    $(this).prop('checked', checked);
                });
            });
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
                    '<td><input class="checkItem" type="checkbox" table-id="' + this.inputTableId + '"></td>' +
                    '<td>' + this.tableName + '</td>' +
                    '<td>' + this.tableDesc + '</td>' +
                    '<td>' + this.tableFields + '</td>' +
                    '<td>' + this.splitRegex + '</td>' +
                    '<td>' +
                    '<i class="layui-icon layui-icon-upload-drag" input-id="' + this.inputTableId + '"></i>' +
                    '</td>' +
                    '</tr>');
            });
        },
        tableAdd: function () {
            var addLayer = layer.open({
                type: 1,
                title: '新增上传表',
                anim: 1,
                skin: 'layui-layer-molv',
                area: ['265px', '320px'], //宽高
                content: '<div class="dialog-container"> ' +
                '<div><span class="span">表名称</span><input id="tableName"></div>' +
                '<div><span class="span">表描述</span><input id="tableDesc"></div>' +
                '<div><span class="span">数据库类型</span><select id="dbType">' +
                '<option value="1">mysql</option>' +
                '<option value="2">oracle</option>' +
                '<option value="3">内存库</option>' +
                '<option value="4">其他</option>' +
                '</select></div>' +
                '<div><span class="span">字段选择</span><input id="tableFields"></div>' +
                '<div><span class="span">分隔符</span><input id="splitRegex"></div>' +
                '<div><button class="layui-btn layui-btn-sm btn-margin" id="add-confirm">确定</button></div>' +
                '</div>'
            });
            //确定
            $('#add-confirm').on('click', function () {
                var tableName = $('#tableName').val();
                var tableDesc = $('#tableDesc').val();
                var dbType = $('#dbType').find('option:selected').val();
                var tableFields = $('#tableFields').val();
                var splitRegex = $('#splitRegex').val();
                //空检验
                if (!Globals.validate.validateNull(tableName, tableFields)) {
                    layer.msg('请将界面信息填写完整', {icon: 2});
                    return false;
                }
                var inputTable = {
                    tableName: tableName,
                    tableDesc: tableDesc,
                    tableFields: tableFields,
                    dbType: dbType,
                    splitRegex: splitRegex
                };
                $.ajax({
                    type: 'POST',
                    url: DataInput.URL.tables(),
                    contentType: 'application/json;charset=utf-8',
                    data: JSON.stringify(inputTable),
                    dateType: 'json',
                    success: function (result) {
                        var success = result['success'];
                        var msg = result['message'];
                        if (success == 1) {
                            layer.close(addLayer);
                            layer.msg(msg, {icon: 1});
                            DataInput.method.renderTable(10, 1);
                        } else {
                            layer.msg(msg, {icon: 2});
                        }
                    }
                });
            });
        },
        tableDel: function () {
            layer.confirm('确定删除？', {
                skin: 'layui-layer-molv',
                btn: ['确定', '取消'] //按钮
            }, function () {
                var checkeds = $('.checkItem:checked');
                if (checkeds.length > 0) {
                    var arr = new Array();
                    $(checkeds).each(function () {
                        var tableId = $(this).attr('table-id');
                        arr.push(tableId);
                    });
                    $.ajax({
                        type: 'DELETE',
                        url: DataInput.URL.tables(),
                        contentType: 'application/json;charset=utf-8',
                        data: JSON.stringify(arr),
                        dateType: 'json',
                        success: function (result) {
                            var success = result['success'];
                            var msg = result['message'];
                            if (success == 1) {
                                layer.msg(msg, {icon: 1});
                                DataInput.method.renderTable(10, 1);
                            } else {
                                layer.msg(msg, {icon: 2});
                            }
                        }
                    });
                } else {
                    layer.msg('请至少选择一项进行删除', {icon: 2});
                }
            });
        },
        tableUpdate: function () {
            //判断选择项
            var checked = $('.checkItem:checked');
            if (checked.length != 1) {
                layer.msg('请选择一项进行编辑', {icon: 2});
            } else {
                var tableId = $('.checkItem:checked').attr('table-id');
                var tableName = '';
                var tableDesc = '';
                var dbType = 0;
                var tableFields = '';
                var splitRegex = '';
                var userId = 0;
                $.get(DataInput.URL.tables() + '/' + tableId, {},
                    function (result) {
                        if (result['success'] == 1) {
                            tableName = result.data.tableName;
                            tableDesc = result.data.tableDesc;
                            dbType = result.data.dbType;
                            tableFields = result.data.tableFields;
                            splitRegex = result.data.splitRegex;
                            userId = result.data.userId;
                            layer.open({
                                type: 1,
                                title: '修改上传表',
                                anim: 1,
                                skin: 'layui-layer-molv',
                                area: ['265px', '320px'], //宽高
                                content: '<div class="dialog-container"> ' +
                                '<div><span class="span">表名称</span><input id="update-table-name"></div>' +
                                '<div><span class="span">表描述</span><input id="update-table-desc"></div>' +
                                '<div><span class="span">数据库类型</span><select id="update-db-type">' +
                                '<option value="1">mysql</option>' +
                                '<option value="2">oracle</option>' +
                                '<option value="3">内存库</option>' +
                                '<option value="4">其他</option>' +
                                '</select></div>' +
                                '<div><span class="span">字段选择</span><input id="update-table-fields"></div>' +
                                '<div><span class="span">分隔符</span><input id="update-split-regex"></div>' +
                                '<div><button class="layui-btn layui-btn-sm btn-margin" id="update-confirm">确定</button></div>' +
                                '</div>'
                            });
                            $('#update-table-name').val(tableName);
                            $('#update-table-desc').val(tableDesc);
                            $('#update-table-fields').val(tableFields);
                            $('#update-split-regex').val(splitRegex);
                        } else {
                            layer.msg(result['message'], {icon: 2});
                        }
                    });

                $(document).unbind("click").on('click', '#update-confirm', function () {
                    var tableName = $('#update-table-name').val();
                    var tableDesc = $('#update-table-desc').val();
                    var dbType = $('#update-db-type').find('option:selected').val();
                    var tableFields = $('#update-table-fields').val();
                    var splitRegex = $('#update-split-regex').val();
                    //空检验
                    if (!Globals.validate.validateNull(tableName, tableFields)) {
                        layer.msg('请将界面信息填写完整', {icon: 2});
                        return false;
                    }
                    var inputTable = {
                        tableName: tableName,
                        tableDesc: tableDesc,
                        tableFields: tableFields,
                        dbType: dbType,
                        inputTableId: tableId,
                        userId: userId,
                        splitRegex: splitRegex
                    };
                    $.ajax({
                        type: 'PUT',
                        url: DataInput.URL.tables(),
                        contentType: 'application/json;charset=utf-8',
                        data: JSON.stringify(inputTable),
                        dateType: 'json',
                        success: function (result) {
                            var success = result['success'];
                            var msg = result['message'];
                            if (success == 1) {
                                layer.msg(msg, {icon: 1});
                                DataInput.method.renderTable(10, 1);
                            } else {
                                layer.msg(msg, {icon: 2});
                            }
                        }
                    });
                });
            }
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
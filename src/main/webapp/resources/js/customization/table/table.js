$(function () {


    Table.init();
    $('#t-table').renderTable({tableId: 2});
});
var Table = {

    URL: {
        config: function (viewId) {
            return Globals.contextPath() + '/config/' + viewId;
        }
    },

    init: function () {
        var viewId = $('input[view-id]').attr('view-id');
        Table.methods.queryConfiguration(viewId);
        Table.methods.queryTable();
        Table.methods.addRecord();
        Table.methods.editRecord();
        Table.methods.delRecords();
    },
    methods: {
        queryConfiguration: function (viewId) {
            $.get(Table.URL.config(viewId), {}, function (result) {
                console.log(result);
            });
        },
        queryTable: function () {
            $.get(Globals.contextPath() + "/table/query", {
                'param': '{"tableId":"2",' +
                //'"fields":[{"name":"test_id","value":["1"]}],' +
                //'"fields":[{"name":"test_name","value":["2d"]}],' +
                //'"fields":[{"name":"test_order","value":["250"]}],' +
                //'"fields":[{"name":"test_code","value":["0531"]}],' +
                '"fields":[{"name":"test_date","value":["2019-01-01 11:29:12","2019-03-01 11:29:12"]}],' +
                '"order":[{"field":"test_date","type":"asc"}],' +
                '"page":{"pageSize":5,"pageNum":1}}'
            }, function (result) {
                var list = result.data.list;
                layer.msg("查询数据总量:" + result.data.total +
                    ";总页数:" + result.data.pages +
                    ";当前页码:" + result.data.pageNum);
                $('#tbody').empty();
                $(list).each(function () {
                    $('#tbody').append('<tr>' +
                        '<td><input type="checkbox"></td>' +
                        '<td>' + this['test_id'] + '</td>' +
                        '<td>' + this['test_name'] + '</td>' +
                        '<td>' + this['test_code'] + '</td>' +
                        '<td>' + this['test_date'] + '</td>' +
                        '<td>' + this['test_order'] + '</td>' +
                        '<td>' + this['test_linkage'] + '</td>' +
                        '</tr>');
                });
            });
        },
        addRecord: function () {
            $(document).on('click', '.layui-icon-add-circle-fine', function () {
                layer.msg('增加成功');
            });
        },
        editRecord: function () {
            $(document).on('click', '.layui-icon-edit', function () {
                var nums = $('tr input:checkbox:checked').length;
                if (nums != 1) {
                    layer.msg('请选择一条数据进行编辑');
                } else {
                    layer.msg('编辑成功！');
                }
            });
        },
        delRecords: function () {
            $(document).on('click', '.layui-icon-delete', function () {
                var nums = $('tr input:checkbox:checked').length;
                if (nums < 1) {
                    layer.msg('请至少选择一条数据进行删除');
                } else {
                    layer.msg('删除成功，共' + nums + '条');
                }
            });
        }
    }
};
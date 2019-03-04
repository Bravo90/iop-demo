$(function () {
    Table.init();
});
var Table = {
    URL: {
        config: function (viewId) {
            return Globals.contextPath() + '/config/' + viewId;
        }
    },
    init: function () {
        var viewId = $('input[view-id]').attr('view-id');
        Table.methods.initTable(viewId);
    },
    methods: {
        initTable: function (viewId) {
            $.get(Table.URL.config(viewId), {}, function (result) {
                console.log(result)
                var success = result['success'];
                var msg = result['message'];
                if (success == 1) {
                    $('#t-table').renderTable({
                        tableId: viewId,
                        viewName: result.data.viewName,
                        editable: result.data.editable,
                        pageable: result.data.pageable,
                        pageSize: result.data.pageSize,
                        col: result.data.cols,
                        searchCols: result.data.searchCols,
                        fieldMap: result.data.fieldMap,
                        btns: result.data.btns,
                        url: {
                            query: Globals.contextPath() + '/table/query',
                            update: Globals.contextPath() + '/table/update',
                            del: Globals.contextPath() + '/table/delete'
                        }
                    });
                } else {
                    layer.msg(msg, {icon: 2});
                }
            });
        }
    }
};
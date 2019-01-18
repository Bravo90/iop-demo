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
                console.log(result.data);
                $('#t-table').renderTable({
                    tableId: viewId,
                    viewName: result.data.viewName,
                    editable: result.data.editable,
                    pageable: result.data.pageable,
                    pageSize: result.data.pageSize,
                    col: result.data.cols,
                    searchCols: result.data.searchCols,
                });
            });
        }
    }
};
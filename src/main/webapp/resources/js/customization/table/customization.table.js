//定制化table
;(function ($, window, document, undefined) {
    var Table = function (ele, option) {
        this.ele = ele;
        this.default = {
            tableId: 0,
            tableStyle: 'table-css',
            url: {
                config: '/config/',
                query: '',
                add: '',
                del: '',
                update: ''
            },
            col: []
        };
        this.option = $.extend({}, this.default, option);
        this.init();
        this.layui = window.layui;
        this.layer = window.layui.layer;
    };

    Table.prototype = {
        init: function () {
            var _this = this;
            var configUrl = Globals.contextPath() + this.option.url.config + this.option.tableId;
            $.get(configUrl, {}, function (result) {
                var tr = $('<tr></tr>');
                var searchArea = $('<div></div>').addClass('table-search-container');
                $(result.data).each(function () {
                    //1.处理表头
                    var th = _this.renderHeadTh(this);
                    tr.append(th);
                    //2.处理搜索字段
                    if (this['searchable']) {
                        searchArea.append(_this.renderSearchField(this));
                    }
                });
                searchArea.append('<div class="btn">' +
                    '<button class="layui-btn layui-btn-sm">查询</button>' +
                    '<button class="layui-btn layui-btn-sm">重置</button>' +
                    '</div>');
                _this.ele.append(searchArea);
                _this.ele.append(_this.createTable(_this.renderHead(tr)));

            });
        },
        createTable: function (head) {
            var table = $('<table></table>')
                .addClass(this.option.tableStyle)
                .append(head);
            return table;
        },
        renderHead: function (tr) {
            var head = $('<thead></thead>')
                .append(tr);
            return head;
        },
        renderHeadTh: function (field) {
            var th = $('<th></th>')
                .html(field['fieldDesc'])
                .attr('field-name', field['fieldName']);
            if (field['keyFiled']) {
                th.attr('field', 'key')
            }
            if (field['sortable']) {
                var span = $('<span></span>');
                span.addClass('table-sort inline');
                var iasc = $('<i></i>');
                iasc.addClass('edge table-sort-asc');
                var idesc = $('<i></i>');
                idesc.addClass('edge table-sort-desc');
                span.append(iasc).append(idesc);
                th.append(span);
            }
            return th;
        },
        renderBody: function () {

        },
        renderSearchField: function (field) {
            var span = $('<span></span>').html(field['fieldDesc']);
            if (field['required']) {
                span.append('<span class="required">*</span>');
            }
            console.log('fieldType = ' + field['fieldType'],field['fieldMapping']);
            var input = $("<input>");
            var div = $('<div></div>').addClass('field')
                .append(span).append(input);
            return div;
        },
        renderSearchArea: function () {

        },
        query: function () {
            if (this.option.url.query != '') {

            } else {

            }
        },
        remove: function () {

        },
        update: function () {

        },
        insert: function () {

        }
    };

    $.fn.renderTable = function (option) {
        return new Table($(this), option);
    }
})(jQuery, window, document);
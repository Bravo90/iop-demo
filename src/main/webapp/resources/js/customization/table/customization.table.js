//定制化table
;(function ($, window, document, undefined) {
    var Table = function (ele, option) {
        this.ele = ele;
        this.default = {
            tableStyle: 'table-css',
            url: {
                config: '',
                query: '',
                add: '',
                del: '',
                update: ''
            },
            col: []
        };
        this.option = $.extend({}, this.default, option);
        this.init();
    };

    Table.prototype = {
        init: function () {
            $.get(Globals.contextPath() + "/config/1", {}, function (result) {
                console.log(result)
            });
            this.query();
        },
        renderHead: function () {

        },
        renderBody:function () {
            
        },
        query: function () {
            if (this.option.url.query != '') {

            } else {
                alert('尚未提供数据服务接口');
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
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
            }
        };
        this.option = $.extend({}, this.default, option);
        this.init();
    };

    Table.prototype = {
        init: function () {
            console.log(this);
            this.query();
        },
        query: function () {
            console.log(this.option.url.query)
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
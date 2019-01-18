//定制化table
;(function ($, window, document, undefined) {
    var Table = function (ele, option) {
        this.ele = ele;
        this.default = {
            tableId: 0,
            tableStyle: 'table-css',
            url: {
                query: '',
                add: '',
                del: '',
                update: ''
            },
            editable: false,
            pageable: true,
            pageSize: 10,
            col: [],
            searchCols: []
        };
        this.option = $.extend({}, this.default, option);
        this.init();
        this.layui = window.layui;
        this.layer = window.layui.layer;
    };

    Table.prototype = {
        init: function () {
            this.renderView();
            this.events();
        },
        renderView: function () {
            this.renderSearchArea();
            this.renderTable();
        },
        renderPage: function () {

        }
        ,
        renderTable: function () {
            var head = this.renderHead();
            var table = $('<table></table>')
                .addClass(this.option.tableStyle)
                .append(head);
            this.ele.append(table);
        },
        renderHead: function () {
            var _this = this;
            var tr = $('<tr></tr>');
            $(_this.option.col).each(function () {
                var th = _this.renderHeadTh(this);
                tr.append(th);
            });
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
        renderSearchArea: function () {
            var _this = this;
            var searchArea = $('<div></div>').addClass('table-search-container');
            $(_this.option.searchCols).each(function () {
                if (this['searchable']) {
                    searchArea.append(_this.renderSearchField(this));
                }
            });
            searchArea.append('<div class="btn">' +
                '<button class="layui-btn layui-btn-sm query-btn">查询</button>' +
                '<button class="layui-btn layui-btn-sm clear-btn">重置</button>' +
                '</div>');
            _this.ele.append(searchArea);
        },
        renderSearchField: function (field) {
            var span = $('<span></span>').html(field['fieldDesc']);
            var div = $('<div></div>').addClass('field').attr('field', field['fieldName']);
            if (field['required']) {
                span.append('<span class="required">*</span>');
                div.attr('required', true);
            }
            div.append(span);
            //先判断是否是映射值

            //根据搜索类型和字段类型生成查询条件输入框
            var search = field['searchType'];
            var fieldType = field['fieldType'];
            switch (search) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 6:
                case 7:
                case 8:
                case 9:
                case 11:
                default: {
                    if (fieldType == 3) {
                        div.append('<input placeholder="开始" class="Wdate select-control" onClick="WdatePicker({el:this,dateFmt:\'yyyy-MM-dd HH:mm:ss\'})">');
                        div.append(' —');
                        div.append('<input placeholder="结束" class="Wdate select-control" onClick="WdatePicker({el:this,dateFmt:\'yyyy-MM-dd HH:mm:ss\'})">');
                    } else {
                        var input = $('<input>');
                        div.append(input);
                    }
                    break;
                }
                case 10: {
                    if (fieldType == 3) {
                        div.append('<input placeholder="开始" class="Wdate select-control" onClick="WdatePicker({el:this,dateFmt:\'yyyy-MM-dd HH:mm:ss\'})">');
                        div.append(' —');
                        div.append('<input placeholder="结束" class="Wdate select-control" onClick="WdatePicker({el:this,dateFmt:\'yyyy-MM-dd HH:mm:ss\'})">');
                    } else {
                        var input = $('<input placeholder="开始"><input placeholder="结束">');
                        div.append(input);
                    }
                    break;
                }
            }
            return div;
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

        },
        events: function () {
            var _this = this;
            _this.ele.on('click', function (e) {
                var e = e || window.event;
                var source = e.target;
                var className = source.className;
                if (className === 'layui-btn layui-btn-sm query-btn') {
                    var divs = $('.table-search-container').find('div');
                    var params = new Array();
                    $(divs).each(function () {
                        var required = $(this).attr('required');
                        var fieldName = $(this).attr('field');
                        var inputs = $(this).find("input");
                        var valueArr = new Array();
                        if (inputs.length > 0) {
                            for (var i = 0; i < inputs.length; i++) {
                                if ($(inputs[i]).val() != '') {
                                    valueArr.push($(inputs[i]).val());
                                    $(inputs[i]).removeClass('empty-alert')
                                }else{
                                    if(required != undefined){
                                        $(inputs[i]).addClass('empty-alert')
                                        layer.msg('必选项不可为空', {icon: 2});
                                        return;
                                    }
                                }
                            }
                        }
                        if (valueArr.length > 0) {
                            params.push({
                                'name': fieldName,
                                'value': valueArr
                            });
                        }
                    });
                    console.log(JSON.stringify(params));
                } else if (className === 'layui-btn layui-btn-sm clear-btn') {
                    layer.alert('已重置');
                } else if (
                    className == 'edge table-sort-asc') {
                    $('.table-sort-asc').removeClass('selected');
                    $('.table-sort-desc').removeClass('selected');
                    $(source).addClass('selected');
                } else if (className == 'edge table-sort-desc') {
                    $('.table-sort-asc').removeClass('selected');
                    $('.table-sort-desc').removeClass('selected');
                    $(source).addClass('selected');
                }
            });
        }
    };

    $.fn.renderTable = function (option) {
        return new Table($(this), option);
    }
})(jQuery, window, document);
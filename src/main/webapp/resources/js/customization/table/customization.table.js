//定制化table
;(function ($, window, document, undefined) {
    var Table = function (ele, option) {
        this.ele = ele;
        this.layui = window.layui;
        this.layer = window.layui.layer;
        this.laypage = window.layui.laypage;
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
            searchCols: [],
            fieldMap: [],
            btns: []
        };
        this.option = $.extend({}, this.default, option);
        this.requestParam = {
            'tableId': option.tableId,
            'fields': [],
            'order': [],
            'page': {'pageSize': 10, 'pageNum': 1}
        }
        this.init();
    };

    Table.prototype = {
        init: function () {
            this.renderView();
            this.renderPage();
            this.query();
            this.events();
        },
        renderView: function () {
            this.renderSearchArea();
            this.renderTools();
            this.renderTable();
        },
        renderPage: function () {
            var _this = this;
            var div = $('<div></div>')
                .attr('id', 'page-id')
                .addClass('table-page');
            _this.ele.append(div);
        },
        renderTable: function () {
            var head = this.renderHead();
            var body = this.renderBody();
            var table = $('<table></table>')
                .addClass(this.option.tableStyle)
                .append(head).append(body);
            this.ele.append(table);
        },
        renderHead: function () {
            var _this = this;
            var tr = $('<tr></tr>');
            if (_this.option.editable) {
                tr.append('<th class="check-all"><input class="check-all" type="checkbox"></th>');
            }
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
            var body = $('<tbody></tbody>').attr('id', 'tbody');
            return body;
        },
        buildBody: function (list) {
            var _this = this;
            var ths = $('th[field-name]');
            $('#tbody').empty();
            $(list).each(function () {
                var tr = $('<tr></tr>');
                if (_this.option.editable) {
                    tr.append('<td class="checkbox"><input class="checkItem" type="checkbox"></td>');
                }
                for (var i = 0; i < ths.length; i++) {
                    var td = $('<td></td>');
                    var fieldName = $(ths[i]).attr('field-name');
                    //码表转译
                    var vk = _this.option.fieldMap[fieldName];
                    if (vk != undefined) {
                        var value = _this.handleFieldMap(vk, this[fieldName]);
                        td.html(value);
                    } else {
                        td.html(this[fieldName]);
                    }
                    tr.append(td);
                }
                $('#tbody').append(tr);
            });
        },
        buildPage: function (data) {
            var _this = this;
            var limit = data.pageSize;
            var count = data.total;
            var pageNum = data.pageNum;
            _this.laypage.render({
                elem: 'page-id',
                count: count,
                limit: limit,
                curr: pageNum,
                layout: ['count', 'prev', 'page', 'next', 'limit', 'skip'],
                jump: function (obj) {
                    if (obj.curr != _this.requestParam.page.pageNum ||
                        obj.limit != _this.requestParam.page.pageSize) {
                        _this.requestParam = $.extend(_this.requestParam, {
                            'page': {
                                'pageSize': obj.limit,
                                'pageNum': obj.curr
                            }
                        });
                        _this.query();
                    }
                }
            });
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
            var _this = this;
            var span = $('<span></span>').html(field['fieldDesc']);
            var div = $('<div></div>').addClass('field').attr('field', field['fieldName']);
            if (field['required']) {
                span.append('<span class="required">*</span>');
                div.attr('required', true);
            }
            div.append(span);
            //根据搜索类型和字段类型生成查询条件输入框
            var search = field['searchType'];
            var fieldType = field['fieldType'];
            switch (search) {
                case 101: {
                    var fieldMap = _this.option.fieldMap[field['fieldName']];
                    console.log(fieldMap)
                    if (fieldMap != undefined) {
                        var select = _this.buildSeletMap(fieldMap);
                        div.append(select);
                    } else {
                        var input = $('<input>');
                        div.append(input);
                    }
                    break;
                }
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
                        div.append('<input placeholder="开始日期" class="Wdate select-control" onClick="WdatePicker({el:this,dateFmt:\'yyyy-MM-dd HH:mm:ss\'})">');
                        //div.append('');
                        div.append('<input placeholder="结束日期" class="Wdate select-control" onClick="WdatePicker({el:this,dateFmt:\'yyyy-MM-dd HH:mm:ss\'})">');
                    } else {
                        var input = $('<input>');
                        div.append(input);
                    }
                    break;
                }
                case 10: {
                    if (fieldType == 3) {
                        div.append('<input placeholder="开始日期" class="Wdate select-control" onClick="WdatePicker({el:this,dateFmt:\'yyyy-MM-dd HH:mm:ss\'})">');
                        //div.append('');
                        div.append('<input placeholder="结束日期" class="Wdate select-control" onClick="WdatePicker({el:this,dateFmt:\'yyyy-MM-dd HH:mm:ss\'})">');
                    } else {
                        var input = $('<input placeholder="开始"><input placeholder="结束">');
                        div.append(input);
                    }
                    break;
                }
            }
            return div;
        },
        renderTools: function () {
            var btns = this.option.btns;
            if (btns.length > 0) {
                var div = $('<div></div>').addClass('table-tools');
                var style1 = 'color:#009688;font-size: 30px;margin-right: 10px;cursor: pointer;';
                var style2 = 'color:#009688;font-size: 30px;float: right;margin-right: 10px;cursor: pointer';
                $(btns).each(function () {
                    var className = this['btnClass'];
                    var btnName = this['btnName'];
                    switch (className) {
                        case 'add':
                            var add = '<i title="' + btnName + '" class="layui-icon layui-icon-add-circle-fine" style="' + style1 + '"></i>';
                            div.append(add);
                            break;
                        case 'del':
                            var del = '<i title="' + btnName + '" class="layui-icon layui-icon-delete" style="' + style1 + '"></i>';
                            div.append(del);
                            break;
                        case 'update':
                            var update = ' <i title="' + btnName + '" class="layui-icon layui-icon-edit" style="' + style1 + '" ></i>';
                            div.append(update);
                            break;
                        case 'dataOutput':
                            var dataOutput = '<i title="' + btnName + '" class="layui-icon layui-icon-download-circle"  style="' + style2 + '" ></i>';
                            div.append(dataOutput);
                            break;
                        case 'dataInput':
                            var dataInput = '<i title="' + btnName + '" class="layui-icon layui-icon-upload-drag"  style="' + style2 + '" ></i>';
                            div.append(dataInput);
                            break;
                    }
                });
                this.ele.append(div);
            }
        },
        query: function () {
            var _this = this;
            if (_this.option.url.query != '') {
                $.get(_this.option.url.query,
                    {'param': JSON.stringify(this.requestParam)},
                    function (result) {
                        var list = result.data.list;
                        //tbody数据构建
                        _this.buildBody(list);
                        //分页处理
                        _this.buildPage(result.data);
                    });
            } else {
                layer.msg('服务接口尚未提供，请配置url.query路径')
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
                        var inputs = $(this).find('input');
                        var selects = $(this).find('select');
                        var valueArr = new Array();
                        if (inputs.length > 0) {
                            for (var i = 0; i < inputs.length; i++) {
                                if ($(inputs[i]).val() != '') {
                                    valueArr.push($(inputs[i]).val());
                                    $(inputs[i]).removeClass('empty-alert');
                                } else {
                                    if (required != undefined) {
                                        $(inputs[i]).addClass('empty-alert')
                                        layer.msg('必选项不可为空', {icon: 2});
                                        return false;
                                    }
                                }
                            }
                        } else if (selects.length > 0) {
                            for (var i = 0; i < selects.length; i++) {
                                var value = $(selects[i]).find('option:selected').val();
                                if (value != 'none') {
                                    valueArr.push(value);
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
                    _this.requestParam = $.extend(_this.requestParam, {
                        'fields': params
                    });
                    _this.query();
                } else if (className === 'layui-btn layui-btn-sm clear-btn') {
                    console.log(123)
                    //清空查询条件
                    $('.field').find('input').val("");
                    $('.field').find('select option:first').prop("selected", 'selected');

                    $('.table-sort-asc').removeClass('selected');
                    $('.table-sort-desc').removeClass('selected');
                    //重新查询数据
                    _this.requestParam = $.extend(_this.requestParam, {
                        'fields': [],
                        'order': [],
                        'page': {'pageSize': 10, 'pageNum': 1}
                    });
                    _this.query();
                } else if (className == 'edge table-sort-asc') {
                    $('.table-sort-asc').removeClass('selected');
                    $('.table-sort-desc').removeClass('selected');
                    $(source).addClass('selected');
                    var field = $(source).parent().parent().attr('field-name');
                    var orderArr = new Array();
                    orderArr.push({
                        "field": field,
                        "type": "asc"
                    });
                    _this.requestParam = $.extend(_this.requestParam, {
                        'order': orderArr
                    });
                    _this.query();

                } else if (className == 'edge table-sort-desc') {
                    $('.table-sort-asc').removeClass('selected');
                    $('.table-sort-desc').removeClass('selected');
                    $(source).addClass('selected');
                    var field = $(source).parent().parent().attr('field-name');
                    var orderArr = new Array();
                    orderArr.push({
                        "field": field,
                        "type": "desc"
                    });
                    _this.requestParam = $.extend(_this.requestParam, {
                        'order': orderArr
                    });
                    console.log(_this.requestParam)
                    _this.query();
                } else if (className == 'layui-icon layui-icon-add-circle-fine') {
                    var ths = $('.check-all').parent().siblings();
                    var content = _this.getUpdateContent(ths);
                    layer.open({
                        type: 1,
                        title: '新增数据',
                        anim: 1,
                        skin: 'layui-layer-molv',
                        area: ['290px', '400px'], //宽高
                        content: content
                    });
                } else if (className == 'layui-icon layui-icon-delete') {
                    var checked = $('.checkItem:checked');
                    if (checked.length <= 0) {
                        layer.msg('请至少选择一个删除项', {icon: 2})
                    } else {
                        layer.msg('删除记录成功', {icon: 1});
                    }
                } else if (className == 'layui-icon layui-icon-edit') {
                    var checked = $('.checkItem:checked');
                    if (checked.length != 1) {
                        layer.msg('请选择一项进行编辑', {icon: 2});
                    } else {
                        var tds = $('.checkItem:checked').parent().siblings();
                        var ths = $('.check-all').parent().siblings();
                        var content = _this.getUpdateContent(ths, tds);
                        layer.open({
                            type: 1,
                            title: '更新数据',
                            anim: 1,
                            skin: 'layui-layer-molv',
                            area: ['290px', '400px'], //宽高
                            content: content
                        });
                    }
                } else if (className == 'layui-icon layui-icon-upload-drag') {
                    layer.msg('导入记录', {icon: 2});
                } else if (className == 'layui-icon layui-icon-download-circle') {
                    layer.msg('导出记录', {icon: 2});
                }
            });
            $('input[type=checkbox]').on('change', function () {
                if ($(this).hasClass('check-all')) {
                    var checked = $(this).is(':checked');
                    $('.checkItem').each(function () {
                        $(this).prop('checked', checked);
                    });
                }
            });
            //更新确定
            $(document).on('click', '.update-confirm', function () {
                layer.msg('1');
            });
        },
        handleFieldMap: function (list, value) {
            for (var i = 0; i < list.length; i++) {
                if (list[i]['key'] == value) {
                    return list[i]['value'];
                }
            }
            return value;
        },
        getUpdateContent: function (ths, tds) {
            var content = '<div class="insert-update-container">';
            $(ths).each(function (index) {
                var value = "";
                if (tds != undefined) {
                    value = $(tds[index]).text();
                }

                //先判断是否为映射值

                content += ('<span >' +
                    $(this).text() + '</span><div><input field-name="' +
                    $(this).attr('field-name') + '" ' + 'value="' + value + '"></div>');
            });
            content += '<div></div>' +
                '<div><button class="layui-btn layui-btn-sm update-confirm">确定</button></div>';
            return content + '</div>';
        },
        buildSeletMap: function (fieldMap) {
            var select = $('<select></select>');
            select.append('<option value="none">--请选择--</option>');
            for (var i = 0; i < fieldMap.length; i++) {
                var key = fieldMap[i].key;
                var value = fieldMap[i].value;
                var option = $('<option value="' + key + '">' + value + '</option>');
                select.append(option);
            }
            return select;
        }
    };
    $.fn.renderTable = function (option) {
        return new Table($(this), option);
    }
})(jQuery, window, document);
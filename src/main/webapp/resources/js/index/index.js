$(function () {
    Index.init();
});

var Index = {
    URL: {},
    init: function () {
        Index.methods.renderCarousel();
        Index.methods.tableQuery();

        var table = $("#tbody").renderTable({
            url: {
                config: '/table/config',
                del: '/table/del',
                query: '/table/query'
            }
        });
    },
    methods: {
        renderCarousel: function () {
            var carousel = layui.carousel;
            carousel.render({
                elem: '#test1',
                width: '100%',
                height: '100%',
                arrow: 'hover',
                autoplay: false,
                indicator: 'none'
            });
        },
        tableQuery: function () {
            $.get(Globals.contextPath() + "/table/query", {
                'param': '{"tableId":"2",' +
                //'"fields":[{"name":"test_id","value":["1"]}],' +
                //'"fields":[{"name":"test_name","value":["2d"]}],' +
                //'"fields":[{"name":"test_order","value":["250"]}],' +
                //'"fields":[{"name":"test_code","value":["0531"]}],' +
                '"fields":[{"name":"test_date","value":["2019-01-01 11:29:12","2019-03-01 11:29:12"]}],' +
                '"order":[{"field":"test_date","type":"asc"}],' +
                '"page":{"pageSize":5,"pageNum":2}}'
            }, function (result) {
                var list = result.data.list;
                layer.msg("查询数据总量:" + result.data.total +
                    ";总页数:" + result.data.pages +
                    ";当前页码:" + result.data.pageNum);
                $('#tbody').empty();
                $(list).each(function () {
                    $('#tbody').append("<tr>" +
                        "<td>" + this['test_id'] + "</td>" +
                        "<td>" + this['test_name'] + "</td>" +
                        "<td>" + this['test_code'] + "</td>" +
                        "<td>" + this['test_date'] + "</td>" +
                        "<td>" + this['test_order'] + "</td>" +
                        "<td>" + this['test_linkage'] + "</td>" +
                        "</tr>");
                });
            });
        }
    }
};
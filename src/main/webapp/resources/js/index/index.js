$(function () {
    Index.init();
});

var Index = {
    URL: {},
    init: function () {
        Index.methods.renderCarousel();
        Index.methods.tableQuery();
    },
    methods: {
        renderCarousel: function () {
            var carousel = layui.carousel;
            carousel.render({
                elem: '#test1',
                width: '100%',
                height: '100%',
                arrow: 'always'
            });
        },
        tableQuery: function () {
            $.get(Globals.contextPath() + "/table/query", {
                'param': '{"tableId":"2",' +
                //'"fields":[{"name":"test_id","value":["1"]}],' +
                //'"fields":[{"name":"test_name","value":["2d"]}],' +
                // '"fields":[{"name":"test_order","value":["250"]}],' +
                '"fields":[{"name":"test_code","value":["0531"]}],' +
                '"order":[{"field":"test_id","type":"asc"}],' +
                '"page":{"pageSize":5,"pageNum":1}}'
            }, function (result) {
                var list = result.data.list;
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
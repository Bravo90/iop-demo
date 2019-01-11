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
                'param': '{"tableId":"1","fields":[{"name":"username","value":["c"]}],"order":[{"field":"user_id","type":"asc"}],"page":{"pageSize":10,"pageNum":1}}'
            }, function (result) {
                console.log(result);
            });
        }
    }
};
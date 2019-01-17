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
                arrow: 'hover',
                autoplay: false,
                indicator: 'none'
            });
        }
    }
};
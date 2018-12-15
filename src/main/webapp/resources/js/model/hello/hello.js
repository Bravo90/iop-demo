$(function () {
    Hello.init();
});

var Hello = {
    URL: {
        ajaxRequest: function () {
            return Globals.contextPath() + "/ajaxRequest";
        }
    },
    init: function () {
        $("#team-btn").on("click", function () {
            $.get(Hello.URL.ajaxRequest(), {}, function (result) {
                console.log(result);
                alert(result.message);
            })
        });
    },
    methods: {}
}



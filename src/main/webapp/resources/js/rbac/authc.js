$(function () {
    Authc.init();
});

var Authc = {
    URL: {
        parentAuthc: function () {
            return Globals.contextPath() + "/authc/plist";
        }
    },
    init: function () {
        Authc.methods.renderParentAuthcTable();
    },
    methods: {
        renderParentAuthcTable: function () {
            $.get(Authc.URL.parentAuthc(), {}, function (result) {
                console.log(result);
            });

        }
    }
};
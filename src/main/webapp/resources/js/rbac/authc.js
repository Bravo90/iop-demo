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
                $('tBody').empty();
                var list = result.data;
                $(list).each(function (index) {
                    $('#tbody').append('<tr>'+
                        '<th>' + this.authcId + '</th>' +
                        '<th>' + this.authcName + '</th>' +
                        '<th>' + this.authcDesc + '</th>' +
                        '<th></th>' +
                        '</tr>');
                });
            });
        }
    }
};
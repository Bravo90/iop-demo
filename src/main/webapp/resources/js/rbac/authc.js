$(function () {
    Authc.init();
});

var Authc = {
    URL: {
        parentAuthc: function () {
            return Globals.contextPath() + '/authc/plist';
        },
        checkAuthcNameExist :function (authcname) {
            return Globals.contextPath() + '/authc/authcname/' + authcname;
        }
    },
    init: function () {
        var layer = layui.layer;
        Authc.methods.renderParentAuthcTable();
        Authc.methods.addAuthc(layer);
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
        },
        addAuthc:function(){
            var addLayer;
            $('#authc-add').on('click',function () {
                addLayer = layer.open({
                    type: 1,
                    title: '新增权限',
                    anim: 1,
                    skin: 'layui-layer-molv',
                    area: ['290px', '190px'], //宽高
                    content: '<div class="rbac-user-update bg-image">' +
                        '<div>权限名称：<input id="authc-add-authcname"></div>' +
                        '<div>权限描述：<input id="authc-add-authcdesc"></div>' +
                        '<button class="layui-btn layui-btn-sm" id="authc-add-confirm">确定</button>' +
                        '</div>'
                });
            });


            //确定参加
            $(document).on('click','#authc-add-confirm',function () {

                //检验合法性
                if(Authc.methods.checkAuthcNameExist('sys:user:page')){
                    layer.alert('用户名已存在');
                    return false;
                }
                layer.alert('添加成功');
                layer.close(addLayer);
            });
        },
        checkAuthcNameExist: function (authcName) {
            var exist = true;
            $.ajax({
                method: "GET",
                url: Authc.URL.checkAuthcNameExist(authcName),
                dataType: 'json',
                async: false,
                success: function (result) {
                    var success = result['success'];
                    if (success == 1) { //不存在
                        exist = false;
                    }
                }
            });
            return exist;
        }
    }
};
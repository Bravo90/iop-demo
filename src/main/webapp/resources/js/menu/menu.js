$(function () {
    Menu.init();
});

var Menu = {
    URL: {
        parentAuthc: function () {
            return Globals.contextPath() + '/authc/plist';
        },
        parentMenu: function () {
            return Globals.contextPath() + '/menu/plist';
        }
    },
    init: function () {
        Menu.methods.renderParentMenuTable();
        Menu.methods.addMenu();
        Menu.methods.getMenuAuthc();
    },
    methods: {
        renderParentMenuTable: function () {
            $.get(Menu.URL.parentMenu(), {}, function (result) {
                $('tBody').empty();
                var list = result.data;
                $(list).each(function (index) {
                    $('#tbody').append('<tr>' +
                        '<td>' + this.menuId + '</td>' +
                        '<td>' + this.menuName + '</td>' +
                        '<td>' + this.menuUrl + '</td>' +
                        '<td>' +
                        '<button class="layui-btn layui-btn-xs menu-url_edit"></button>' +
                        '</td>' +
                        '</tr>'
                    );
                });
            });
        },
        addMenu: function () {
            var addLayer;
            $('#menu-add').on('click', function () {
                addLayer = layer.open({
                    type: 1,
                    title: '新增菜单',
                    anim: 1,
                    skin: 'layui-layer-molv',
                    area: ['290px', '310px'], //宽高
                    content: '<div class="rbac-user-update bg-image">' +
                    '<div>菜单名称：<input id="menu-add-name"></div>' +
                    '<div>菜单地址：<input id="menu-add-url"></div>' +
                    '<div>父级菜单：<select id="menu-add-pname">' +
                    Menu.methods.getParentMenu() +
                    '</select></div>' +
                    '<div>菜单顺序：<input id="menu-add-order"></div>' +
                    '<div>菜单权限：<select id="menu-add-authc">' +
                    Menu.methods.getMenuAuthc() +
                    '</select></div>' +
                    '<button class="layui-btn layui-btn-sm" id="menu-add-confirm">确定</button>' +
                    '</div>'
                });
            });
        },
        getParentMenu: function () {
            var content = '<option value="0">根菜单(0级菜单)</option>';
            $.ajax({
                method: "GET",
                url: Menu.URL.parentMenu(),
                dataType: 'json',
                async: false,
                success: function (result) {
                    var success = result['success'];
                    if (success == 1) {
                        var list = result.data;
                        $(list).each(function () {
                            content = content +
                                '<option value="' +
                                this.menuId + '">' +
                                this.menuName + '(' + this.menuLevel + '级菜单)</option>';
                        });
                    }
                }
            });
            return content;
        },

        getMenuAuthc: function () {
            var content = '<option value="0">  ——请选择—— </option>';
            $.ajax({
                method: "GET",
                url: Menu.URL.parentAuthc(),
                dataType: 'json',
                async: false,
                success: function (result) {
                    var success = result['success'];
                    if (success == 1) {
                        var list = result.data;
                        $(list).each(function () {
                            content = content +
                                '<option value="' +
                                this.authcId + '">' +
                                this.authcDesc + '</option>';
                        });
                    }
                }
            });
            return content;
        }
    }
};
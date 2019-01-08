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
        Menu.methods.renderSubMenuTable()
        Menu.methods.addMenu();
        Menu.methods.addSubMenu();
        Menu.methods.getMenuAuthc();
    },
    methods: {
        renderParentMenuTable: function () {
            $.get(Menu.URL.parentMenu(), {}, function (result) {
                $('tBody').empty();
                var list = result.data;
                $(list).each(function (index) {
                    $('#tbody').append('<tr>' +
                        '<td class="sub-open">' + this.menuId +
                        '<i class="layui-icon layui-icon-down" style="font-size: 16px; color: black;font-weight:bold;margin-left: 10px"></i>' +
                        '</td>' +
                        '<td>' + this.menuName + '</td>' +
                        '<td>' + this.menuUrl + '</td>' +
                        '<td>' +
                        '<button class="layui-btn layui-btn-xs submenu-add" p-id="' + this.menuId + '" p-name="' + this.menuName + '">新增子菜单</button>' +
                        '<button class="layui-btn layui-btn-xs menu-edit" p-id="' + this.menuId + '">删除</button>' +
                        '<button class="layui-btn layui-btn-xs menu-delete" p-id="' + this.menuId + '">修改</button>' +
                        '</td>' +
                        '</tr>'
                    );
                });
            });
        },
        renderSubMenuTable: function () {
            $(document).on('click', '.sub-open', function () {
                $(this).find('i').removeClass('layui-icon-down').addClass('layui-icon-up');
                $(this).removeClass('sub-open').addClass('sub-close');
            });
            $(document).on('click', '.sub-close', function () {
                $(this).find('i').removeClass('layui-icon-up').addClass('layui-icon-down');
                $(this).removeClass('sub-close').addClass('sub-open');
            });
        },
        addMenu: function () {
            var addLayer;
            $('#menu-add').on('click', function () {
                addLayer = layer.open({
                    type: 1,
                    title: '新增根菜单',
                    anim: 1,
                    skin: 'layui-layer-molv',
                    area: ['290px', '190px'], //宽高
                    content: '<div class="rbac-user-update bg-image">' +
                    '<div>菜单名称：<input id="rootmenu-add-name"></div>' +
                    '<div>菜单顺序：<input id="rootmenu-add-order" type="number"></div>' +
                    '<button class="layui-btn layui-btn-sm" id="rootmenu-add-confirm">确定</button>' +
                    '</div>'
                });
            });
        },
        addSubMenu: function () {
            $(document).on('click', '.submenu-add', function () {
                addSubLayer = layer.open({
                    type: 1,
                    title: '新增子菜单',
                    anim: 1,
                    skin: 'layui-layer-molv',
                    area: ['290px', '310px'], //宽高
                    content: '<div class="rbac-user-update bg-image">' +
                    '<div>菜单名称：<input id="menu-add-name"></div>' +
                    '<div>菜单地址：<input id="menu-add-url"></div>' +
                    '<div>父级菜单：<input id="menu-add-pmenu" readonly></div>' +
                    '<div>菜单顺序：<input id="menu-add-order" type="number"></div>' +
                    '<div>菜单权限：<select id="menu-add-authcid">' +
                    Menu.methods.getMenuAuthc() +
                    '</select></div > ' +
                    '<button class="layui-btn layui-btn-sm" id="menu-add-confirm">确定</button>' +
                    '</div>'
                });
            });
        },
        getParentMenu: function () {
            var content = '<option value="0">根菜单</option>';
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
                                this.menuName +
                                '</option>';
                        });
                    }
                }
            });
            return content;
        },

        getMenuAuthc: function () {
            var content = '';
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
$(function () {
    User.init();
});
var User = {

    URL: {
        userList: function () {
            return Globals.contextPath() + '/user/list';
        },
        userSingle: function (userId) {
            return Globals.contextPath() + '/user/' + userId;
        },
        checkExistByUsername: function (username) {
            return Globals.contextPath() + '/user/username/' + username;
        },
        userAdd: function () {
            return Globals.contextPath() + '/user/add';
        },
        userUpdate: function () {
            return Globals.contextPath() + '/user/update';
        },
        userDelete: function (userId) {
            return Globals.contextPath() + '/user/' + userId;
        },
        userAssign: function (userId) {
            return Globals.contextPath() + '/user/assign/' + userId;
        },
        userAssignUpdate: function () {
            return Globals.contextPath() + '/user/assign';
        }
    },
    init: function () {
        var layer = layui.layer;
        /**
         * 查询用户列表
         */
        User.methods.renderTable();
        /**
         * 角色分配
         */
        User.methods.assignRole(layer);
        /**
         * 新增用户
         */
        $('#user-add').on('click', function () {
            var addLayer = layer.open({
                type: 1,
                title: '新增用户',
                anim: 1,
                skin: 'layui-layer-molv',
                area: ['290px', '230px'], //宽高
                content: '<div class="rbac-user-update bg-image">' +
                '<div>用户名称：<input id="user-add-username"></div>' +
                '<div>用户密码：<input id="user-add-password"></div>' +
                '<div>用户昵称：<input id="user-add-nickname"></div>' +
                '<button class="layui-btn layui-btn-sm" id="user-add-confirm">确定</button>' +
                '</div>'
            });

            $('#user-add-username').on('blur', function () {
                var username = $(this).val();
                if (User.methods.checkUsernameExist(username)) {
                    layer.tips('用户名已存在', '#user-add-username');
                }
            });

            $('#user-add-confirm').unbind("click").on('click', function () {
                var username = $.trim($('#user-add-username').val());
                var password = $.trim($('#user-add-password').val());
                var nickname = $.trim($('#user-add-nickname').val());

                if (!Globals.validate.validateNull(username, password, nickname)) {
                    layer.msg('请将界面信息填写完整');
                    return false;
                }
                /*判断用户是否已存在*/
                if (User.methods.checkUsernameExist(username)) {
                    layer.msg('用户名已存在');
                    return false;
                }

                var user = {
                    "username": username,
                    "password": password,
                    "nickname": nickname
                };

                $.ajax({
                    type: "POST",
                    url: User.URL.userAdd(),
                    contentType: "application/json;charset=utf-8",
                    data: JSON.stringify(user),
                    dataType: "json",
                    success: function (result) {
                        var success = result['success'];
                        var msg = result['message'];
                        if (success == 1) {
                            layer.close(addLayer);
                            layer.msg(msg, {icon: 1});
                            User.methods.renderTable();
                        } else {
                            layer.msg(msg, {icon: 2});
                        }
                    }
                });
            });
        });

        /**
         * 用户删除
         */
        $(document).on('click', '.user-delete', function () {
            var userId = $(this).attr('user-id');
            layer.confirm('确定删除？', {
                skin: 'layui-layer-molv',
                btn: ['确定', '取消'] //按钮
            }, function () {
                $.ajax({
                    type: "delete",
                    url: User.URL.userDelete(userId),
                    data: {},
                    dataType: "json",
                    success: function (result) {
                        var success = result['success'];
                        var msg = result['message'];
                        if (success == 1) {
                            layer.msg(msg, {icon: 1});
                            User.methods.renderTable();
                        } else {
                            layer.msg(msg, {icon: 2});
                        }
                    }
                });
            });

        });
        /**
         * 用户更新
         */
        $(document).on('click', '.user-update', function () {
            var userId = $(this).attr('user-id');
            var usernameOld = '';
            var passwordOld = '';
            var nicknameOld = '';
            $.get(User.URL.userSingle(userId), {}, function (result) {
                if (result['success'] == 1) {
                    usernameOld = result.data['username'];
                    passwordOld = result.data['password'];
                    nicknameOld = result.data['nickname'];
                }
                var updateLayer = layer.open({
                    type: 1,
                    title: '更新用户',
                    skin: 'layui-layer-molv',
                    anim: 1,
                    area: ['290px', '230px'], //宽高
                    content: '<div class="rbac-user-update bg-image">' +
                    '<div>用户名称：<input id="user-update-username"></div>' +
                    '<div>用户密码：<input type="password" id="user-update-password"></div>' +
                    '<div>用户昵称：<input id="user-update-nickname"></div>' +
                    '<button class="layui-btn layui-btn-sm" id="user-update-confirm">确定</button>' +
                    '</div>'
                });

                $('#user-update-username').val(usernameOld);
                $('#user-update-password').val(passwordOld);
                $('#user-update-nickname').val(nicknameOld);

                $('#user-update-confirm').unbind("click").on('click', function () {

                    var username = $.trim($('#user-update-username').val());
                    var password = $.trim($('#user-update-password').val());
                    var nickname = $.trim($('#user-update-nickname').val());

                    if (!Globals.validate.validateNull(username, password, nickname)) {
                        layer.msg('请将界面信息填写完整');
                        return false;
                    }

                    if (username != usernameOld && User.methods.checkUsernameExist(username)) {
                        layer.msg('用户名已存在');
                        return false;
                    }

                    var user = {
                        "userId": userId,
                        "username": username,
                        "password": password,
                        "nickname": nickname
                    };

                    $.ajax({
                        type: "PUT",
                        url: User.URL.userUpdate(),
                        contentType: "application/json;charset=utf-8",
                        data: JSON.stringify(user),
                        dataType: "json",
                        success: function (result) {
                            var success = result['success'];
                            var msg = result['message'];
                            if (success == 1) {
                                layer.close(updateLayer);
                                layer.msg(msg, {icon: 1});
                                User.methods.renderTable();
                            } else {
                                console.log(result['message']);
                                layer.msg(msg, {icon: 2});
                            }
                        }
                    });
                });
            });
        });

    },
    methods: {
        renderTable: function () {
            $.get(User.URL.userList(), {}, function (result) {
                $('#tbody').empty();
                var list = result.data.list;
                if (list.length > 0) {
                    $(list).each(function () {
                        $('#tbody').append('<tr>' +
                            '<th>' + this.userId + '</th>' +
                            '<th>' + this.username + '</th>' +
                            '<th>' + this.nickname + '</th>' +
                            '<th>  ******  </th>' +
                            '<th>' +
                            '<button class="layui-btn layui-btn-xs user-delete" user-id="' + this.userId + '">删除</button>' +
                            '<button class="layui-btn layui-btn-xs user-update" user-id="' + this.userId + '">更新</button>' +
                            '<button class="layui-btn layui-btn-xs user-role" user-id="' + this.userId + '">分配角色</button>' +
                            '</th>' +
                            '</tr>'
                        );
                    });
                } else {
                    $('#tbody').append('<tr><td colspan="8” style="text-align: center;color:red;">暂无数据</td></tr>');
                }
            });
        },
        checkUsernameExist: function (username) {
            var exist = true;
            $.ajax({
                method: "GET",
                url: User.URL.checkExistByUsername(username),
                dataType: 'json',
                async: false,
                success: function (result) {
                    var success = result['success'];
                    if (success == 1) {
                        exist = false;
                    }
                }
            });
            return exist;
        },
        assignRole: function (layer) {
            var userId;
            var assignLayer;
            $(document).on('click', '.user-role', function () {
                userId = $(this).attr('user-id');
                $.get(User.URL.userAssign(userId), {}, function (result) {
                    var success = result['success'];
                    var msg = result['message'];
                    if (success == 0) {
                        layer.msg(msg, {icon: 2});
                        layer.close(assignLayer);
                        return false;
                    }

                    var userRoles = result.data['userRoles'];
                    var roles = result.data['allRoles'];
                    //渲染所有角色
                    var content = User.methods.assignUserContent(roles);
                    assignLayer = layer.open({
                        type: 1,
                        title: '角色分配',
                        anim: 1,
                        skin: 'layui-layer-molv',
                        area: ['290px', '400px'], //宽高
                        content: content
                    });
                    //渲染已有的角色
                    $(userRoles).each(function () {
                        var roleId = this.roleId;
                        $('input[role-id=' + roleId + ']').attr("checked", 'true');
                    });
                });
            });
            //分配角色确定
            $(document).on('click', '#user-role-confirm', function () {
                var checkBoxes = $('input[role-id]');
                var roleIds = new Array();
                $(checkBoxes).each(function () {
                    if ($(this).is(':checked')) {
                        roleIds.push($(this).attr('role-id'));
                    }
                });
                $.post(User.URL.userAssignUpdate(), {
                    'userId': userId,
                    'roleIds': roleIds.join(",")
                }, function (result) {
                    var success = result['success'];
                    var msg = result['message'];
                    if (success == 1) {
                        layer.msg(msg, {icon: 1});
                        layer.close(assignLayer);
                    } else {
                        layer.msg(msg, {icon: 2});
                    }
                });

            });
        },
        assignUserContent: function (roles) {
            var content = '<div class="assign-role-box"><div class="assign-role-box-inner">';
            $(roles).each(function () {
                content += '<div class="checkbox-style"><input type="checkbox" role-id="' +
                    this.roleId + '"></div><div class="checkbox-text">' +
                    this.roleName +
                    '</div>';
            });
            var button = '<button id="user-role-confirm" class="layui-btn layui-btn-sm">确定</button>';
            return content + '</div>' + button + '</div>';
        }
    }

};
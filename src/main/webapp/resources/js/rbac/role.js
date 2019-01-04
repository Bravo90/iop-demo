$(function () {
    Role.init();
});

var Role = {
    URL: {
        roleList: function () {
            return Globals.contextPath() + '/role/list';
        },
        roleSingle: function (roleId) {
            return Globals.contextPath() + '/role/' + roleId;
        },
        roleAdd: function () {
            return Globals.contextPath() + '/role/add';
        },
        checkRoleNameExist: function (roleName) {
            return Globals.contextPath() + '/role/rolename/' + roleName;
        },
        roleDelete: function (roleId) {
            return Globals.contextPath() + '/role/' + roleId;
        },
        roleUpdate: function (roleId) {
            return Globals.contextPath() + '/role/' + roleId;
        },
        roleAuthc: function (roleId) {
            return Globals.contextPath() + '/role/authc/' + roleId;
        },
        roleAssignAuthc: function () {
            return Globals.contextPath() + '/role/assign';
        }

    },
    init: function () {
        var layer = layui.layer;
        Role.methods.renderTable();
        Role.methods.addRole(layer);
        Role.methods.deleteRole(layer);
        Role.methods.updateRole(layer);
        Role.methods.getRoleAuthc();
        Role.methods.assignRoleAuthc();
    },
    methods: {
        renderTable: function () {
            $.get(Role.URL.roleList(), {}, function (result) {
                $('tBody').empty();
                var list = result.data.list;
                $(list).each(function () {
                    $('#tbody').append('<tr>' +
                        '<th>' + this.roleId + '</th>' +
                        '<th>' + this.roleName + '</th>' +
                        '<th>' + this.roleDesc + '</th>' +
                        '<th>' +
                        '<button  class="layui-btn layui-btn-xs role-delete" role-id="' + this.roleId + '">删除</button>' +
                        '<button class="layui-btn layui-btn-xs role-update" role-id="' + this.roleId + '">更新</button>' +
                        '<button class="layui-btn layui-btn-xs role-authc" role-id="' + this.roleId + '">分配权限</button>' +
                        '</th>' +
                        '</tr>'
                    );
                });
            });
        },
        addRole: function (layer) {
            var addLayer;
            //新增弹出框
            $('#role-add').on('click', function () {
                addLayer = layer.open({
                    type: 1,
                    title: '新增角色',
                    anim: 1,
                    skin: 'layui-layer-molv',
                    area: ['290px', '190px'], //宽高
                    content: '<div class="rbac-user-update bg-image">' +
                    '<div>角色名称：<input id="role-add-rolename"></div>' +
                    '<div>角色描述：<input id="role-add-roledesc"></div>' +
                    '<button class="layui-btn layui-btn-sm" id="role-add-confirm">确定</button>' +
                    '</div>'
                });
            });
            //检验用户存在与否
            $(document).on('blur', '#role-add-rolename', function () {
                var roleName = $('#role-add-rolename').val();
                if (roleName != '') {
                    if (Role.methods.checkRoleNameExist(roleName)) {
                        layer.tips('角色名已存在', '#role-add-rolename');
                    }
                }
            });

            //确定增加
            $(document).on('click', "#role-add-confirm", function () {
                var roleName = $('#role-add-rolename').val();
                var roleDesc = $('#role-add-roledesc').val();

                if (!Globals.validate.validateNull(roleName, roleDesc)) {
                    layer.msg('请将界面信息填写完整', {icon: 2});
                    return false;
                }
                if (Role.methods.checkRoleNameExist(roleName)) {
                    layer.msg('角色名称已存在', {icon: 2});
                    return false;
                }

                var role = {
                    roleName: roleName,
                    roleDesc: roleDesc
                };

                $.ajax({
                    type: "POST",
                    url: Role.URL.roleAdd(),
                    contentType: "application/json;charset=utf-8",
                    data: JSON.stringify(role),
                    dataType: "json",
                    success: function (result) {
                        var success = result['success'];
                        var msg = result['message'];
                        if (success == 1) {
                            layer.close(addLayer);
                            layer.msg(msg, {icon: 1});
                            Role.methods.renderTable();
                        } else {
                            layer.msg(msg, {icon: 2});
                        }
                    }
                });
            })
        },
        deleteRole: function (layer) {
            $(document).on('click', '.role-delete', function () {
                var roleId = $(this).attr('role-id');
                layer.confirm('确定删除？', {
                    skin: 'layui-layer-molv',
                    btn: ['确定', '取消'] //按钮
                }, function () {
                    $.ajax({
                        type: "delete",
                        url: Role.URL.roleDelete(roleId),
                        data: {},
                        dataType: "json",
                        success: function (result) {
                            var success = result['success'];
                            var msg = result['message'];
                            if (success == 1) {
                                layer.msg(msg, {icon: 1});
                                Role.methods.renderTable();
                            } else {
                                layer.msg(msg, {icon: 2});
                            }
                        }
                    });
                });
            });
        },
        updateRole: function () {
            var updateLayer;
            var url = '';
            var roleNameOld = '';
            var roleDescOld = '';
            var roleId;
            $(document).on('click', '.role-update', function () {
                roleId = $(this).attr('role-id');
                url = Role.URL.roleUpdate(roleId);

                $.get(Role.URL.roleSingle(roleId), {}, function (result) {
                    if (result['success'] == 1) {
                        roleNameOld = result.data['roleName'];
                        roleDescOld = result.data['roleDesc'];
                        updateLayer = layer.open({
                            type: 1,
                            title: '更新角色',
                            anim: 1,
                            skin: 'layui-layer-molv',
                            area: ['290px', '190px'], //宽高
                            content: '<div class="rbac-user-update bg-image">' +
                            '<div>角色名称：<input id="role-update-rolename"></div>' +
                            '<div>角色描述：<input id="role-update-roledesc"></div>' +
                            '<button class="layui-btn layui-btn-sm" id="role-update-confirm">确定</button>' +
                            '</div>'
                        });
                        $('#role-update-rolename').val(roleNameOld);
                        $('#role-update-roledesc').val(roleDescOld);
                    } else {
                        layer.msg(result['message'], {icon: 2});
                    }
                });
            });

            //更新
            $(document).on('click', "#role-update-confirm", function () {

                var roleName = $('#role-update-rolename').val();
                var roleDesc = $('#role-update-roledesc').val();

                if (!Globals.validate.validateNull(roleName)) {
                    layer.msg('请将界面信息填写完整');
                    return false;
                }

                if (roleName != roleNameOld && Role.methods.checkRoleNameExist(roleName)) {
                    layer.msg('角色名已存在');
                    return false;
                }

                var role = {
                    'roleName': roleName,
                    'roleDesc': roleDesc
                };

                $.ajax({
                    type: "PUT",
                    url: Role.URL.roleUpdate(roleId),
                    contentType: "application/json;charset=utf-8",
                    data: JSON.stringify(role),
                    dataType: "json",
                    success: function (result) {
                        var success = result['success'];
                        var msg = result['message'];
                        if (success == 1) {
                            layer.close(updateLayer);
                            layer.msg(msg, {icon: 1});
                            Role.methods.renderTable();
                        } else {
                            console.log(result['message']);
                            layer.msg(msg, {icon: 2});
                        }
                    }
                });
            });
        },
        getRoleAuthc: function () {
            $(document).on('click', '.role-authc', function () {
                var roleId = $(this).attr('role-id');
                $.get(Role.URL.roleAuthc(roleId), {}, function (result) {
                    var success = result['success'];
                    if (success == 1) {
                        layer.open({
                            type: 1,
                            title: '分配权限',
                            anim: 1,
                            skin: 'layui-layer-molv',
                            area: ['270px', '350px'], //宽高
                            content: '<div class="rbac-autch">' +
                            '<div><ul id="tree-demo" class="ztree"></ul></div>' +
                            '<button class="layui-btn layui-btn-sm" id="autch-role-confirm">确定</button>' +
                            '</div>'
                        });
                        var setting = {
                            role: roleId,
                            check: {
                                enable: true
                            },
                            data: {
                                simpleData: {
                                    enable: true
                                }
                            }
                        };
                        $.fn.zTree.init($("#tree-demo"), setting, result['data']);
                    } else {
                        layer.msg(result['message'], {icon: 2});
                    }
                });
            });
        },
        assignRoleAuthc: function () {
            $(document).on('click', '#autch-role-confirm', function () {
                var tree = $.fn.zTree.getZTreeObj('tree-demo');
                var roleId = tree.setting['role'];
                var nodesChanges = tree.getChangeCheckedNodes();
                var del = new Array();
                var add = new Array();
                if (nodesChanges.length > 0) {
                    for (var i = 0; i < nodesChanges.length; i++) {
                        if (nodesChanges[i]['checked']) {
                            add.push(nodesChanges[i]['authcId'])
                        } else {
                            del.push(nodesChanges[i]['authcId']);
                        }
                    }
                    //保存
                    $.ajax({
                        type: 'PUT',
                        url: Role.URL.roleAssignAuthc(),
                        dataType: 'json',
                        data: {
                            'roleId': roleId,
                            'add': add.join(','),
                            'del': del.join(',')
                        },
                        success: function (result) {
                            var msg = result['message'];
                            if (result['success'] == 1) {
                                layer.msg(msg, {icon: 1});
                            } else {
                                layer.msg(msg, {icon: 2});
                            }
                        }
                    });
                }else{
                    layer.msg('未有改动！');
                }
            });
        },
        checkRoleNameExist: function (roleName) {
            var exist = true;
            $.ajax({
                method: 'GET',
                url: Role.URL.checkRoleNameExist(roleName),
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
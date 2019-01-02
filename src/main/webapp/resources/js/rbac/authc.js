$(function () {
    Authc.init();
});

var Authc = {
        URL: {
            getAuthcById: function (authcId) {
                return Globals.contextPath() + '/authc/' + authcId;
            },
            parentAuthc: function () {
                return Globals.contextPath() + '/authc/plist';
            },
            childrenAuthc: function (pid) {
                return Globals.contextPath() + '/authc/clist/' + pid;
            },
            addAuthc: function () {
                return Globals.contextPath() + '/authc/add';
            },
            deleteAuthc: function (authcId) {
                return Globals.contextPath() + '/authc/' + authcId;
            },
            updateAuthc: function (authcId) {
                return Globals.contextPath() + '/authc/' + authcId;
            },
            checkAuthcNameExist: function (authcName) {
                return Globals.contextPath() + '/authc/authcname/' + authcName;
            }
        },
        init: function () {
            var layer = layui.layer;
            Authc.methods.renderParentAuthcTable();
            Authc.methods.addAuthc(layer);
            Authc.methods.deleteAuthc();
            Authc.methods.updateAuthc();
            Authc.methods.getAuthcContent();
            Authc.methods.showChildrenAuthcTable();
            Authc.methods.hideChildrenAuthcTable();

        },
        methods: {
            renderParentAuthcTable: function () {
                $.get(Authc.URL.parentAuthc(), {}, function (result) {
                    $('tBody').empty();
                    var list = result.data;
                    $(list).each(function (index) {
                        $('#tbody').append('<tr authc-id="' + this.authcId + '">' +
                            '<th>' + this.authcId + '</th>' +
                            '<th>' + this.authcName + '</th>' +
                            '<th>' + this.authcDesc + '</th>' +
                            '<th>' +
                            '<button class="layui-btn layui-btn-xs authc-delete" authc-id="' + this.authcId + '">删除</button>' +
                            '<button class="layui-btn layui-btn-xs authc-update" authc-id="' + this.authcId + '">更新</button>' +
                            '<button class="layui-btn layui-btn-xs authc-children-show" authc-id="' + this.authcId + '">' +
                            '<i class="layui-icon layui-icon-down" style="font-size: 30px; color: white;"></i>功能权限</button>' +
                            '</th>' +
                            '</tr>');
                    });
                });
            },
            showChildrenAuthcTable: function () {
                $(document).on('click', '.authc-children-show', function () {
                    $(this).removeClass('authc-children-show').addClass('authc-children-hide');
                    $(this).find('i').removeClass('layui-icon-down').addClass('layui-icon-up');
                    var authcId = $(this).attr('authc-id');
                    $.get(Authc.URL.childrenAuthc(authcId), {}, function (result) {
                        var success = result['success'];
                        if (success == 1) {
                            var list = result['data'];
                            if (list.length > 0) {
                                $(list).each(function () {
                                    $('tr[authc-id=' + authcId + ']').after('<tr p-id="' + authcId + '" style="background-color: #F2F2F2;color: #79C48C">' +
                                        '<td>' + this.authcId + '</td>' +
                                        '<td>' + this.authcName + '</td>' +
                                        '<td>' + this.authcDesc + '</td>' +
                                        '<th>' +
                                        '<button class="layui-btn layui-btn-primary layui-btn-xs authc-delete" authc-id="' + this.authcId + '">删除</button>' +
                                        '<button class="layui-btn layui-btn-primary layui-btn-xs authc-update" authc-id="' + this.authcId + '">更新</button>' +
                                        '</th>' +
                                        '</tr>');
                                });
                            } else {
                                layer.msg('暂时没有功能权限');
                            }
                        } else {
                            layer.msg('暂时没有功能权限');
                        }
                    });
                })
            },
            hideChildrenAuthcTable: function () {
                $(document).on('click', '.authc-children-hide', function () {
                    $(this).removeClass('authc-children-hide').addClass('authc-children-show');
                    $(this).find('i').removeClass('layui-icon-up').addClass('layui-icon-down');
                    var authcId = $(this).attr('authc-id');
                    $('tr[p-id=' + authcId + ']').remove();
                });
            },
            addAuthc: function () {
                var addLayer;
                $('#authc-add').on('click', function () {
                    addLayer = layer.open({
                        type: 1,
                        title: '新增权限',
                        anim: 1,
                        skin: 'layui-layer-molv',
                        area: ['290px', '220px'], //宽高
                        content: '<div class="rbac-user-update bg-image">' +
                        '<div>分类选择：<select id="autch-add-authcid">' +
                        Authc.methods.getAuthcContent() +
                        '</select></div>' +
                        '<div>权限名称：<input id="authc-add-authcname"></div>' +
                        '<div>权限描述：<input id="authc-add-authcdesc"></div>' +
                        '<button class="layui-btn layui-btn-sm" id="authc-add-confirm">确定</button>' +
                        '</div>'
                    });
                });

                //检验权限存在与否
                $(document).on('blur', '#authc-add-authcname', function () {
                    var authcName = $('#authc-add-authcname').val();
                    if (Authc.methods.checkAuthcNameExist(authcName)) {
                        layer.tips('权限名已存在', '#authc-add-authcname');
                    }
                });

                //确定增加
                $(document).on('click', '#authc-add-confirm', function () {

                    var authcName = $('#authc-add-authcname').val();
                    var authcDesc = $('#authc-add-authcdesc').val();
                    var authcId = $('#autch-add-authcid').find('option:selected').val();
                    console.log(authcId)

                    //空检验
                    if (!Globals.validate.validateNull(authcName, authcDesc)) {
                        layer.msg('请将界面信息填写完整', {icon: 2});
                        return false;
                    }

                    //检验合法性
                    if (Authc.methods.checkAuthcNameExist(authcName)) {
                        layer.alert('权限名称已存在');
                        return false;
                    }

                    var authc = {
                        authcId: authcId,
                        authcName: authcName,
                        authcDesc: authcDesc
                    }

                    $.ajax({
                        type: 'POST',
                        url: Authc.URL.addAuthc(),
                        contentType: 'application/json;charset=utf-8',
                        data: JSON.stringify(authc),
                        dateType: 'json',
                        success: function (result) {
                            var success = result['success'];
                            var msg = result['message'];
                            if (success == 1) {
                                layer.close(addLayer);
                                layer.msg(msg, {icon: 1});
                                Authc.methods.renderParentAuthcTable();
                            } else {
                                layer.msg(msg, {icon: 2});
                            }
                        }
                    });
                });
            },
            deleteAuthc: function () {
                $(document).on('click', '.authc-delete', function () {
                    var authcId = $(this).attr('authc-id');
                    layer.confirm('确定删除？', {
                        skin: 'layui-layer-molv',
                        btn: ['确定', '取消'] //按钮
                    }, function () {
                        $.ajax({
                            type: 'delete',
                            url: Authc.URL.deleteAuthc(authcId),
                            data: {},
                            dataType: "json",
                            success: function (result) {
                                var success = result['success'];
                                var msg = result['message'];
                                if (success == 1) {
                                    layer.msg(msg, {icon: 1});
                                    Authc.methods.renderParentAuthcTable();
                                } else {
                                    layer.msg(msg, {icon: 2});
                                }
                            }
                        });
                    })
                });
            },
            updateAuthc: function () {
                var authcId;
                var updateLayer;
                var authcNameOld = '';
                var authcDescOld = '';
                $(document).on('click', '.authc-update', function () {
                    authcId = $(this).attr('authc-id');
                    $.get(Authc.URL.getAuthcById(authcId), {}, function (result) {
                        if (result['success'] == 1) {
                            authcNameOld = result.data['authcName'];
                            authcDescOld = result.data['authcDesc'];
                        }
                        updateLayer = layer.open({
                            type: 1,
                            title: '更新角色',
                            anim: 1,
                            skin: 'layui-layer-molv',
                            area: ['290px', '190px'], //宽高
                            content: '<div class="rbac-user-update bg-image">' +
                            '<div>角色名称：<input id="authc-update-authcname"></div>' +
                            '<div>角色描述：<input id="authc-update-authcdesc"></div>' +
                            '<button class="layui-btn layui-btn-sm" id="authc-update-confirm">确定</button>' +
                            '</div>'
                        });
                        $('#authc-update-authcname').val(authcNameOld);
                        $('#authc-update-authcdesc').val(authcDescOld);
                    });
                });
                //更新确定
                $(document).on('click', '#authc-update-confirm', function () {
                    var authcName = $('#authc-update-authcname').val();
                    var authcDesc = $('#authc-update-authcdesc').val();

                    //检查是否为空
                    if (!Globals.validate.validateNull(authcName, authcDesc)) {
                        layer.msg('请将界面信息填写完整');
                        return false;
                    }
                    //检查是否已存在
                    if (authcName != authcNameOld && Authc.methods.checkAuthcNameExist(authcName)) {
                        layer.msg('权限名已存在');
                        return false;
                    }
                    //执行保存
                    var authc = {
                        authcName: authcName,
                        authcDesc: authcDesc
                    }

                    $.ajax({
                        type: 'put',
                        url: Authc.URL.updateAuthc(authcId),
                        contentType: "application/json;charset=utf-8",
                        data: JSON.stringify(authc),
                        dataType: "json",
                        success: function (result) {
                            var success = result['success'];
                            var msg = result['message'];
                            if (success == 1) {
                                layer.close(updateLayer);
                                layer.msg(msg, {icon: 1});
                                Authc.methods.renderParentAuthcTable();
                            } else {
                                console.log(result['message']);
                                layer.msg(msg, {icon: 2});
                            }
                        }
                    });
                });
            },
            checkAuthcNameExist: function (authcName) {
                var exist = false;
                if (authcName != '') {
                    $.ajax({
                        method: "GET",
                        url: Authc.URL.checkAuthcNameExist(authcName),
                        dataType: 'json',
                        async: false,
                        success: function (result) {
                            var success = result['success'];
                            if (success == 0) { //不存在
                                exist = true;
                            }
                        }
                    });
                }
                return exist;
            }
            ,
            getAuthcContent: function () {
                var authcContent = '<option value="1000">界面权限(界面权限)</option>';
                $.ajax({
                    method: "GET",
                    url: Authc.URL.parentAuthc(),
                    dataType: 'json',
                    async: false,
                    success: function (result) {
                        var success = result['success'];
                        if (success == 1) {
                            var list = result.data;
                            $(list).each(function () {
                                authcContent = authcContent +
                                    '<option value="' +
                                    this.authcId + '">' +
                                    this.authcDesc + '(功能权限)</option>';
                            });
                        }
                    }
                });
                return authcContent;
            }
        }
    }
;
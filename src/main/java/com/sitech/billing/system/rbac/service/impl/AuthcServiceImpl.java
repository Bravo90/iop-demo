package com.sitech.billing.system.rbac.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sitech.billing.common.enums.ErrorMsgEnum;
import com.sitech.billing.common.exception.IopException;
import com.sitech.billing.system.rbac.dao.AuthcMapper;
import com.sitech.billing.system.rbac.model.Authc;
import com.sitech.billing.system.rbac.model.Role;
import com.sitech.billing.system.rbac.service.AuthcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("authcService")
public class AuthcServiceImpl implements AuthcService {

    @Autowired
    private AuthcMapper authcMapper;

    @Override
    public PageInfo<Authc> listAllAuthc(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Authc> authcList = authcMapper.listAllAuthc();
        PageInfo pageInfo = new PageInfo(authcList);
        return pageInfo;
    }

    @Override
    public Integer saveAuthc(Authc authc) {
        return authcMapper.saveAuthc(authc);
    }

    @Override
    public Integer delAuthcByAuthcId(Integer authcId) {
        return authcMapper.delAuthcByAuthcId(authcId);
    }

    @Override
    public Integer updateAuthc(Authc authc) {
        if (authc.getAuthcId() != null) {
            return authcMapper.updateAuthc(authc);
        } else {
            throw new IopException(ErrorMsgEnum.AUTHC_ID_IS_NULL);
        }
    }

    @Override
    public Authc getAuthcByAuthcId(Integer authcId) {
        return authcMapper.getAuthcByAuthcId(authcId);
    }

    @Override
    public Authc getAuthcByAuthcName(String authcName) {
        return authcMapper.getAuthcByAuthcName(authcName);
    }

    /**
     * @param roleList
     * @return TODO:需要优化
     */
    @Override
    public List<Authc> listAuthcByRole(List<Role> roleList) {
        List<Authc> list = new ArrayList<>();
        for (Role role : roleList) {
            List<Authc> authcList = authcMapper.listAuthcByRole(role);
            list.addAll(authcList);
        }
        return list;
    }

    @Override
    public List<Authc> listParentAuthc() {
        return authcMapper.listParentAuthc();
    }

    @Override
    public List<Authc> listAuthcByParentId(Integer parentId) {
        return authcMapper.listAuthcByParentId(parentId);
    }
}

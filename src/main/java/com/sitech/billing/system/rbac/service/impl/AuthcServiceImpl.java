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
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public Integer delAuthcByAuthcId(Integer authcId) {
        authcMapper.deleteRoleAuthcByAuthcId(authcId);
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
     * @return
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
    public List<Authc> listChildrenAuthc() {
        return authcMapper.listChildrenAuthc();
    }

    @Override
    public List<Authc> listAuthcByParentId(Integer parentId) {
        return authcMapper.listAuthcByParentId(parentId);
    }

    @Override
    public Integer getParentAuthcId() {

        Integer maxId = authcMapper.getMaxParentId();
        if (maxId == null) {
            return 1001;
        }
        return maxId + 1;
    }

    @Override
    public Integer getChildAuthcId(Integer parentAuthcId) {
        Integer maxId = authcMapper.getMaxChildId(parentAuthcId);
        if (maxId == null) {
            return parentAuthcId * 1000 + 1;
        }
        return maxId + 1;
    }
}

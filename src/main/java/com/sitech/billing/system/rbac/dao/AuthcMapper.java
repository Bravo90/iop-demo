package com.sitech.billing.system.rbac.dao;

import com.sitech.billing.system.rbac.model.Authc;
import com.sitech.billing.system.rbac.model.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import javax.swing.undo.AbstractUndoableEdit;
import java.util.List;

@Mapper
public interface AuthcMapper {

    List<Authc> listAllAuthc();

    Integer saveAuthc(@Param("authc") Authc authc);

    Integer delAuthcByAuthcId(@Param("authcId") Integer authcId);

    Integer updateAuthc(@Param("authc") Authc authc);

    Authc getAuthcByAuthcId(@Param("authcId") Integer authcId);

    Authc getAuthcByAuthcName(@Param("authcName") String authcName);

    List<Authc> listAuthcByRole(@Param("role") Role role);

    List<Authc> listParentAuthc();

    List<Authc> listAuthcByParentId(@Param("parentId") Integer parentId);

    Integer getMaxParentId();

    List<Authc> listChildrenAuthc();

    Integer getMaxChildId(@Param("parentAuthcId") Integer parentAuthcId);

    Integer deleteRoleAuthcByAuthcId(@Param("authcId") Integer authcId);

}

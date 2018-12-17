package com.sitech.billing.system.rbac.dao;

import com.sitech.billing.system.rbac.model.Authc;
import com.sitech.billing.system.rbac.model.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AuthcMapper {

    List<Authc> listAllAuthc();

    Integer saveAuthc(@Param("authc") Authc authc);

    Integer delAuthcByAuthcId(@Param("authcId") Integer authcId);

    Integer updateAuthc(@Param("authc") Authc authc);

    Authc getAuthcByAuthcId(@Param("authcId") Integer authcId);

    List<Authc> listAuthcByRole(@Param("role") Role role);
}

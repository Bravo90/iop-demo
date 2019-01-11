package com.sitech.billing.customization.table.pagehelper;


import com.github.pagehelper.dialect.AbstractHelperDialect;
import com.github.pagehelper.dialect.helper.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 根据数据库类型获得不同的数据库方言
 * @author sunzhen
 * @date 2019/1/10 16:51
 * @see com.github.pagehelper.page.PageAutoDialect
 */
public class PageDialect {

    private static Map<String, Class<?>> dialectAliasMap = new HashMap();

    public static AbstractHelperDialect getDialect(String dialectStr) throws Exception {
        Class sqlDialectClass = resloveDialectClass(dialectStr);
        AbstractHelperDialect dialect = (AbstractHelperDialect) sqlDialectClass.newInstance();
        return dialect;
    }

    private static Class resloveDialectClass(String className) throws Exception {
        return dialectAliasMap.containsKey(className.toLowerCase()) ? (Class)dialectAliasMap.get(className.toLowerCase()) : Class.forName(className);
    }

    static {
        dialectAliasMap.put("hsqldb", HsqldbDialect.class);
        dialectAliasMap.put("h2", HsqldbDialect.class);
        dialectAliasMap.put("postgresql", HsqldbDialect.class);
        dialectAliasMap.put("phoenix", HsqldbDialect.class);
        dialectAliasMap.put("mysql", MySqlDialect.class);
        dialectAliasMap.put("mariadb", MySqlDialect.class);
        dialectAliasMap.put("sqlite", MySqlDialect.class);
        dialectAliasMap.put("oracle", OracleDialect.class);
        dialectAliasMap.put("db2", Db2Dialect.class);
        dialectAliasMap.put("informix", InformixDialect.class);
        dialectAliasMap.put("sqlserver", SqlServerDialect.class);
        dialectAliasMap.put("sqlserver2012", SqlServer2012Dialect.class);
        dialectAliasMap.put("derby", SqlServer2012Dialect.class);
    }
}

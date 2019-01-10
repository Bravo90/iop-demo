package com.sitech.billing.customization.table.pagehelper;


import com.github.pagehelper.dialect.AbstractHelperDialect;
import com.github.pagehelper.dialect.helper.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sunzhen
 * @date 2019/1/10 16:51
 */
public class PageDialect {
    private static Map<String, Class<?>> dialectAliasMap = new HashMap();

    public AbstractHelperDialect getDialect(String dialectStr) throws Exception {
        Class sqlDialectClass = dialectAliasMap.get(dialectStr);
        AbstractHelperDialect dialect = (AbstractHelperDialect) sqlDialectClass.newInstance();
        return dialect;
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

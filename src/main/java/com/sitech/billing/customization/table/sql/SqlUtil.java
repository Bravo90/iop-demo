package com.sitech.billing.customization.table.sql;

import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLOrderBy;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.statement.*;
import com.alibaba.druid.util.JdbcConstants;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

/**
 * sql处理工具
 *
 * @author sunzhen
 * @date 2019/1/8 16:19
 */
public class SqlUtil {
    public static SQL parseSQLObject(String sqlStatement) {
        List<SQLStatement> list = SQLUtils.parseStatements(sqlStatement, JdbcConstants.MYSQL);
        if (list.size() == 1) {
            SQLSelectStatement sqlSelectStatement = (SQLSelectStatement) list.get(0);
            SQLSelectQueryBlock block = (SQLSelectQueryBlock) sqlSelectStatement.getSelect().getQuery();
            SQL sql = new SQL();
            // selectSQL(sql, block.getSelectList());
            fromSQL(sql, block.getFrom());
            whereSQL(sql, block.getWhere());
            groupBySQL(sql, block.getGroupBy());
            orderBySQL(sql, block.getOrderBy());
            return sql;
        }
        throw new RuntimeException("sql语句解析失败");
    }

    private static void selectSQL(SQL sql, List<SQLSelectItem> selectList) {
        for (SQLSelectItem sqlSelectItem : selectList) {
            sql.SELECT(SQLUtils.toMySqlString(sqlSelectItem));
        }
    }

    private static void fromSQL(SQL sql, SQLTableSource from) {
        sql.FROM(SQLUtils.toMySqlString(from));
    }

    private static void whereSQL(SQL sql, SQLExpr where) {
        if (where != null) {
            sql.WHERE(SQLUtils.toMySqlString(where));
        }
    }

    private static void groupBySQL(SQL sql, SQLSelectGroupByClause groupBy) {
        if (groupBy != null) {
            List<SQLExpr> items = groupBy.getItems();
            for (SQLExpr item : items) {
                sql.GROUP_BY(SQLUtils.toMySqlString(item));
            }
        }
    }

    private static void orderBySQL(SQL sql, SQLOrderBy orderBy) {
        if (orderBy != null) {
            List<SQLSelectOrderByItem> items = orderBy.getItems();
            for (SQLSelectOrderByItem item : items) {
                sql.ORDER_BY(SQLUtils.toMySqlString(item));
            }
        }
    }

    /**
     * 根据sql语句解析SQL语句中字段的名称和别名
     */
    public static List<SQLSelectItem> sqlSelectItems(String sql) {
        List<SQLStatement> list = SQLUtils.parseStatements(sql, JdbcConstants.MYSQL);
        if (list.size() == 1) {
            SQL sQL = new SQL();

            SQLSelectStatement sqlSelectStatement = (SQLSelectStatement) list.get(0);
            SQLSelectQueryBlock block = (SQLSelectQueryBlock) sqlSelectStatement.getSelect().getQuery();
            return block.getSelectList();
        }

        return null;
    }

    public static SQLSelectQueryBlock getSQLSelectQueryBlock(String sql) {

        List<SQLStatement> list = SQLUtils.parseStatements(sql, JdbcConstants.MYSQL);
        if (list.size() == 1) {
            SQLSelectStatement sqlSelectStatement = (SQLSelectStatement) list.get(0);
            SQLSelectQueryBlock block = (SQLSelectQueryBlock) sqlSelectStatement.getSelect().getQuery();
            return block;
        }
        throw new RuntimeException("sql解析错误");
    }
}

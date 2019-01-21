package com.sitech.billing.customization.table.pagehelper;

import com.github.pagehelper.Page;
import com.github.pagehelper.dialect.AbstractHelperDialect;
import com.github.pagehelper.parser.CountSqlParser;
import com.sitech.billing.customization.table.model.request.RequestPageInfo;
import org.apache.ibatis.cache.CacheKey;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

/**
 * 分页处理
 *
 * @author sunzhen
 * @date 2019/1/11 9:16
 */
public class PageHandler {

    private String sql;
    private RequestPageInfo pageInfo;
    private int count;
    private String pageSql;
    private String dbDialect;
    private JdbcTemplate jdbcTemplate;

    public PageHandler(String sql, RequestPageInfo pageInfo, String dbDialect, JdbcTemplate jdbcTemplate) {
        this.sql = sql;
        this.pageInfo = pageInfo;
        this.dbDialect = dbDialect;
        this.jdbcTemplate = jdbcTemplate;
        count();
    }

    private void count() {
        CountSqlParser countSqlParser = new CountSqlParser();
        String countSql = countSqlParser.getSmartCountSql(sql);
        Integer count = jdbcTemplate.queryForObject(countSql, int.class);
        this.count = count;
    }

    public String pageSql(Page page) throws Exception {
        AbstractHelperDialect dialect = PageDialect.getDialect(dbDialect);
        CacheKey cacheKey = new CacheKey();
        String pageSql = dialect.getPageSql(sql, page, cacheKey);
        System.err.println(pageSql);
        return pageSql;
    }

    public List<Map<String, Object>> pageResult() throws Exception {
        Page<Map<String, Object>> page = new Page(pageInfo.getPageNum(), pageInfo.getPageSize(), true);
        page.setReasonable(true);
        page.setTotal(count);
        pageSql = pageSql(page);
        List<Map<String, Object>> result;
        if (page.getStartRow() == 0) {
            result = jdbcTemplate.queryForList(pageSql, page.getPageSize());
        } else {
            result = jdbcTemplate.queryForList(pageSql, page.getStartRow(), page.getPageSize());
        }
        page.addAll(result);
        return page;
    }
}

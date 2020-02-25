package net.shop.util;

import java.util.List;

/**
 * Класс поискового запроса. Составного.
 */
public class SearchQuery {

    private StringBuilder sql;
    private List<Object> params;

    public SearchQuery() {
        super();
    }

    public SearchQuery(StringBuilder sql, List<Object> params) {
        super();
        this.sql = sql;
        this.params = params;
    }

    public StringBuilder getSql() {
        return sql;
    }

    public void setSql(StringBuilder sql) {
        this.sql = sql;
    }

    public List<Object> getParams() {
        return params;
    }

    public void setParams(List<Object> param) {
        this.params = param;
    }
}

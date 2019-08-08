package com.ddbuy.utils;

import java.io.Serializable;

public class PageUtils implements Serializable {
    private Integer page=1;  //页码
    private Integer rows=3;  //页大小

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getPage() {
        return page;
    }

    public Integer getRows() {
        return rows;
    }
}

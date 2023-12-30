package com.techimage.projectjfx.controller.pagination;

public class Page {
    private Integer limit;
    private Integer offset;
    public Page (Integer limit, Integer offset) {
        this.limit = limit;
        this.offset = offset;
    }
}

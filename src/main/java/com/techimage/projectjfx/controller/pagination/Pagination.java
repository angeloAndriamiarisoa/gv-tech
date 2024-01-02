package com.techimage.projectjfx.controller.pagination;

public class Pagination {

    private static final  Integer LIMIT = 15;

    public static void setPaginationFactory(javafx.scene.control.Pagination pagination,
                                            Integer totalItems) {
        if(totalItems > 0) {
            Integer totalPage = Math.ceilDiv(totalItems, LIMIT);
            if(totalPage <= 1) {
                pagination.setVisible(false);
                return;
            }
            pagination.setMaxPageIndicatorCount(5);
            pagination.setPageCount(totalPage);
            pagination.setCurrentPageIndex(0);
            return;
        }
        pagination.setVisible(false);

    }
    public Integer getLIMIT() {
        return LIMIT;
    }
}

package com.techimage.projectjfx.controller.pagination;

public class Pagination {

    public static void setPaginationFactory (javafx.scene.control.Pagination pagination,
                                             Integer totalItems,
                                             Integer perPage) {
        if(totalItems > 0) {
            Integer totalPage = Math.ceilDiv(totalItems, perPage);
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
}

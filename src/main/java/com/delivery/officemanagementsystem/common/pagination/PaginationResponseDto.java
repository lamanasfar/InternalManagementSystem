package com.delivery.officemanagementsystem.common.pagination;

import java.util.List;


public record PaginationResponseDto <T> (
        List<T> content,
        int page,
        int size,
        long totalElements,
        int totalPages,
        boolean first,
        boolean last
) {
}
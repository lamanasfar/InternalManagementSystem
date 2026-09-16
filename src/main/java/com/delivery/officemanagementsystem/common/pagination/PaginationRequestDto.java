package com.delivery.officemanagementsystem.common.pagination;

public record PaginationRequestDto(
        int page,
        int size,
        String search
) {
    public static final int DEFAULT_SIZE = 4;
    public PaginationRequestDto {
        if (page < 0) {
            page = 0;
        }

        if (search != null) {
            search = search.trim();
        }
    }
}



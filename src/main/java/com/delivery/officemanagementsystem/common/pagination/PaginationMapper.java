package com.delivery.officemanagementsystem.common.pagination;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class PaginationMapper {

    public <T, R> PaginationResponseDto<R> toResponse(
            Page<T> page,
            Function<T, R> mapper
    ) {

        return new PaginationResponseDto<>(
                page.getContent()
                        .stream()
                        .map(mapper)
                        .toList(),

                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.isFirst(),
                page.isLast()
        );
    }
}
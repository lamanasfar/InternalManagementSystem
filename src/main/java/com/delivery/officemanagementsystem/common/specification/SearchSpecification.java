package com.delivery.officemanagementsystem.common.specification;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class SearchSpecification {

    public static <T> Specification<T> search(
            String search,
            String... fields
    ) {

        return (root, query, criteriaBuilder) -> {

            if (search == null || search.isBlank()) {
                return criteriaBuilder.conjunction();
            }

            String value = "%" + search.trim().toLowerCase() + "%";

            List<Predicate> predicates = new ArrayList<>();

            for (String field : fields) {

                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(
                                        root.get(field).as(String.class)
                                ),
                                value
                        )
                );
            }

            return criteriaBuilder.or(
                    predicates.toArray(new Predicate[0])
            );
        };
    }
}

package com.example.hybridbooksbackend.model;

import com.example.hybridbooksbackend.util.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BookSpecificationBuilder {

    private final List<SearchCriteria> params;

    public BookSpecificationBuilder() {
        params = new ArrayList<SearchCriteria>();
    }

    public BookSpecificationBuilder with(String key, String operation, Object value) {
        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public Specification<Book> build() {
        if (params.size() == 0) {
            return null;
        }

        List<Specification> specs = params.stream()
                .map(BookSpecification::new)
                .collect(Collectors.toList());

        Specification result = specs.get(0);

        for (int i = 1; i < params.size(); i++) {
            result = null;
        }
        return result;
    }
}

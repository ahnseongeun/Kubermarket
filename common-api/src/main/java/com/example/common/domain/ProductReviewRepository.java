package com.example.common.domain;


import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductReviewRepository extends CrudRepository<ProductReview,Long> {
    List<ProductReview> findAllById(Long productId);
}

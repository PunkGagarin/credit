package com.gagarin.credit.service;

import com.gagarin.credit.model.ProductEntity;
import com.gagarin.credit.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.function.Predicate;

@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public ProductEntity findBySum(Double sum) {
        if (sum == null)
            return null;

        return productRepository.findOne(Specification.where(
                sumBetweenSpec((int) Math.round(sum))
        )).orElse(null);
    }

    public static Specification<ProductEntity> sumBetweenSpec(int sum) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.and(criteriaBuilder.greaterThanOrEqualTo(root.get("maxSum"), sum),
                        criteriaBuilder.lessThanOrEqualTo(root.get("minSum"), sum)));
    }
}
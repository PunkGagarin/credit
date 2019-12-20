package com.sberbank.credit.service.product;

import com.sberbank.credit.model.entity.ProductEntity;
import com.sberbank.credit.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service("productService")
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public ProductEntity findBySum(Double sum) {
        if (sum == null) {
            return null;
        }
        return productRepository.findOne(Specification.where(
                sumBetweenSpec((int) Math.round(sum))
        )).orElse(null);
    }

    public static Specification<ProductEntity> sumBetweenSpec(int sum) {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.and(criteriaBuilder.greaterThanOrEqualTo(root.get("maxSum"), sum),
                        criteriaBuilder.lessThanOrEqualTo(root.get("minSum"), sum)));
    }

    @PostConstruct
    public void init() {
        List<ProductEntity> products = Stream.of(
                new ProductEntity(3000, 30000, 5, 24, 6),
                new ProductEntity(30001, 100000, 5, 24, 10),
                new ProductEntity(100001, 300000, 5, 24, 12)
        ).collect(Collectors.toList());

        productRepository.saveAll(products);
    }
}

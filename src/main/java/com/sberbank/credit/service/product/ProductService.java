package com.sberbank.credit.service.product;

import com.sberbank.credit.model.entities.ProductEntity;

public interface ProductService {

    ProductEntity findBySum(Double sum);
}

package com.sberbank.credit.service;

import com.sberbank.credit.model.ProductEntity;

public interface ProductService {

    ProductEntity findBySum(Double sum);
}

package com.gagarin.credit.service;

import com.gagarin.credit.model.ProductEntity;

public interface ProductService {

    ProductEntity findBySum(Double sum);
}

package com.jdbc.example.service;

import com.jdbc.example.domain.Product;

import java.util.Collection;

public interface IProductService {

    Collection<Product> getAllProducts();
}

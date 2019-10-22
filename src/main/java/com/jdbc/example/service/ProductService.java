package com.jdbc.example.service;

import com.jdbc.example.domain.Product;
import com.jdbc.example.repository.IProductRepository;

import java.util.Collection;

public class ProductService implements IProductService {

    private final IProductRepository repository;

    public ProductService(IProductRepository repository) {
        this.repository = repository;
    }


    @Override
    public Collection<Product> getAllProducts() {
        return repository.findProducts();
    }
}

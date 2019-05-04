package com.jdbc.example.repository;

import com.jdbc.example.domain.Product;

import java.util.Collection;

public interface IProductRepository {

    Collection<Product> findProducts();
}

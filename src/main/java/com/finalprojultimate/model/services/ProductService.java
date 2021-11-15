package com.finalprojultimate.model.services;

import com.finalprojultimate.model.entity.product.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();
    void create(Product product);
    Product getById(int id);
    void update(Product product);
    void delete(Product product);
}

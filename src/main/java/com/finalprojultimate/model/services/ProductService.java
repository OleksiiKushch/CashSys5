package com.finalprojultimate.model.services;

import com.finalprojultimate.model.entity.product.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();
    void create(Product product);
    Product getById(int id);
    void update(Product product);
    void delete(Product product);
    List<Product> getForPagination(int offset, int limit);
    int getCount();
    List<Product> findProductsByBarcode(String pattern);
    List<Product> findProductsByName(String pattern);
}

package com.finalprojultimate.model.services;

import com.finalprojultimate.model.entity.product.Product;
import com.finalprojultimate.model.services.util.ReportBestProductByCountReceipt;

import java.util.List;
import java.util.Set;

public interface ProductService {
    List<Product> getAll();
    void create(Product product);
    Product getById(int id);
    void update(Product product);
    void delete(Product product);
    List<ReportBestProductByCountReceipt> getBestProductsByCountReceiptForTheLastMonth(int limit);
    List<Product> getProductsByIds(Set<Integer> ids);
    List<Product> getForPagination(int offset, int limit);
    List<Product> getForPaginationSortByParameter(String sortParameter, int offset, int limit);
    int getCount();
    List<Product> findProductsByBarcode(String pattern);
    List<Product> findProductsByName(String pattern);
}

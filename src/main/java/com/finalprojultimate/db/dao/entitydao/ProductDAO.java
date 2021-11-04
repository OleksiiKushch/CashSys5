package com.finalprojultimate.db.dao.entitydao;

import com.finalprojultimate.db.entity.Product;

import java.util.List;

public interface ProductDAO extends EntityDAO<Product> {
    List<Product> findProductsByName(String pattern);
    List<Product> findProductsByBarcode(String pattern);
}

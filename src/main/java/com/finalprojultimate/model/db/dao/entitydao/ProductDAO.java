package com.finalprojultimate.model.db.dao.entitydao;

import com.finalprojultimate.model.db.dao.exception.DaoException;
import com.finalprojultimate.model.entity.product.Product;

import java.util.List;

public interface ProductDAO extends EntityDAO<Product> {
    List<Product> findProductsByName(String pattern) throws DaoException;
    List<Product> findProductsByBarcode(String pattern) throws DaoException;
}

package com.finalprojultimate.model.db.dao.entitydao;

import com.finalprojultimate.model.db.dao.exception.DaoException;
import com.finalprojultimate.model.entity.product.Product;
import com.finalprojultimate.model.service.util.ReportBestProductByCountReceipt;

import java.util.List;
import java.util.Set;

public interface ProductDAO extends EntityDAO<Product> {
    List<Product> findProductsByName(String pattern) throws DaoException;
    List<Product> findProductsByBarcode(String pattern) throws DaoException;
    List<ReportBestProductByCountReceipt> findBestProductsByCountReceiptForTheLastMonth(int limit) throws DaoException;
    List<Product> findProductsByIds(Set<Integer> ids);
    List<Product> findProductsWithPaginationSortByNone(int offset, int limit) throws DaoException;
    List<Product> findProductsWithPaginationSortByName(int offset, int limit) throws DaoException;
    int getCountOfProducts() throws DaoException;
}

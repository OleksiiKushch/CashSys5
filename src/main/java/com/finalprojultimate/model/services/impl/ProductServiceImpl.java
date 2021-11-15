package com.finalprojultimate.model.services.impl;

import com.finalprojultimate.model.db.dao.DAOFactory;
import com.finalprojultimate.model.db.dao.connection.PoolConnectionBuilder;
import com.finalprojultimate.model.db.dao.entitydao.ProductDAO;
import com.finalprojultimate.model.db.dao.util.DAOConstants;
import com.finalprojultimate.model.entity.product.Product;
import com.finalprojultimate.model.services.ProductService;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    private static final Logger logger = Logger.getLogger(ProductServiceImpl.class);

    private final ProductDAO productDAO;

    private static class Holder {
        static final ProductServiceImpl INSTANCE = new ProductServiceImpl();
    }

    public static ProductServiceImpl getInstance() {
        return Holder.INSTANCE;
    }

    public ProductServiceImpl() {
        DAOFactory.setDaoFactoryFCN(DAOConstants.MySqlDAOFactoryFCN);
        DAOFactory daoFactory = null;
        try {
            daoFactory = DAOFactory.getInstance();
        } catch (NoSuchMethodException | ClassNotFoundException | InvocationTargetException |
                InstantiationException | IllegalAccessException e) {
            logger.error(e.getMessage(), e);
        }
        assert daoFactory != null;
        productDAO = daoFactory.getProductDAO();
        productDAO.setConnectionBuilder(PoolConnectionBuilder.getInstance());
    }

    @Override
    public List<Product> getAll() {
        return productDAO.getAll();
    }

    @Override
    public void create(Product product) {
        productDAO.insert(product);
    }

    @Override
    public Product getById(int id) {
        return productDAO.getById(id);
    }

    @Override
    public void update(Product product) {
        productDAO.update(product);
    }

    @Override
    public void delete(Product product) {
        productDAO.delete(product);
    }
}

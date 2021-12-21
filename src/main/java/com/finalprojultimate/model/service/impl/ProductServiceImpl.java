package com.finalprojultimate.model.service.impl;

import com.finalprojultimate.model.db.dao.DAOFactory;
import com.finalprojultimate.model.db.dao.connection.PoolConnectionBuilder;
import com.finalprojultimate.model.db.dao.entitydao.ProductDAO;
import com.finalprojultimate.model.db.dao.util.DAOConstants;
import com.finalprojultimate.model.entity.product.Product;
import com.finalprojultimate.model.entity.receipt.Receipt;
import com.finalprojultimate.model.service.ProductService;
import com.finalprojultimate.model.service.ReceiptService;
import com.finalprojultimate.model.service.exception.ServiceException;
import com.finalprojultimate.model.service.util.ReportBestProductByCountReceipt;
import com.finalprojultimate.util.MessageKey;
import org.apache.log4j.Logger;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Set;

import static com.finalprojultimate.util.Parameter.*;

public class ProductServiceImpl implements ProductService {
    private static final Logger logger = Logger.getLogger(ProductServiceImpl.class);

    private static final String LOGGER_PRODUCT_WITH_THIS_BARCODE_ALREADY_EXISTS =
            "Creating product failed : product with this barcode already exists in the system" +
                    " - BARCODE : %s";
    private static final String LOGGER_PRODUCT_CONTAINED_IN_RECEIPTS =
            "Deleting product failed : this product is contained in some receipts in the system" +
                    " - PRODUCT ID : %d";

    private final ProductDAO productDAO;

    private static class Holder {
        static final ProductServiceImpl INSTANCE = new ProductServiceImpl();
    }

    public static ProductServiceImpl getInstance() {
        return Holder.INSTANCE;
    }

    public ProductServiceImpl() {
        DAOFactory.setDaoFactoryFCN(DAOConstants.MY_SQL_DAO_FACTORY_FCN);
        DAOFactory daoFactory = null;
        try {
            daoFactory = DAOFactory.getInstance();
        } catch (NoSuchMethodException | ClassNotFoundException | InvocationTargetException |
                InstantiationException | IllegalAccessException e) {
            logger.error(e.getMessage(), e);
        }
        productDAO = daoFactory.getProductDAO();
        productDAO.setConnectionBuilder(PoolConnectionBuilder.getInstance());
    }

    @Override
    public List<Product> getAll() {
        return productDAO.getAll();
    }

    @Override
    public void create(Product product) {
        if (productDAO.findProductsByBarcode(product.getBarcode()).isEmpty()) {
            productDAO.insert(product);
        } else {
            throw new ServiceException()
                    .addMessage(MessageKey.ERROR_PRODUCT_ALREADY_EXISTS)
                    .addLogMessage(String.format(LOGGER_PRODUCT_WITH_THIS_BARCODE_ALREADY_EXISTS, product.getBarcode()))
                    .setClassThrowsException(ProductServiceImpl.class);
        }
    }

    @Override
    public Product getById(int id) {
        return productDAO.getById(id);
    }

    @Override
    public void update(Product product) {
        String oldBarcode = productDAO.getById(product.getId()).getBarcode();
        if (oldBarcode.equals(product.getBarcode()) ||
                productDAO.findProductsByBarcode(product.getBarcode()).isEmpty()) {
            productDAO.update(product);
        } else {
            throw new ServiceException()
                    .addMessage(MessageKey.ERROR_BARCODE_ALREADY_TAKEN)
                    .addLogMessage(String.format(LOGGER_PRODUCT_WITH_THIS_BARCODE_ALREADY_EXISTS, product.getBarcode()))
                    .setClassThrowsException(ProductServiceImpl.class);
        }
    }

    @Override
    public void delete(Product product) {
        ReceiptService receiptService = ReceiptServiceImpl.getInstance();
        if (receiptService.findReceiptsContainProduct(product).size() == 0) {
            productDAO.delete(product);
        } else {
            throw new ServiceException()
                    .addMessage(MessageKey.ERROR_PRODUCT_CONTAINED_IN_RECEIPTS)
                    .addLogMessage(String.format(LOGGER_PRODUCT_CONTAINED_IN_RECEIPTS, product.getId()))
                    .setClassThrowsException(ProductServiceImpl.class);
        }
    }

    @Override
    public List<ReportBestProductByCountReceipt> getBestProductsByCountReceiptForTheLastMonth(int limit) {
        return productDAO.findBestProductsByCountReceiptForTheLastMonth(limit);
    }

    @Override
    public List<Product> getProductsByIds(Set<Integer> ids) {
        return productDAO.findProductsByIds(ids);
    }

    @Override
    public List<Product> getForPagination(int offset, int limit) {
        return productDAO.findProductsWithPaginationSortByNone(offset, limit);
    }

    /**
     * return products from the database in the range [(offset - 1) * limit; offset * limit] pre-sorted by parameter or not (none)
     *
     * @param sortParameter parameter sorting [none, productName]
     * @param offset number of page at pagination
     * @param limit number of return products (size List)
     * @return list of products
     */
    @Override
    public List<Product> getForPaginationSortByParameter(String sortParameter, int offset, int limit) {
        if (sortParameter.equals(PRODUCT_NAME)) {
            return productDAO.findProductsWithPaginationSortByName(offset, limit);
        } else {
            return getForPagination(offset, limit);
        }
    }

    @Override
    public int getCount() {
        return productDAO.getCountOfProducts();
    }

    @Override
    public List<Product> findProductsByBarcode(String pattern) {
        return productDAO.findProductsByBarcode(pattern);
    }

    @Override
    public List<Product> findProductsByName(String pattern) {
        return productDAO.findProductsByName(pattern);
    }
}

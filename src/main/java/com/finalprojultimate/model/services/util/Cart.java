package com.finalprojultimate.model.services.util;

import com.finalprojultimate.model.entity.product.Product;
import com.finalprojultimate.model.entity.product.Unit;
import org.apache.log4j.Logger;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Cart implements Serializable {
    private static final long serialVersionUID = 139389168791006342L;
    private static final Logger logger = Logger.getLogger(Cart.class);

    private LinkedHashMap<Integer, Product> container;

    public Cart() {
        container = new LinkedHashMap<>();
    }

    public LinkedHashMap<Integer, Product> getContainer() {
        return container;
    }

    public void setContainer(LinkedHashMap<Integer, Product> container) {
        this.container = container;
    }

    public void put(Integer id, Product product) {
        if (container.containsKey(id)) {
            Product oldProduct = container.get(id);
            oldProduct.setAmount(oldProduct.getAmount().add(product.getAmount()));
            container.put(id, oldProduct);
        } else {
            container.put(id, product);
        }
    }

    public void remove(Integer id) {
        container.remove(id);
    }

    public BigDecimal getSum() {
        // TODO implements with stream
        BigDecimal result = new BigDecimal("0");
        Collection<Product> products = container.values();
        for (Product product : products) {
            result = result.add(product.getPrice().multiply(product.getAmount()));
        }
        return result;
    }

    public void updateAmount(Integer id, BigDecimal amount) {
        Product oldProduct = container.get(id);
        oldProduct.setAmount(amount);
        container.put(id, oldProduct);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "container=" + container +
                '}';
    }

}

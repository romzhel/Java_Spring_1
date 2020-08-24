package com.geekbrains.repositories;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;

@Component
public class ProductsRepository {
    private HashMap<Integer, Product> productsList;

    public ProductsRepository() {
        productsList = new HashMap<>();
        productsList.put(1, new Product(1, "Ноутбук HP", 35000));
        productsList.put(2, new Product(2, "Ноутбук LENOVO", 25000));
        productsList.put(3, new Product(3, "Ноутбук DELL", 30000));
        productsList.put(4, new Product(4, "Ноутбук FUJITSU", 40000));
        productsList.put(5, new Product(5, "Ноутбук APPLE", 50000));
    }

    public Collection<Product> getAllProducts() {
        return productsList.values();
    }

    public Product getProductById(int id) {
        return productsList.get(id);
    }

    public void addProduct(Product product) {
        product.setId(productsList.keySet().stream().max(Integer::compareTo).get() + 1);
        productsList.put(product.getId(), product);
    }
}

package com.geekbrains.service;

import com.geekbrains.repositories.Product;
import com.geekbrains.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ProductsService {
    private ProductsRepository productsRepository;

    @Autowired
    public void setProductsRepository(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public Product getProductById(int id) {
        return productsRepository.getProductById(id);
    }

    public void addProduct(Product product) {
        productsRepository.addProduct(product);
    }

    public Collection<Product> getAllProducts() {
        return productsRepository.getAllProducts();
    }
}

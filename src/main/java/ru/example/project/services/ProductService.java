package ru.example.project.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.example.project.entities.Product;

public interface ProductService {
    Page<Product> getWithLowerCost(int cost, Pageable pageable);

    Page<Product> getWithGreaterCost(int cost, Pageable pageable);

    Page<Product> getWithIncludedCost(int minCost, int maxCost, Pageable pageable);
}

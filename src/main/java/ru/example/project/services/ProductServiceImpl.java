package ru.example.project.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.example.project.entities.Product;
import ru.example.project.repositories.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Page<Product> getWithLowerCost(int cost, Pageable pageable) {
        return getPagedProductsList(pageable, productRepository.findAllByCostLessThan(cost));
    }

    @Override
    public Page<Product> getWithGreaterCost(int cost, Pageable pageable) {
        return getPagedProductsList(pageable, productRepository.findAllByCostGreaterThan(cost));
    }

    @Override
    public Page<Product> getWithIncludedCost(int minCost, int maxCost, Pageable pageable) {
        return getPagedProductsList(pageable, productRepository.findAllByCostBetween(minCost, maxCost));
    }

    private Page<Product> getPagedProductsList(Pageable pageable, List<Product> filteredList) {
        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();

        List<Product> resultList = filteredList.stream()
                .skip(currentPage * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());

        return new PageImpl<Product>(resultList, PageRequest.of(currentPage, pageSize), filteredList.size());
    }
}

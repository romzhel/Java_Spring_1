package ru.example.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.example.project.entities.Product;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCostGreaterThan(int cost);

    List<Product> findAllByCostLessThan(int cost);

    List<Product> findAllByCostBetween(int minCost, int maxCost);
}

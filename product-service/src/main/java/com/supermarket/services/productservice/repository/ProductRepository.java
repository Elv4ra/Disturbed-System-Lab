package com.supermarket.services.productservice.repository;

import com.supermarket.services.productservice.repository.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByCategory(String category);

    List<Product> findAllByTrademark(String trademark);
}

package com.supermarket.services.productservice.service;

import com.supermarket.services.productservice.repository.ProductRepository;
import com.supermarket.services.productservice.repository.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductService {

    private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(long id) throws IllegalArgumentException {
        final Optional<Product> maybeProduct = productRepository.findById(id);
        if (maybeProduct.isEmpty()) throw new IllegalArgumentException("Product not found");
        else return maybeProduct.get();
    }

    public long createProduct(String productName, Double price, String category, String size, Long amount, String trademark) {
        if (!category.equals("Bakery")
                && !category.equals("Fruit & Vegetables")
                && !category.equals("Frozen Food")
                && !category.equals("Dairy Products")
                && !category.equals("Meat")
                && !category.equals("Alcohol")) throw  new IllegalArgumentException("Illegal category argumnet");
        final Product product = new Product(productName, price, category, size, amount, trademark);
        final Product savedProduct = productRepository.save(product);
        return savedProduct.getId();
    }

    public void updateProduct(long id, String productName, Double price, String category, String size, Long amount, String trademark) throws IllegalArgumentException {
        final Optional<Product> maybeProduct = productRepository.findById(id);
        if (maybeProduct.isEmpty()) throw new IllegalArgumentException("Product not found");
        Product product = maybeProduct.get();
        if (productName != null && !productName.isBlank()) product.setProductName(productName);
        if (price != null && !price.isNaN() && !price.isInfinite()) product.setPrice(price);
        if (category != null && !category.isBlank() &&(category.equals("Bakery")
                || category.equals("Fruit & Vegetables")
                || category.equals("Frozen Food")
                || category.equals("Dairy Products")
                || category.equals("Meat")
                || category.equals("Alcohol"))) product.setCategory(category);
        if (size != null && !size.isBlank()) product.setSize(size);
        if (amount != null && amount>0) product.setAmount(amount);
        if (trademark != null && !trademark.isBlank()) product.setTrademark(trademark);
        productRepository.save(product);
    }

    public void deleteProduct(long id)     {
        productRepository.deleteById(id);
    }

    public List<Product> getAllProductsByCategory(String category) {
        return productRepository.findAllByCategory(category);
    }

    public List<Product> getAllProductsByTrademark(String trademark) {
        return productRepository.findAllByTrademark(trademark);
    }
}

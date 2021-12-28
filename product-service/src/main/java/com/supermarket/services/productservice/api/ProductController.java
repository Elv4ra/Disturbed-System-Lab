package com.supermarket.services.productservice.api;

import com.supermarket.services.productservice.repository.model.Product;
import com.supermarket.services.productservice.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/products")
public final class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<List<Product>> findAll() {
        final List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> findById(@PathVariable long id) {
        try {
            final Product product = productService.getProductById(id);
            return ResponseEntity.ok(product);
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Product>> findByCategory(@PathVariable String category) {
        List<Product> products = productService.getAllProductsByCategory(category);
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }

    @GetMapping("/tradeMark/{trademark}")
    public ResponseEntity<List<Product>> findByTradeMark(@PathVariable String trademark) {
        List<Product> products = productService.getAllProductsByTrademark(trademark);
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody com.supermarket.services.productservice.api.dto.Product product) {
        final String productName = product.getProductName();
        final Double price = product.getPrice();
        final String category = product.getCategory();
        final String size = product.getSize();
        final Long amount = product.getAmount();
        final String trademark = product.getTrademark();
        try {
            final long id = productService.createProduct(productName, price, category, size, amount, trademark);
            final String location = String.format("/products/%d", id);
            return ResponseEntity.created(URI.create(location)).build();
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable long id, @RequestBody com.supermarket.services.productservice.api.dto.Product product) {
        final String productName = product.getProductName();
        final Double price = product.getPrice();
        final String category = product.getCategory();
        final String size = product.getSize();
        final Long amount = product.getAmount();
        final String trademark = product.getTrademark();
        try {
            productService.updateProduct(id, productName, price, category, size, amount, trademark);
            return ResponseEntity.noContent().build();
        }
        catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}

package com.supermarket.services.productservice.repository.model;

import javax.persistence.*;

@Entity
@Table(name = "products")
public final class Product {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long id;
    @Column(name = "product_name")
    private String productName;
    private Double price;
    private String category;
    private String size;
    private Long amount;
    private String trademark;

    public Product() {
    }

    public Product(String productName, Double price, String category, String size, Long amount, String trademark) {
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.size = size;
        this.amount = amount;
        this.trademark = trademark;
    }

    public long getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getTrademark() {
        return trademark;
    }

    public void setTrademark(String trademark) {
        this.trademark = trademark;
    }
}

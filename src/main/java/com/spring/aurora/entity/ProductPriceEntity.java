package com.spring.aurora.entity;

import com.spring.aurora.model.Customer;
import com.spring.aurora.model.Product;

public class ProductPriceEntity {
    private String priceId;
    private Customer customer;
    private Product product;
    private Double sellingPrice;

    public ProductPriceEntity(String priceId, Customer customer, Product product, Double sellingPrice) {
        this.priceId = priceId;
        this.customer = customer;
        this.product = product;
        this.sellingPrice = sellingPrice;
    }

    public String getPriceId() {
        return priceId;
    }

    public void setPriceId(String priceId) {
        this.priceId = priceId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(Double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }
}

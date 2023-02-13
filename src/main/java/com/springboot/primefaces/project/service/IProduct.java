package com.springboot.primefaces.project.service;

import com.springboot.primefaces.project.entity.Product;

import java.util.List;

public interface IProduct {

    List<Product> getAllProduct();

    Product getProductById(Long id);

    void saveProduct(Product product);

    void saveAllProduct(List<Product> products);
    void updateProduct(Product product);

    void deleteProduct(Long id);
}

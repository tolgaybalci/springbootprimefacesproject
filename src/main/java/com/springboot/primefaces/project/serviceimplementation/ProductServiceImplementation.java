package com.springboot.primefaces.project.serviceimplementation;

import com.springboot.primefaces.project.dao.ProductDao;
import com.springboot.primefaces.project.entity.Product;
import com.springboot.primefaces.project.service.IProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImplementation implements IProduct {

    @Autowired
    private ProductDao productDao;
    @Override
    public List<Product> getAllProduct() {
        return productDao.getAllProduct();
    }

    @Override
    public Product getProductById(Long id) {
        return productDao.getProductById(id);
    }

    @Override
    public void saveProduct(Product product) {
        productDao.saveProduct(product);
    }

    @Override
    public void saveAllProduct(List<Product> products) {
        productDao.saveAllProducts(products);
    }

    @Override
    public void updateProduct(Product product) {
        productDao.updateProduct(product);
    }

    @Override
    public void deleteProduct(Long id) {
        productDao.deleteProduct(id);
    }
}

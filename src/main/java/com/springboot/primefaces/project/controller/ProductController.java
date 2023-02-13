package com.springboot.primefaces.project.controller;

import com.springboot.primefaces.project.entity.Product;
import com.springboot.primefaces.project.serviceimplementation.ProductServiceImplementation;
import org.primefaces.PrimeFaces;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@ViewScoped
@RestController
@RequestMapping("/products")
public class ProductController implements Serializable {

    private Product product = new Product();

    private List<Product> products = new ArrayList<Product>();

    private Product selectedProduct;
    private List<Product> selectedProducts;
    @Autowired
    private ProductServiceImplementation productServiceImplementation;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Product getSelectedProduct() {
        return selectedProduct;
    }

    public void setSelectedProduct(Product selectedProduct) {
        this.selectedProduct = selectedProduct;
    }

    public List<Product> getSelectedProducts() {
        return selectedProducts;
    }

    public void setSelectedProducts(List<Product> selectedProducts) {
        this.selectedProducts = selectedProducts;
    }

    @GetMapping
    public List<Product> getAllProduct(){ return productServiceImplementation.getAllProduct(); }

    @PostMapping
    public ResponseEntity<Product> saveProduct(@Validated @RequestBody Product product){
        productServiceImplementation.saveProduct(product);
        return new ResponseEntity(null, HttpStatus.CREATED);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable("productId") Long id){
        Product product = productServiceImplementation.getProductById(id);
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Product> updateProduct(@Validated @RequestBody Product product){
        productServiceImplementation.updateProduct(product);
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @DeleteMapping("/product/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("productId") Long id){
        productServiceImplementation.deleteProduct(id);
        return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
    }
    public void delete(){
        this.products.remove(this.product);
        this.product = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Ürün Silindi"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }

    public void save(){
        this.productServiceImplementation.saveProduct(product);
        product = new Product();
        products = productServiceImplementation.getAllProduct();
    }

    public void edit(Product product) {
        this.product = product;
    }

}

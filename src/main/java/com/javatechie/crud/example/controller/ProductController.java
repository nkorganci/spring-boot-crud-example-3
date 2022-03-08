package com.javatechie.crud.example.controller;

import com.javatechie.crud.example.entity.Product;
import com.javatechie.crud.example.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
import java.util.List;

@RestController
//@RequestMapping("/myProducts")
public class ProductController {
    @Autowired
    private ProductService productService;

    // 1 POST a product
    @PostMapping("/addProduct")
    public Product addProduct(@RequestBody Product product){
        return productService.saveProduct(product);
    }

    // 2 POST products
    @PostMapping("/addProducts")
    public List<Product> addProducts(@RequestBody List<Product> products){
        return productService.saveProducts(products);
    }

    // 3 GET products
    @GetMapping("/getProducts")
    public List<Product> findAllProducts(){
        return productService.getProducts();
    }

    // 4 GET a product
    @GetMapping("/getProduct/{id}")
    public Product findProductById(@PathVariable Integer id){
        return productService.getProductById(id);
    }

    // 5 PUTMAPPING
    @PutMapping("/update")
    public Product updateProduct(@RequestBody Product product){
        return productService.updateProduct(product);
    }

    // 6 Deletemapping
    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id){
        return productService.deleteProduct(id);
    }


}

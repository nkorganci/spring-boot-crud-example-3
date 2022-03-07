package com.javatechie.crud.example.service;

import com.javatechie.crud.example.entity.Product;
import com.javatechie.crud.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.net.PortUnreachableException;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    // 1 POST a product
    public Product saveProduct(Product product){
        return productRepository.save(product);
    }

    // 2 POST many products
    public List<Product> saveProducts(List<Product> products){
        return productRepository.saveAll(products);
    }

    // 3 GET a product
    public Product getProductById(Integer id){
        return productRepository.findById(id).orElse(null);
    }

    // 4 GET products
    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    // 5 GET by name which has no build in method for NAME
    public Product getByName(String name){
        return productRepository.findByName(name);
    }

    // 6 DELETE a product
    public String deleteProduct(Integer id){
        productRepository.deleteById(id);
        return "product " + id + " deleted";
    }

    // 7 UPDATE a product, no update method in JPA
    public Product updateProduct(Product product){
        Product existingProduct = productRepository.findById(product.getId()).orElse(null);
        existingProduct.setPrice(product.getPrice());
        existingProduct.setName(product.getName());
        existingProduct.setQuantity(product.getQuantity());
        return productRepository.save(existingProduct);
    }



}

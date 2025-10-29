package com.example.demo_test_mongodb.controller;

import com.example.demo_test_mongodb.entity.Product;
import com.example.demo_test_mongodb.service.ProductService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@AllArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<Product> getAllProducts(){

        return productService.getAllProducts();
    }

    @PostMapping
    public ResponseEntity<Product> saveProduct(@RequestBody Product product){
        Product saved = productService.saveProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);

    }

    @GetMapping("{id}")
    public Product getProductById(@PathVariable String id){
        return productService.findProductById(id);
    }




}

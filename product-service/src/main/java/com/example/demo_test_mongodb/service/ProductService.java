package com.example.demo_test_mongodb.service;

import com.example.demo_test_mongodb.entity.Product;
import com.example.demo_test_mongodb.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor  // required for mockito
public class ProductService {
    private final ProductRepository productRepository;

    @CachePut(value = "products", key = "#product.id")
    @CacheEvict(value = "allProducts", allEntries = true)
    public Product saveProduct(Product product){
        Product saved = productRepository.save(product);
        log.info("{} has been saved with id {}", saved.getName(), saved.getId());
        return saved;
    }

    @Cacheable("allProducts")
    public List<Product> getAllProducts(){

        return productRepository.findAll();
    }

    @Cacheable(value = "products", key = "#id")
    public Product findProductById(String id){
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product with id " + id + " not found"));
    }
}


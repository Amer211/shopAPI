package com.example.demo_test_mongodb.controller;

import com.example.demo_test_mongodb.entity.Product;
import com.example.demo_test_mongodb.repository.ProductRepository;
import com.example.demo_test_mongodb.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@Slf4j
@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductControllerTest {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductService productService;


    @Autowired
    private MockMvc mockMvc;



    @Container
    static MongoDBContainer mongoDBContainer= new MongoDBContainer("mongo:5.0.15");

    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry registry){
        registry.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }
    @AfterEach
    void tearDown(){
        productRepository.deleteAll();
    }





    @Test
    @DisplayName("GET /api/products should return all products")
    void getAllProducts() throws Exception{
        productRepository.save(new Product(null, "iPhone 14", 999.99));
        productRepository.save(new Product(null, "Galaxy S24", 899.99));

        mockMvc.perform(get("/api/products")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2));
    }


    @Test
    @DisplayName("POST /api/products should save product")
    void shouldSaveProduct() throws Exception {
        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Test Product\",\"price\":99.99}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").isNotEmpty())
                .andExpect(jsonPath("$.name").value("Test Product"))
                .andExpect(jsonPath("$.price").value(99.99));
    }



    @Test
    @DisplayName("GET /api/products should find product by id")
    void getProductById() throws Exception{
        Product saved = productRepository.save(new Product(null, "iPhone 14", 999.99));

        log.info("{} was saved with id {}", saved.getName(),saved.getId());

        mockMvc.perform(get("/api/products/"+saved.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("iPhone 14"))
                .andExpect(jsonPath("$.price").value(999.99));




    }
    @Test
    @DisplayName("Redis cache test for getProductById")
    void redisCacheTest() throws Exception {
        Product product = new Product(null, "Test Product", 99.99);
        productRepository.save(product);  //ca cache it

        Product cached = productService.findProductById(product.getId()); //from db to cache


    }









}
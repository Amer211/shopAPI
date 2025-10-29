package com.example.demo_test_mongodb.service;

import com.example.demo_test_mongodb.entity.Product;
import com.example.demo_test_mongodb.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatReflectiveOperationException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product product;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
        product= Product.builder()
                .name("Test Product")
                .id("123")
                .price(99.99)
                .build();
    }


    @Test
    @DisplayName("Should save Product successfully")
    void shouldSaveProduct() {
        //given
        when(productRepository.save(product)).thenReturn(product);

        //when
        Product savedProduct = productService.saveProduct(product);

        //then
        assertThat(savedProduct.getName()).isEqualTo("Test Product");
        verify(productRepository,times(1)).save(product);




    }

    @Test
    @DisplayName("Should get all products successfully")
    void getAllProducts() {
        //given
        when(productRepository.findAll()).thenReturn(java.util.List.of(product));

        //when
        var products = productService.getAllProducts();

        //then
        assertThat(products).isNotNull();
        assertThat(products.size()).isEqualTo(1);
        verify(productRepository, times(1)).findAll();


    }





    @Test
    @DisplayName("Should find product by Id")
    void findProductById() {
        //given
        when(productRepository.findById("123")).thenReturn(java.util.Optional.ofNullable(product));

        //when
        Product foundProduct = productService.findProductById("123");

        //then
        assertThat(foundProduct).isNotNull();
        assertThat(foundProduct.getId()).isEqualTo("123");
        verify(productRepository, times(1)).findById("123");





    }
}
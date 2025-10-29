package com.example.demo_test_mongodb.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serial;
import java.io.Serializable;

@Document(collection="products")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Product implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @Field("name")
    private String name;

    private Double price;
}

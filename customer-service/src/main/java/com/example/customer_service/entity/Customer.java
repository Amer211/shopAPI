package com.example.customer_service.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="customers")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false,unique = true)
    private String username;








}

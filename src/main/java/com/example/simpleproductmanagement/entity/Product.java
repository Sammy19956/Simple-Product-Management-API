package com.example.simpleproductmanagement.entity;

import com.example.simpleproductmanagement.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;


@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private  UUID productCode;
    private String productName;
    private double price;
    private String manufacturer;

    @Enumerated(EnumType.STRING)
    private Status status;
    private int quantityInStore;

    @CreationTimestamp
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}

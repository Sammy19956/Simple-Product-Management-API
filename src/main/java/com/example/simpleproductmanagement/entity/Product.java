package com.example.simpleproductmanagement.entity;

import com.example.simpleproductmanagement.enums.Status;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;


@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String productName;
    private  UUID productCode;
    private double price;
    private String manufacturer;

    @Enumerated(EnumType.STRING)
    private Status status;
    private int quantityInStore;

    @CreationTimestamp
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}

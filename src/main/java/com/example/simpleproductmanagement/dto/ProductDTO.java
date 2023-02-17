package com.example.simpleproductmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProductDTO {
    private String productName;
    private double price;
    private String manufacturer;
    private String status;
    private int quantityInStore;


}

package com.example.simpleproductmanagement.dto;

import com.example.simpleproductmanagement.enums.Status;
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
    private Status status;
    private int quantityInStore;


}

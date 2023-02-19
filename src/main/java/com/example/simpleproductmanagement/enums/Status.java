package com.example.simpleproductmanagement.enums;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public enum Status {
    AVAILABLE,
    OUT_OF_STOCK
}

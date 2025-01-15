package com.works.models;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {

    private Long pid;
    private String title;
    private String description;
    private BigDecimal price;

}

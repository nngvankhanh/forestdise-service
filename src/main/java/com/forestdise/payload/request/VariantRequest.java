package com.forestdise.payload.request;

import lombok.Getter;

@Getter
public class VariantRequest {
    private String name;
    private String skuCode;
    private int stockQuantity;
    private double weight;
    private double price;
    private double salePrice;
    private String img;
    private int product_id;
}

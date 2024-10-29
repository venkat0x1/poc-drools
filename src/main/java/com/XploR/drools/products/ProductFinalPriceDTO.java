package com.XploR.drools.products;

import lombok.Data;

@Data
public class ProductFinalPriceDTO {

    private Integer productId;
    private String creditCardType;
    private Double finalPrice;

}
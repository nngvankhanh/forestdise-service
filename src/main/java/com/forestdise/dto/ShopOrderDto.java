package com.forestdise.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShopOrderDto {
    private Long id;
    private UserDTO userDTO;
    private List<VariantDTO> variantDtoList;
    private String order_date;
    private AddressDto addressDto;
    private String message;
    private PaymentMethodDto paymentMethodDto;
    private ShippingMethodDto shippingMethodDto;
    private double orderTotal;
}

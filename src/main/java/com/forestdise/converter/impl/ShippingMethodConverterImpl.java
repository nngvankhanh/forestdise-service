package com.forestdise.converter.impl;

import com.forestdise.converter.ShippingMethodConverter;
import com.forestdise.entity.ShippingMethod;
import com.forestdise.payload.response.ShippingMethodResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class ShippingMethodConverterImpl implements ShippingMethodConverter {
    @Override
    public ShippingMethodResponse convertToDto(ShippingMethod shippingMethod) {
        return ShippingMethodResponse
                .builder()
                .id(shippingMethod.getId())
                .name(shippingMethod.getName())
                .price(shippingMethod.getPrice())
                .build();
    }
}

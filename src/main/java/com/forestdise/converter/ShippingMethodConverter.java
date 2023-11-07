package com.forestdise.converter;

import com.forestdise.entity.ShippingMethod;
import com.forestdise.payload.response.ShippingMethodResponse;

public interface ShippingMethodConverter {
    ShippingMethodResponse convertToDto(ShippingMethod shippingMethod);
}

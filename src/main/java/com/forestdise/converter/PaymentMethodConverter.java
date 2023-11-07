package com.forestdise.converter;

import com.forestdise.entity.PaymentMethod;
import com.forestdise.payload.request.PaymentMethodRequest;
import com.forestdise.payload.response.PaymentMethodResponse;


public interface PaymentMethodConverter {
    PaymentMethodResponse convertToDto(PaymentMethod paymentMethod);
    PaymentMethod convertToEntity(PaymentMethodRequest paymentMethodRequest);
}

package com.forestdise.converter.impl;

import com.forestdise.converter.PaymentMethodConverter;
import com.forestdise.entity.PaymentMethod;
import com.forestdise.payload.request.PaymentMethodRequest;
import com.forestdise.payload.response.PaymentMethodResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class PaymentMethodConverterImpl implements PaymentMethodConverter {
    @Override
    public PaymentMethodResponse convertToDto(PaymentMethod paymentMethod) {
        return PaymentMethodResponse
                .builder()
                .id(paymentMethod.getId())
                .cartNumber(paymentMethod.getCartNumber())
                .nameOnCard(paymentMethod.getNameOnCard())
                .expirationDate(paymentMethod.getExpirationDate())
                .defaultPayment(paymentMethod.getDefaultPayment())
                .build();
    }

    @Override
    public PaymentMethod convertToEntity(PaymentMethodRequest paymentMethodRequest) {
        PaymentMethod paymentMethod = new PaymentMethod();
        BeanUtils.copyProperties(paymentMethodRequest,paymentMethod);
        return paymentMethod;
    }
}

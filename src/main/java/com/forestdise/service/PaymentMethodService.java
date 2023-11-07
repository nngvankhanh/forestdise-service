package com.forestdise.service;

import com.forestdise.payload.request.PaymentMethodRequest;
import com.forestdise.payload.response.PaymentMethodResponse;

import java.util.List;

public interface PaymentMethodService {
    List<PaymentMethodResponse> findPaymentMethod(Long userId);
    PaymentMethodResponse createPaymentMethod(PaymentMethodRequest paymentMethodRequest);
    PaymentMethodResponse updatePaymentMethod(PaymentMethodRequest paymentMethodRequest);
}

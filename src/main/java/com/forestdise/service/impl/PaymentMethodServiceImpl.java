package com.forestdise.service.impl;

import com.forestdise.converter.PaymentMethodConverter;
import com.forestdise.entity.PaymentMethod;
import com.forestdise.entity.User;
import com.forestdise.payload.request.PaymentMethodRequest;
import com.forestdise.payload.response.PaymentMethodResponse;
import com.forestdise.repository.PaymentMethodRepository;
import com.forestdise.repository.UserRepository;
import com.forestdise.service.PaymentMethodService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PaymentMethodServiceImpl implements PaymentMethodService {

    @Autowired
    private final PaymentMethodRepository paymentMethodRepository;

    @Autowired
    private final PaymentMethodConverter paymentMethodConverter;

    @Autowired
    private final UserRepository userRepository;

    @Override
    public List<PaymentMethodResponse> findPaymentMethod(Long userId) {
        List<PaymentMethod> paymentMethodList = paymentMethodRepository.findByUserId(userId);
        List<PaymentMethodResponse> paymentMethodResponseList = new ArrayList<>();
        for(PaymentMethod paymentMethod : paymentMethodList){
            PaymentMethodResponse paymentMethodResponse = paymentMethodConverter.convertToDto(paymentMethod);
            paymentMethodResponseList.add(paymentMethodResponse);
        }
        return paymentMethodResponseList;
    }

    @Override
    public PaymentMethodResponse createPaymentMethod(PaymentMethodRequest paymentMethodRequest) {
        PaymentMethod paymentMethod = paymentMethodConverter.convertToEntity(paymentMethodRequest);
        User user = userRepository.findById(paymentMethodRequest.getUserId()).get();
        paymentMethod.setUser(user);
        PaymentMethod paymentMethodNew = paymentMethodRepository.save(paymentMethod);
        return paymentMethodConverter.convertToDto(paymentMethodNew);
    }

    @Override
    public PaymentMethodResponse updatePaymentMethod(PaymentMethodRequest paymentMethodRequest) {
        PaymentMethod paymentMethod = paymentMethodRepository.findById(paymentMethodRequest.getId()).get();
        paymentMethod.setCartNumber(paymentMethodRequest.getCartNumber());
        paymentMethod.setNameOnCard(paymentMethodRequest.getNameOnCard());
        paymentMethod.setExpirationDate(paymentMethodRequest.getExpirationDate());
        paymentMethod.setDefaultPayment(paymentMethodRequest.getDefaultPayment());
        PaymentMethod paymentMethodNew = paymentMethodRepository.save(paymentMethod);
        return paymentMethodConverter.convertToDto(paymentMethodNew);
    }
}

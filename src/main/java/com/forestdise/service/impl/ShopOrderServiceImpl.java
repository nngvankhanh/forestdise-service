package com.forestdise.service.impl;

import com.forestdise.converter.ShopOrderConverter;
import com.forestdise.entity.Address;
import com.forestdise.entity.PaymentMethod;
import com.forestdise.entity.ShippingMethod;
import com.forestdise.entity.ShopOrder;
import com.forestdise.entity.User;
import com.forestdise.entity.Variant;
import com.forestdise.payload.request.ShopOrderRequest;
import com.forestdise.payload.response.ShopOrderResponse;
import com.forestdise.repository.AddressRepository;
import com.forestdise.repository.PaymentMethodRepository;
import com.forestdise.repository.ShippingMethodRepository;
import com.forestdise.repository.ShopOrderRepository;
import com.forestdise.repository.UserRepository;
import com.forestdise.repository.VariantRepository;
import com.forestdise.service.EmailService;
import com.forestdise.service.ShopOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ShopOrderServiceImpl implements ShopOrderService {

    @Autowired
    private ShopOrderRepository shopOrderRepository;

    @Autowired
    private ShopOrderConverter shopOrderConverter;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VariantRepository variantRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private PaymentMethodRepository paymentMethodRepository;

    @Autowired
    private ShippingMethodRepository shippingMethodRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public ShopOrderResponse findShopOrder(Long userId) {
        ShopOrder shopOrder = shopOrderRepository.findByUserId(userId);
        return shopOrderConverter.convertToDto(shopOrder);
    }

    @Override
    public List<ShopOrderResponse> createShopOrder(List<ShopOrderRequest> shopOrderRequest) {
        List<ShopOrderResponse> shopOrderResponseList = new ArrayList<>();
        User user = new User();
        for(ShopOrderRequest shopOrder: shopOrderRequest){
            ShopOrder shopOrderOld = shopOrderConverter.convertToEntity(shopOrder);
            User takeUser = userRepository.findById(shopOrder.getUserId()).get();
            user = takeUser;
            Variant variant = variantRepository.findById(shopOrder.getVariantId()).get();
            Address address = addressRepository.findById(shopOrder.getAddressId()).get();
            PaymentMethod paymentMethod = paymentMethodRepository.findById(shopOrder.getPaymentMethodId()).get();
            ShippingMethod shippingMethod = shippingMethodRepository.findById(shopOrder.getShippingMethodId()).get();
            shopOrderOld.setUser(takeUser);
            shopOrderOld.setVariant(variant);
            shopOrderOld.setAddress(address);
            shopOrderOld.setPaymentMethod(paymentMethod);
            shopOrderOld.setShippingMethod(shippingMethod);
            ShopOrder shopOrderNew = shopOrderRepository.save(shopOrderOld);
            ShopOrderResponse shopOrderResponse = shopOrderConverter.convertToDto(shopOrderNew);
            shopOrderResponseList.add(shopOrderResponse);
        }
        emailService.sendPaymentEmail(user);
        return shopOrderResponseList;
    }
}

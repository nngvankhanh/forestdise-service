package com.forestdise.converter.impl;

import com.forestdise.converter.AddressConverter;
import com.forestdise.converter.PaymentMethodConverter;
import com.forestdise.converter.ShippingMethodConverter;
import com.forestdise.converter.ShopOrderConverter;
import com.forestdise.entity.ShopOrder;
import com.forestdise.payload.request.ShopOrderRequest;
import com.forestdise.payload.response.ShopOrderResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
public class ShopOrderConverterImpl implements ShopOrderConverter {
    @Autowired
    private AddressConverter addressConverter;

    @Autowired
    private PaymentMethodConverter paymentMethodConverter;

    @Autowired
    private ShippingMethodConverter shippingMethodConverter;

    @Override
    public ShopOrderResponse convertToDto(ShopOrder shopOrder) {
        return ShopOrderResponse
                .builder()
                .address(addressConverter.convertToDto(shopOrder.getAddress()))
                .paymentMethod(paymentMethodConverter.convertToDto(shopOrder.getPaymentMethod()))
                .shippingMethod(shippingMethodConverter.convertToDto(shopOrder.getShippingMethod()))
                .build();
    }

    @Override
    public ShopOrder convertToEntity(ShopOrderRequest shopOrderRequest) {
        ShopOrder shopOrder = new ShopOrder();
        BeanUtils.copyProperties(shopOrderRequest,shopOrder);
        return shopOrder;
    }
}

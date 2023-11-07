package com.forestdise.service;

import com.forestdise.payload.request.ShopOrderRequest;
import com.forestdise.payload.response.ShopOrderResponse;

import java.util.List;

public interface ShopOrderService {
    ShopOrderResponse findShopOrder(Long userId);
    List<ShopOrderResponse> createShopOrder(List<ShopOrderRequest> shopOrderRequest);
}

package com.forestdise.converter;

import com.forestdise.entity.ShopOrder;
import com.forestdise.payload.request.ShopOrderRequest;
import com.forestdise.payload.response.ShopOrderResponse;

public interface ShopOrderConverter {
    ShopOrderResponse convertToDto(ShopOrder shopOrder);
    ShopOrder convertToEntity(ShopOrderRequest shopOrderRequest);
}

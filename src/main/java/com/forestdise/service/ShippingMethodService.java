package com.forestdise.service;

import com.forestdise.payload.response.ShippingMethodResponse;

import java.util.List;

public interface ShippingMethodService {
    List<ShippingMethodResponse> findShippingMethod();
}

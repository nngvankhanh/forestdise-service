package com.forestdise.service;

import com.forestdise.payload.request.AddressRequest;
import com.forestdise.payload.response.AddressResponse;

import java.util.List;

public interface AddressService {
    List<AddressResponse> findAddress(Long userId);
    AddressResponse createAddress(AddressRequest addressRequest);
    AddressResponse updateAddress(AddressRequest addressRequest);
}

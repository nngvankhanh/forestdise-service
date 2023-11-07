package com.forestdise.converter;

import com.forestdise.entity.Address;
import com.forestdise.payload.request.AddressRequest;
import com.forestdise.payload.response.AddressResponse;

public interface AddressConverter {
    AddressResponse convertToDto(Address address);
    Address convertToEntity(AddressRequest addressRequest);
}

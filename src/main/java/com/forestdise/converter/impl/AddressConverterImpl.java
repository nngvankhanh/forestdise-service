package com.forestdise.converter.impl;

import com.forestdise.converter.AddressConverter;
import com.forestdise.entity.Address;
import com.forestdise.payload.request.AddressRequest;
import com.forestdise.payload.response.AddressResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
@Transactional
@RequiredArgsConstructor
public class AddressConverterImpl implements AddressConverter {
    @Override
    public AddressResponse convertToDto(Address addressed) {
        return AddressResponse
                .builder()
                .id(addressed.getId())
                .district(addressed.getDistrict())
                .ward(addressed.getWard())
                .city(addressed.getCity())
                .street(addressed.getStreet())
                .build();
    }

    @Override
    public Address convertToEntity(AddressRequest addressRequest) {
        Address address = new Address();
        BeanUtils.copyProperties(addressRequest,address);
        return address;
    }
}

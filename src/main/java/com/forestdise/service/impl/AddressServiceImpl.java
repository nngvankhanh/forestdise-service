package com.forestdise.service.impl;

import com.forestdise.converter.AddressConverter;
import com.forestdise.entity.Address;
import com.forestdise.entity.User;
import com.forestdise.payload.request.AddressRequest;
import com.forestdise.payload.response.AddressResponse;
import com.forestdise.repository.AddressRepository;
import com.forestdise.repository.UserRepository;
import com.forestdise.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressConverter addressConverter;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<AddressResponse> findAddress(Long userId) {
        List<Address> addressList = addressRepository.findByUserId(userId);
        List<AddressResponse> addressResponseList = new ArrayList<>();
        for(Address address: addressList){
            AddressResponse addressResponse = addressConverter.convertToDto(address);
            addressResponseList.add(addressResponse);
        }
        return addressResponseList;
    }

    @Override
    public AddressResponse createAddress(AddressRequest addressRequest) {
        Address address = addressConverter.convertToEntity(addressRequest);
        User user = userRepository.findById(addressRequest.getUserId()).get();
        address.setUser(user);
        Address addressNew = addressRepository.save(address);
        return addressConverter.convertToDto(addressNew);
    }

    @Override
    public AddressResponse updateAddress(AddressRequest addressRequest) {
        Address address = addressRepository.findById(addressRequest.getId()).get();
        address.setDistrict(addressRequest.getDistrict());
        address.setWard(addressRequest.getWard());
        address.setCity(addressRequest.getCity());
        address.setStreet(addressRequest.getStreet());
        Address addressNew = addressRepository.save(address);
        return addressConverter.convertToDto(addressNew);
    }
}

package com.forestdise.converter.impl;

import com.forestdise.converter.SellerConverter;
import com.forestdise.dto.SellerDTO;
import com.forestdise.entity.Seller;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class SellerConverterImpl implements SellerConverter {
    @Override
    public List<SellerDTO> entitiesToDTOs(List<Seller> element) {
        return element.stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public SellerDTO entityToDTO(Seller element) {
        SellerDTO result = new SellerDTO();
        BeanUtils.copyProperties(element, result);
        return result;
    }

    @Override
    public Seller dtoToEntity(SellerDTO element) {
        Seller result = new Seller();
        BeanUtils.copyProperties(element, result);
        return result;
    }

    @Override
    public SellerDTO convertEntityToDTO(Seller seller) {
        SellerDTO result = new SellerDTO();
        BeanUtils.copyProperties(seller, result);
        return result;
    }
}

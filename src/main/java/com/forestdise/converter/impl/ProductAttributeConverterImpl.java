package com.forestdise.converter.impl;

import com.forestdise.converter.ProductAttributeConverter;
import com.forestdise.dto.ProductAttributeDTO;
import com.forestdise.entity.ProductAttribute;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductAttributeConverterImpl implements ProductAttributeConverter {
    @Override
    public List<ProductAttributeDTO> entitiesToDTOs(List<ProductAttribute> element) {
        return element.stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }
    @Override
    public ProductAttributeDTO entityToDTO(ProductAttribute element) {
        ProductAttributeDTO result = new ProductAttributeDTO();
        BeanUtils.copyProperties(element, result);
        return result;
    }
    @Override
    public ProductAttribute dtoToEntity(ProductAttributeDTO element) {
        ProductAttribute result = new ProductAttribute();
        BeanUtils.copyProperties(element, result);
        return result;
    }
}

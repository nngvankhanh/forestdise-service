package com.forestdise.converter;

import com.forestdise.dto.ProductAttributeDTO;
import com.forestdise.entity.ProductAttribute;

import java.util.List;

public interface ProductAttributeConverter {
    ProductAttribute dtoToEntity(ProductAttributeDTO element);
    List<ProductAttributeDTO> entitiesToDTOs(List<ProductAttribute> element);
    ProductAttributeDTO entityToDTO(ProductAttribute element);
}

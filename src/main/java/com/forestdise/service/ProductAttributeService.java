package com.forestdise.service;

import com.forestdise.dto.ProductAttributeDTO;
import com.forestdise.entity.ProductAttribute;

import java.util.List;

public interface ProductAttributeService {
    List<ProductAttributeDTO> getProductAttributeByProductId(Long product_id);
    List<ProductAttribute> createProductAttribute(List<ProductAttributeDTO> productAttributeDtoList, Long product_Id);
}

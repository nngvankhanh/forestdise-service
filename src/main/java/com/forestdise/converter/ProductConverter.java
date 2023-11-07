package com.forestdise.converter;

import com.forestdise.dto.ProductDTO;
import com.forestdise.entity.Product;

import java.util.List;

public interface ProductConverter {
    List<ProductDTO> entitiesToDTOs(List<Product> element);
    ProductDTO entityToDTO(Product element);
    Product dtoToEntity(ProductDTO element);
}

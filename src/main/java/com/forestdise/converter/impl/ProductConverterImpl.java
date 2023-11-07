package com.forestdise.converter.impl;

import com.forestdise.converter.ProductConverter;
import com.forestdise.dto.ProductDTO;
import com.forestdise.entity.Product;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class ProductConverterImpl implements ProductConverter {
    @Override
    public List<ProductDTO> entitiesToDTOs(List<Product> element) {
        return element.stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO entityToDTO(Product element) {
        ProductDTO result = new ProductDTO();
        BeanUtils.copyProperties(element, result);
        return result;
    }

    @Override
    public Product dtoToEntity(ProductDTO element) {
        Product result = new Product();
        BeanUtils.copyProperties(element, result);
        return result;
    }


}

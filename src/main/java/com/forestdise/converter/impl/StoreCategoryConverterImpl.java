package com.forestdise.converter.impl;

import com.forestdise.converter.StoreCategoryConverter;
import com.forestdise.dto.StoreCategoryDTO;
import com.forestdise.entity.StoreCategory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class StoreCategoryConverterImpl implements StoreCategoryConverter {
    @Override
    public List<StoreCategoryDTO> convertEntitiesToDTOs(List<StoreCategory> element) {
        return element.stream()
                .map(this::convertEntityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public StoreCategoryDTO convertEntityToDTO(StoreCategory element) {
        StoreCategoryDTO result = new StoreCategoryDTO();
        BeanUtils.copyProperties(element, result);
        return result;
    }

    @Override
    public StoreCategory dtoToEntity(StoreCategoryDTO element) {
        StoreCategory result = new StoreCategory();
        BeanUtils.copyProperties(element, result);
        return result;
    }
}

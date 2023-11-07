package com.forestdise.converter.impl;

import com.forestdise.converter.CategoryConverter;
import com.forestdise.dto.CategoryDTO;
import com.forestdise.entity.Category;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component

public class CategoryConverterImpl implements CategoryConverter {
    @Override
    public List<CategoryDTO> entitiesToDTOs(List<Category> element) {
        return element.stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryDTO entityToDTO(Category element) {
        CategoryDTO result = new CategoryDTO();
        BeanUtils.copyProperties(element, result);
        return result;
    }

    @Override
    public Category dtoToEntity(CategoryDTO element) {
        Category result = new Category();
        BeanUtils.copyProperties(element, result);
        return result;
    }
}

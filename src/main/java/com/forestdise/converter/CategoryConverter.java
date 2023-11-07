package com.forestdise.converter;

import com.forestdise.dto.CategoryDTO;
import com.forestdise.entity.Category;

import java.util.List;

public interface CategoryConverter {
    List<CategoryDTO> entitiesToDTOs(List<Category> element);
    CategoryDTO entityToDTO(Category element);
    Category dtoToEntity(CategoryDTO element);
}

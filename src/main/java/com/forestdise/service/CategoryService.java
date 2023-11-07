package com.forestdise.service;

import com.forestdise.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> findByText(String text);

    CategoryDTO findCategory(Long categoryId);
}

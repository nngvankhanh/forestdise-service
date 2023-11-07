package com.forestdise.converter;

import com.forestdise.dto.StoreCategoryDTO;
import com.forestdise.entity.StoreCategory;

import java.util.List;

public interface StoreCategoryConverter {
    List<StoreCategoryDTO> convertEntitiesToDTOs(List<StoreCategory> categories);
    StoreCategoryDTO convertEntityToDTO(StoreCategory element);
    StoreCategory dtoToEntity(StoreCategoryDTO element);

}

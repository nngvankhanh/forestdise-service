package com.forestdise.converter;

import com.forestdise.dto.StoreDTO;
import com.forestdise.entity.Store;

import java.util.List;

public interface StoreConverter {
    List<StoreDTO> entitiesToDTOs(List<Store> element);
    StoreDTO entityToDTO(Store element);
    Store dtoToEntity(StoreDTO element);
}

package com.forestdise.converter;

import com.forestdise.dto.VariantDTO;
import com.forestdise.entity.Variant;

import java.util.List;

public interface VariantConverter {
    List<VariantDTO> entitiesToDTOs(List<Variant> element);
    VariantDTO entityToDTO(Variant element);
    Variant dtoToEntity(VariantDTO element);
}

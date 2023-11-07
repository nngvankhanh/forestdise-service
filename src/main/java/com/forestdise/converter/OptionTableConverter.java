package com.forestdise.converter;

import com.forestdise.dto.OptionTableDTO;
import com.forestdise.entity.OptionTable;

import java.util.List;

public interface OptionTableConverter {
    OptionTableDTO entityToDTO(OptionTable element);

    List<OptionTableDTO> entitiesToDTOs(List<OptionTable> element);

    OptionTable dtoToEntity(OptionTableDTO element);
     List<OptionTable> dtoToEntities(List<OptionTableDTO> element);
}

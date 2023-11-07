package com.forestdise.converter;

import com.forestdise.dto.SaveForLaterDTO;
import com.forestdise.entity.SaveForLater;

import java.util.List;

public interface SaveForLaterConverter {
    SaveForLater convertDtoToEntity(SaveForLaterDTO saveForLaterDto);
    SaveForLaterDTO convertEntityToDto(SaveForLater saveForLater);
    List<SaveForLaterDTO> convertEntitiesToDtos(List<SaveForLater> saveForLaters);
    List<SaveForLater> convertDtoToEntities(List<SaveForLaterDTO> cartLineDtos);
}

package com.forestdise.converter;

import com.forestdise.dto.ImageDTO;
import com.forestdise.entity.Image;

import java.util.List;

public interface ImageConverter {
    ImageDTO entityToDTO(Image element);

    List<ImageDTO> entitiesToDTOs(List<Image> element);

    Image dtoToEntity(ImageDTO element);
    List<Image> dtosToEntities(List<ImageDTO> element);

}

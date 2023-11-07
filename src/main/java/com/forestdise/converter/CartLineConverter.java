package com.forestdise.converter;

import com.forestdise.dto.CartLineDTO;
import com.forestdise.entity.CartLine;

import java.util.List;

public interface CartLineConverter {
    CartLine convertDtoToEntity(CartLineDTO cartLineDto);
    CartLineDTO convertEntityToDto(CartLine cartLine);
    List<CartLineDTO> convertEntitiesToDtos(List<CartLine> cartLines);

    List<CartLine> convertDtoToEntities(List<CartLineDTO> cartLineDTOS);
}

package com.forestdise.converter;

import com.forestdise.dto.CartDTO;
import com.forestdise.entity.Cart;

public interface CartConverter {
    Cart convertDtoToEntity(CartDTO cartDto);
    CartDTO convertEntityToDto(Cart cart);
}

package com.forestdise.converter.impl;

import com.forestdise.converter.CartConverter;
import com.forestdise.dto.CartDTO;
import com.forestdise.entity.Cart;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CartConverterImpl implements CartConverter {
    @Override
    public Cart convertDtoToEntity(CartDTO cartDto) {
        Cart cart = new Cart();
        BeanUtils.copyProperties(cartDto, cart);
        return cart;
    }

    @Override
    public CartDTO convertEntityToDto(Cart cart) {
        CartDTO cartDto = new CartDTO();
        BeanUtils.copyProperties(cart, cartDto);
        return cartDto;
    }
}

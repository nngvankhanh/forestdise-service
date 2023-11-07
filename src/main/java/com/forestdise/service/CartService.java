package com.forestdise.service;

import com.forestdise.dto.UserRegisterDTO;
import com.forestdise.entity.Cart;
import com.forestdise.entity.User;


public interface CartService {
    Cart createCart (UserRegisterDTO userDTO);
    Cart findCartByUserId (User user);
    Cart findById(Long id);
}

package com.forestdise.controller;

import com.forestdise.dto.UserRegisterDTO;
import com.forestdise.entity.Cart;
import com.forestdise.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "https://forestdise.vercel.app")
@RequestMapping("/api/cart")
@AllArgsConstructor
public class CartController {
    private CartService cartService;

    @PostMapping
    public ResponseEntity<?> createCart(@RequestBody UserRegisterDTO userDTO){
        Cart cart = cartService.createCart(userDTO);
        return ResponseEntity.ok(cart);
    }
}

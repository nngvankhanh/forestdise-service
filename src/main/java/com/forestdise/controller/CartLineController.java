package com.forestdise.controller;

import com.forestdise.dto.CartLineDTO;
import com.forestdise.entity.Cart;
import com.forestdise.entity.User;
import com.forestdise.payload.request.CartLineRequest;
import com.forestdise.service.CartLineService;
import com.forestdise.service.CartService;
import com.forestdise.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "https://forestdise.vercel.app")
@RequestMapping("/api/cart-lines")
@AllArgsConstructor
public class CartLineController {
    private CartLineService cartLineService;
    private CartService cartService;
    private UserService userService;


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteCartLine(@PathVariable("id")Long cartLineId){
        cartLineService.removeCartLine(cartLineId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/add-to-cart")
    public ResponseEntity<CartLineDTO> addCartLine(@RequestBody CartLineRequest cartLineRequest){
        CartLineDTO cartLineDto = cartLineService.saveCartLine(cartLineRequest);
        return new ResponseEntity<>(cartLineDto ,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAllCartLines(@PathVariable("id") Long userId){
        User user = userService.findById(userId);
        Cart cart = cartService.findCartByUserId(user);
        List<CartLineDTO> cartLineDTOS = cartLineService.findCartLinesByCartId(cart.getId());
        return new ResponseEntity<>(cartLineDTOS,HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<?> updateCartLine(@RequestBody CartLineDTO cartLineDto) throws Exception {
        cartLineService.updateCartLine(cartLineDto, cartLineDto.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete-all/{id}")
    public ResponseEntity<?> deleteAllCartLine(@PathVariable("id") Long userId){
        User user = userService.findById(userId);
        Cart cart = cartService.findCartByUserId(user);
        cartLineService.removeAllCartLines(cart.getId());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}

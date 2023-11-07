package com.forestdise.controller;

import com.forestdise.dto.SaveForLaterDTO;
import com.forestdise.entity.Cart;
import com.forestdise.entity.User;
import com.forestdise.payload.request.SaveForLaterRequest;
import com.forestdise.service.CartService;
import com.forestdise.service.SaveForLaterService;
import com.forestdise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "https://forestdise.vercel.app")
@RequestMapping("/api/save-for-later")
public class SaveForLaterController {
    @Autowired
    private SaveForLaterService saveForLaterService;

    @Autowired
    private UserService userService;

    @Autowired
    private CartService cartService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getAllSaveForLater(@PathVariable("id") Long userId){
        User user = userService.findById(userId);
        Cart cart = cartService.findCartByUserId(user);
        List<SaveForLaterDTO> saveForLaterDTOS = saveForLaterService.findSaveForLaterByCartId(cart.getId());
        return new ResponseEntity<>(saveForLaterDTOS, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSaveForLater(@PathVariable("id") Long saveForLaterId) {
        saveForLaterService.removeSaveForLater(saveForLaterId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addSaveForLater(@RequestBody SaveForLaterRequest saveForLaterRequest){
        SaveForLaterDTO saveForLaterDto = saveForLaterService.addSaveForLater(saveForLaterRequest);
        return new ResponseEntity<>(saveForLaterDto, HttpStatus.OK);
    }
}

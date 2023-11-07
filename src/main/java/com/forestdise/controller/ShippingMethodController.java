package com.forestdise.controller;

import com.forestdise.service.ShippingMethodService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "https://forestdise.vercel.app")
@RequestMapping("/api/shipping-methods")
@AllArgsConstructor
public class ShippingMethodController {
    private ShippingMethodService shippingMethodService;

    @GetMapping
    public ResponseEntity<?> getAllShippingMethod(){

        return null;
    }
}

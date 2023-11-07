package com.forestdise.controller.back_office;

import com.forestdise.converter.SellerConverter;
import com.forestdise.dto.SellerDTO;
import com.forestdise.entity.Seller;
import com.forestdise.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "https://forestdise.vercel.app")
@RequestMapping("/api/sellers")
public class SellerController {
    @Autowired
    private SellerRepository sellerRepository;
    @Autowired
    private SellerConverter sellerConverter;

    @GetMapping("/{seller_id}")
    public ResponseEntity<SellerDTO> getUser(@PathVariable("seller_id") Long sellerId){
        Seller seller = sellerRepository.findById(sellerId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        SellerDTO sellerDTO = sellerConverter.convertEntityToDTO(seller);
        return ResponseEntity.ok(sellerDTO);
    }
}

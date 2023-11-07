package com.forestdise.service;

import com.forestdise.dto.SellerLoginDTO;
import com.forestdise.dto.SellerRegisterDTO;
import com.forestdise.entity.Seller;

public interface SellerService {
    String login(SellerLoginDTO sellerLoginDTO);
    Seller register(SellerRegisterDTO sellerRegisterDTO);
    Seller findById (Long id);
}

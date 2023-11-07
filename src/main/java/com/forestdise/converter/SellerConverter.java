package com.forestdise.converter;


import com.forestdise.dto.SellerDTO;
import com.forestdise.entity.Seller;

import java.util.List;

public interface SellerConverter {
    List<SellerDTO> entitiesToDTOs(List<Seller> element);
    SellerDTO entityToDTO(Seller element);
    Seller dtoToEntity(SellerDTO element);
    SellerDTO convertEntityToDTO(Seller seller);
}

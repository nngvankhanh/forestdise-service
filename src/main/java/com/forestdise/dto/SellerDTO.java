package com.forestdise.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SellerDTO {
    private Long id;
    private String sellerName;
    private String email;
    private String password;
    private Double balance;
}

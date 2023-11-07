package com.forestdise.payload.response;

import com.forestdise.dto.VariantDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VariantRawResponse {
    private VariantDTO variantDto;
    private String message;
}

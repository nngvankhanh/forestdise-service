package com.forestdise.payload.response;

import com.forestdise.dto.VariantDTO;
import lombok.Data;
import org.springframework.data.domain.Page;
@Data
public class SearchResponse {
    private Page<VariantDTO> variantDtoPage;


}

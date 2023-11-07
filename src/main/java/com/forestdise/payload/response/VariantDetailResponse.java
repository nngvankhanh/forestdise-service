package com.forestdise.payload.response;

import com.forestdise.dto.ImageDTO;
import com.forestdise.dto.OptionValueDTO;
import com.forestdise.dto.VariantDTO;
import com.forestdise.dto.VideoDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VariantDetailResponse {
    private List<ImageDTO> imageDTOS;
    private List<VideoDTO> videoDTOS;
    private List<OptionValueDTO> optionValueDTOS;
    private VariantDTO variantDto;
}

package com.forestdise.dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class VariantDTO {
    private Long id;
    private String name;
    private String skuCode;
    private int stockQuantity;
    private double weight;
    private double price;
    private double salePrice;
    private String img;
    private List<OptionValueDTO> optionValueDTOList;
    private List<ImageDTO> imageDTOList;
    private List<VideoDTO> videoDTOList;
    private List<ReviewDTO> reviewDTOList;
}

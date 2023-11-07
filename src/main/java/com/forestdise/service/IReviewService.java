package com.forestdise.service;


import com.forestdise.dto.ReviewDTO;
import com.forestdise.dto.SummaryDTO;

import java.util.List;

public interface IReviewService {
    public ReviewDTO getReviewById(Long id);
    public List<ReviewDTO> getReviewsByVariantId(Long variantId);
    public List<ReviewDTO> getReviewsByProductId(Long productId);
    public SummaryDTO getSummaryDtoByProductId(Long productId);
}

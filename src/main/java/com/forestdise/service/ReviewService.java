package com.forestdise.service;

import com.forestdise.dto.ReviewDTO;
import com.forestdise.dto.SummaryDTO;
import com.forestdise.payload.request.ReviewRequest;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface ReviewService {
    public ReviewDTO getReviewById(Long id);
    public List<ReviewDTO> getReviewsByVariantId(Long variantId);
    public List<ReviewDTO> getReviewsByProductId(Long productId);
    public SummaryDTO getSummaryDtoByProductId(Long productId);

    ReviewDTO save(ReviewRequest reviewRequest, Long variantId, Long userId);
}

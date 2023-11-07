package com.forestdise.controller;

import com.forestdise.dto.ReviewDTO;
import com.forestdise.payload.request.ReviewRequest;
import com.forestdise.payload.response.ReviewResponse;
import com.forestdise.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "https://forestdise.vercel.app")
@RequestMapping("/api/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/{variant_id}")
    public ResponseEntity<ReviewResponse> getReviewsByVariantId(@PathVariable("variant_id") Long variantId){
        ReviewResponse reviewResponse = new ReviewResponse();
        reviewResponse.setReviewDTOList(reviewService.getReviewsByVariantId(variantId));
        return new ResponseEntity<>(reviewResponse, HttpStatus.OK);

    }

    @GetMapping("/product/{product_id}")
    public ResponseEntity<ReviewResponse> getReviewsByProductId(@PathVariable("product_id") Long productId){
        ReviewResponse reviewResponse = new ReviewResponse();
        reviewResponse.setReviewDTOList(reviewService.getReviewsByProductId(productId));
        reviewResponse.setSummaryDto(reviewService.getSummaryDtoByProductId(productId));
        return new ResponseEntity<>(reviewResponse, HttpStatus.OK);

    }
    @PostMapping("/{userId}/{variantId}")
    public ResponseEntity<ReviewDTO> createReview(@RequestBody ReviewRequest reviewRequest, @PathVariable("variantId") Long variantId, @PathVariable("userId") Long userId){
        ReviewDTO response = reviewService.save(reviewRequest,variantId,userId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



}

package com.forestdise.converter.impl;

import com.forestdise.converter.ReviewConverter;
import com.forestdise.dto.ReviewDTO;
import com.forestdise.entity.Review;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class ReviewConverterImpl implements ReviewConverter {
    @Override
    public List<ReviewDTO> entitiesToDTOs(List<Review> element) {
        return element.stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ReviewDTO entityToDTO(Review element) {
        ReviewDTO result = new ReviewDTO();
        BeanUtils.copyProperties(element, result);
        return result;
    }

    @Override
    public Review dtoToEntity(ReviewDTO element) {
        Review result = new Review();
        BeanUtils.copyProperties(element, result);
        return result;
    }
}

package com.forestdise.payload.response;

import com.forestdise.dto.ReviewDTO;
import com.forestdise.dto.SummaryDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewResponse {
    private List<ReviewDTO> reviewDTOList;
    private SummaryDTO summaryDto;
}

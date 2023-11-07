package com.forestdise.payload.response;

import com.forestdise.dto.CategoryDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SearchCategoryResponse {
    List<CategoryDTO> categoryDtoList;
}

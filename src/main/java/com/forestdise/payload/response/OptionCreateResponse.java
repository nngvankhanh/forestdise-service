package com.forestdise.payload.response;

import com.forestdise.dto.OptionTableDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OptionCreateResponse {
    private List<OptionTableDTO> optionTableDtoList;
    private String message;
}

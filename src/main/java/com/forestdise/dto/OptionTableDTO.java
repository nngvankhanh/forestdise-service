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

public class OptionTableDTO {

    private Long id;
    private String name;
    private List<OptionValueDTO> optionValueDTOList;
}

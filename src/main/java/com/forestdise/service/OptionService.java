package com.forestdise.service;

import com.forestdise.dto.OptionTableDTO;

import java.util.List;

public interface OptionService {
    List<OptionTableDTO> createOption (List<OptionTableDTO> optionDto, Long product_id);
}

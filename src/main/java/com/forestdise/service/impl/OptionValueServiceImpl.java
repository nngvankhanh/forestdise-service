package com.forestdise.service.impl;

import com.forestdise.converter.impl.OptionValueConverterImpl;
import com.forestdise.dto.OptionValueDTO;
import com.forestdise.entity.OptionTable;
import com.forestdise.entity.OptionValue;
import com.forestdise.entity.Variant;
import com.forestdise.repository.OptionTableRepository;
import com.forestdise.repository.OptionValueRepository;
import com.forestdise.repository.VariantRepository;
import com.forestdise.service.OptionValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OptionValueServiceImpl implements OptionValueService {
    private final VariantRepository variantRepository;
    private final OptionValueRepository optionValueRepository;
    private final OptionTableRepository optionTableRepository;
    private final OptionValueConverterImpl optionValueConverterImpl;


    @Autowired
    public OptionValueServiceImpl(
            VariantRepository variantRepository,
            OptionValueRepository optionValueRepository,
            OptionTableRepository optionTableRepository,
            OptionValueConverterImpl optionValueConverterImpl
    ) {
        this.variantRepository = variantRepository;
        this.optionValueRepository = optionValueRepository;
        this.optionTableRepository = optionTableRepository;
        this.optionValueConverterImpl = optionValueConverterImpl;
    }
    @Override
    public List<OptionValueDTO> getOptionValuesByVariantId(Long variant_id) {
        Variant variant= variantRepository.findById(variant_id).orElse(null);
        return optionValueConverterImpl.entitiesToDTOs(variant.getOptionValues());
    }

    @Override
    public List<OptionValueDTO> createOptionValue(List<OptionValueDTO> optionValueDto, Long option_id) {
        List<OptionValue> optionValue = optionValueConverterImpl.dtosToEntities(optionValueDto) ;
        OptionTable option = optionTableRepository.findById(option_id).orElse(new OptionTable());
        for(OptionValue ele : optionValue){
            ele.setOptionTable(option);
            optionValueRepository.save(ele);
        }
        List<OptionValue> optionValueList = option.getOptionValues();
        return optionValueConverterImpl.entitiesToDTOs(optionValueList);
    }


}

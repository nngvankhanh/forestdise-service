package com.forestdise.controller.sellingController;

import com.forestdise.dto.OptionValueDTO;
import com.forestdise.payload.response.OptionValueCreateResponse;
import com.forestdise.service.impl.OptionValueServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "https://forestdise.vercel.app")
@RequestMapping("/api/option-value")
public class OptionValueController {
    private final OptionValueServiceImpl optionValueService;
    @Autowired
    public OptionValueController(OptionValueServiceImpl optionValueService ) {
        this.optionValueService = optionValueService;
    }
    @PostMapping("/{option_id}/create")
    public ResponseEntity<OptionValueCreateResponse> createOptionValue(@RequestBody List<String> valueRequest, @PathVariable("option_id") Long option_id){
        OptionValueCreateResponse optionValueCreateResponse= new OptionValueCreateResponse();
        List<OptionValueDTO> optionValueDtoList = new ArrayList<>();
        for(String ele : valueRequest){
            OptionValueDTO optionValueDto = OptionValueDTO.builder()
                    .value(ele)
                    .build();
            optionValueDtoList.add(optionValueDto);
        }
        List<OptionValueDTO> optionValue =optionValueService.createOptionValue(optionValueDtoList,option_id);
        if (optionValue != null) {
            optionValueCreateResponse.setMessage("OptionValue created successfully");
            optionValueCreateResponse.setOptionValueDtoList(optionValue);
            return new ResponseEntity<>(optionValueCreateResponse, HttpStatus.CREATED);
        } else {
            optionValueCreateResponse.setMessage("Failed to create OptionValue");
            return new ResponseEntity<>(optionValueCreateResponse,HttpStatus.BAD_REQUEST);
        }
    }
//    @GetMapping("/{variantId}")
//    public ResponseEntity<> getOptionValueListByVariantI()
}

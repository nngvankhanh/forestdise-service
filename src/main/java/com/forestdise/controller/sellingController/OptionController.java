package com.forestdise.controller.sellingController;

import com.forestdise.dto.OptionTableDTO;
import com.forestdise.payload.response.OptionCreateResponse;
import com.forestdise.service.impl.OptionServiceImpl;
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
@RequestMapping("/api/option/{product_id}")
public class OptionController {
    private final OptionServiceImpl optionService;
    @Autowired
    public OptionController(OptionServiceImpl optionService ) {
        this.optionService = optionService;
    }
    @PostMapping("/create")
    public ResponseEntity<OptionCreateResponse> createOption(@RequestBody List<String> optionRequest, @PathVariable("product_id") Long product_id){
        OptionCreateResponse optionCreateResponse= new OptionCreateResponse();

        List<OptionTableDTO> optionTableDtoList = new ArrayList<>();
        for(String ele : optionRequest){
            OptionTableDTO optionTableDto = OptionTableDTO.builder()
                    .name(ele)
                    .build();
            optionTableDtoList.add(optionTableDto);
        }
        List<OptionTableDTO> options =optionService.createOption(optionTableDtoList,product_id);

        if (options != null) {
            optionCreateResponse.setMessage("Option created successfully");
            optionCreateResponse.setOptionTableDtoList(options);
            return new ResponseEntity<>(optionCreateResponse, HttpStatus.CREATED);
        } else {
            optionCreateResponse.setMessage("Failed to create Option");
            return new ResponseEntity<>(optionCreateResponse,HttpStatus.BAD_REQUEST);
        }
    }
}

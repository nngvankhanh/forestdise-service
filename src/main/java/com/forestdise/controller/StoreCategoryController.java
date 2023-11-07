package com.forestdise.controller;

import com.forestdise.dto.StoreCategoryDTO;
import com.forestdise.service.StoreCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "https://forestdise.vercel.app")
@RequestMapping("/api/store-category")
public class StoreCategoryController {
    @Autowired
    private StoreCategoryService iStoreCategoryService;

    @PostMapping("/{storeId}")
    public List<StoreCategoryDTO> createListStoreCategory(@RequestBody List<String> storeCateList,@PathVariable Long storeId){
        List<StoreCategoryDTO> storeCategoryDTOS = iStoreCategoryService.createStoreCategorys(storeCateList,storeId);
        return storeCategoryDTOS;
    }
}

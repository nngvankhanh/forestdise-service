package com.forestdise.controller;

import com.forestdise.dto.CategoryDTO;
import com.forestdise.payload.response.SearchCategoryResponse;
import com.forestdise.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "https://forestdise.vercel.app")
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService iCategoryService;
    @GetMapping("/search")
    public ResponseEntity<SearchCategoryResponse> searchCategories(@RequestParam("query") String query){
        SearchCategoryResponse searchCategoryResponse = new SearchCategoryResponse();
        List<CategoryDTO> categoryDtoList = iCategoryService.findByText(query);
        searchCategoryResponse.setCategoryDtoList(categoryDtoList);
        return ResponseEntity.ok(searchCategoryResponse);
    }
    @GetMapping("/{category_id}")
    public ResponseEntity<CategoryDTO> getStore(@PathVariable("category_id") Long category_id) {
        CategoryDTO CategoryDto = iCategoryService.findCategory(category_id);
        return new ResponseEntity<>(CategoryDto, HttpStatus.OK);
    }
}

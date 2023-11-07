package com.forestdise.controller;

import com.forestdise.dto.ProductDTO;
import com.forestdise.dto.StoreDTO;
import com.forestdise.payload.request.AddStoreRequest;
import com.forestdise.repository.ProductRepository;
import com.forestdise.repository.StoreRepository;
import com.forestdise.service.ProductService;
import com.forestdise.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "https://forestdise.vercel.app")
@RequestMapping("/api/stores")
public class StoreController {
    @Autowired
    StoreService storeService;
    @Autowired
    StoreRepository storeRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductService productService;

    @GetMapping("/{store_id}")
    public ResponseEntity<StoreDTO> getStore(@PathVariable("store_id") Long storeId) {
        StoreDTO storeDto = storeService.findStore(storeId);
        return new ResponseEntity<>(storeDto, HttpStatus.OK);
    }

    @GetMapping("/{store_id}/product")
    public ResponseEntity<List<ProductDTO>> getProductsByName(@RequestParam("name")String searchText, @PathVariable("store_id") Long storeId){
        List<ProductDTO> productDTOList = productService.getProductsOfStoreByContaining(storeId, searchText);
        return new ResponseEntity<>(productDTOList, HttpStatus.OK);
    }

    @GetMapping("/{store_id}/products")
    public ResponseEntity<List<ProductDTO>> getProductsByStore(@PathVariable("store_id") Long storeId){
        List<ProductDTO> productDTOList = productService.getAllProductDtosByStore(storeId);
        return new ResponseEntity<>(productDTOList, HttpStatus.OK);
    }

    @GetMapping("/{store_id}/products/category")
    public ResponseEntity<List<ProductDTO>> getProductsByStoreCategory(@RequestParam("name")String categoryName){
        List<ProductDTO> productDTOList = productService.getAllProductDtosByStoreCategory(categoryName);
        return new ResponseEntity<>(productDTOList, HttpStatus.OK);
    }

    @GetMapping("/{store_id}/products/sub_category")
    public ResponseEntity<List<ProductDTO>> getProductsByStoreSubCategory(@RequestParam("name")String categoryName){
        List<ProductDTO> productDTOList = productService.getAllProductDtosByStoreSubCategory(categoryName);
        return new ResponseEntity<>(productDTOList, HttpStatus.OK);
    }

    @PostMapping("/create/{sellerId}")
    @Transactional
    public ResponseEntity<StoreDTO> createStore(@PathVariable("sellerId") Long sellerId, @RequestBody AddStoreRequest storeDto){
        StoreDTO storeDto1 = storeService.createStore(sellerId,storeDto);
        return new ResponseEntity<>(storeDto1,HttpStatus.OK);
    }


}

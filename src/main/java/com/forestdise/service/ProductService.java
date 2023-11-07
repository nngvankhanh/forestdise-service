package com.forestdise.service;

import com.forestdise.dto.OptionTableDTO;
import com.forestdise.dto.ProductDTO;
import com.forestdise.dto.StoreDTO;
import com.forestdise.dto.VariantDTO;
import com.forestdise.entity.Product;

import java.util.List;

public interface ProductService {

    public List<ProductDTO> getAllProductDtosByStore(Long id);
    List<ProductDTO> getAllProductDtosByStoreCategory(String categoryName);
    List<ProductDTO> getAllProductDtosByStoreSubCategory(String categoryName);
     ProductDTO getProductById(Long id);
     List<ProductDTO> getAllProductDtos();
     List<VariantDTO> getVariantsByProductId(Long productId);
     StoreDTO getStoreByProductId(Long productId);
     List<OptionTableDTO> getOptionsByProductId(Long productId);
     List<ProductDTO> getProductsByContaining(String text);
     Product createProduct(Long storeId, Long categoryId, Long storeCategoryId, ProductDTO productDto);

    List<ProductDTO> getProductsOfStoreByContaining(Long id, String text);
     Product updateProduct(ProductDTO productDto);
     void    deleteProduct(Long productId);

}

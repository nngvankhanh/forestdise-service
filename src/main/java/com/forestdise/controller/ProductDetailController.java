package com.forestdise.controller;

import com.forestdise.dto.*;
import com.forestdise.entity.Product;
import com.forestdise.payload.request.ProductRequest;
import com.forestdise.payload.response.ProductDetailResponse;
import com.forestdise.payload.response.VariantDetailResponse;
import com.forestdise.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.List;

@RestController
@CrossOrigin(origins = "https://forestdise.vercel.app")
@RequestMapping("/api/product-detail")
public class ProductDetailController {
    private final ImageService imageServiceImpl;
    private final ProductService productServiceImpl;
    private final ProductAttributeService productAttributeServiceImpl;
    private final VariantService variantServiceImpl;
    private final VideoService videoServiceImpl;

    private final OptionValueService optionValueServiceImpl;
    @Autowired
    public ProductDetailController(ImageService imageServiceImpl,
                                           ProductService productServiceImpl,
                                           ProductAttributeService productAttributeServiceImpl,
                                           VariantService variantServiceImpl,
                                           VideoService videoServiceImpl,
                                           OptionValueService optionValueServiceImpl) {
        this.imageServiceImpl =imageServiceImpl;
        this.productServiceImpl= productServiceImpl;
        this.productAttributeServiceImpl=productAttributeServiceImpl;
        this.variantServiceImpl=variantServiceImpl;
        this.videoServiceImpl=videoServiceImpl;
        this.optionValueServiceImpl=optionValueServiceImpl;
    }

    ProductDetailResponse productDetailResponse=new ProductDetailResponse();
    VariantDetailResponse variantDetailResponse =new VariantDetailResponse();

    @GetMapping("/{product_id}")
    public ResponseEntity<ProductDetailResponse> getProduct(@PathVariable("product_id") Long productId) {
        productDetailResponse.setProductDTO(productServiceImpl.getProductById(productId));
        productDetailResponse.setStoreDto(productServiceImpl.getStoreByProductId(productId));
        productDetailResponse.setOptionTableDto(productServiceImpl.getOptionsByProductId(productId));
        productDetailResponse.setVariantDTOList(variantServiceImpl.getVariantByProductId(productId));
        productDetailResponse.setProductAttributeDTOList(productAttributeServiceImpl.getProductAttributeByProductId(productId));
        return ResponseEntity.ok(productDetailResponse);
    }
    @GetMapping("/{product_id}/{variant_id}")
    public ResponseEntity<VariantDetailResponse> getVariant(@PathVariable("variant_id") Long variantId){
        List<ImageDTO> images = imageServiceImpl.getImageByVariantId(variantId);
        List<VideoDTO> videos = videoServiceImpl.getVideosByVariantId(variantId);
        VariantDTO variantDto = variantServiceImpl.getVariantById(variantId);
        List<OptionValueDTO> optionValueDTOList = optionValueServiceImpl.getOptionValuesByVariantId(variantId);
        variantDetailResponse.setVariantDto(variantDto);
        variantDetailResponse.setImageDTOS(images);
        variantDetailResponse.setVideoDTOS(videos);
        variantDetailResponse.setOptionValueDTOS(optionValueDTOList);
        return ResponseEntity.ok(variantDetailResponse);
    }
    @PostMapping("/create/{storeId}/{categoryId}/{storeCategoryId}")
    public ResponseEntity<Product> createProduct(@RequestBody ProductRequest productRequest,@PathVariable Long storeId,@PathVariable Long categoryId,@PathVariable Long storeCategoryId) {

        ProductDTO productDto = ProductDTO.builder()
                .title(productRequest.getTitle())
                .status("ACTIVE")
                .createAt(Calendar.getInstance().getTime()) // lay thoi gian hien tai
                .updatedAt(Calendar.getInstance().getTime())
                .description(productRequest.getDescription())
                .mainPicture(productRequest.getMainPicture())
                .build();
        Product product = productServiceImpl.createProduct(storeId,categoryId,storeCategoryId,productDto);

        if (product != null) {

            return new ResponseEntity<>(product, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(product, HttpStatus.BAD_REQUEST);
        }
    }
    @PutMapping("/update/{product_id}")
    public ResponseEntity<String> updateProduct(@PathVariable("product_id") Long productId, @RequestBody ProductRequest productRequest) {
        ProductDTO productDto = productServiceImpl.getProductById(productId);

        if (productDto == null) {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }

        productDto.setTitle(productRequest.getTitle());
        productDto.setDescription(productRequest.getDescription());
        productDto.setMainPicture(productRequest.getMainPicture());
        productDto.setStatus("currently for sale");
        productDto.setCreateAt(Calendar.getInstance().getTime());
        productDto.setUpdatedAt(Calendar.getInstance().getTime());

        Product updatedProduct = productServiceImpl.updateProduct(productDto);

        if (updatedProduct != null) {
            return new ResponseEntity<>("Product updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failed to update product", HttpStatus.BAD_REQUEST);
        }
    }
    @DeleteMapping("/delete/{product_id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("product_id") Long productId) {
        ProductDTO productDto = productServiceImpl.getProductById(productId);

        if (productDto == null) {
            return new ResponseEntity<>("Product not found", HttpStatus.NOT_FOUND);
        }

        try {
            productServiceImpl.deleteProduct(productId);
            return new ResponseEntity<>("Product deleted successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to delete product", HttpStatus.BAD_REQUEST);
        }
    }

}

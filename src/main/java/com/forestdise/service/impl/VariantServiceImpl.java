package com.forestdise.service.impl;

import com.forestdise.converter.*;
import com.forestdise.dto.*;
import com.forestdise.entity.*;
import com.forestdise.repository.*;
import com.forestdise.service.ReviewService;
import com.forestdise.service.VariantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VariantServiceImpl implements VariantService {

    @Autowired
    private VariantConverter variantConverterImpl;
    @Autowired
    private VariantRepository variantRepository;
    @Autowired
    private OptionValueConverter optionValueConverter;
    @Autowired
    private ImageConverter iImageConverter;
    @Autowired
    private VideoConverter iVideoConverter;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OptionValueRepository optionValueRepository;
    @Autowired
    private ReviewConverter reviewConverter;
    @Autowired
    private UserConverter userConverter;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private VideoRepository videoRepository;
    public VariantDTO getVariantById(Long id) {
        return variantConverterImpl.entityToDTO(variantRepository.findById(id).orElse(null));
    }

    @Transactional
    public List<VariantDTO> getVariantByProductId(Long product_id) {
        List<VariantDTO> variantDtoList = new ArrayList<>();
        List<Variant> variants = variantRepository.findByProduct_Id(product_id);
        for(Variant variant : variants){
            List<OptionValue> optionValueList = variant.getOptionValues();
            List<Image> imageList = variant.getImages();
            List<Video> videoList = variant.getVideos();
            List<ReviewDTO> reviewList = reviewService.getReviewsByVariantId(variant.getId());
            List<ImageDTO> imageDTOList = iImageConverter.entitiesToDTOs(imageList);
            List<VideoDTO> videoDTOList = iVideoConverter.entitiesToDTOs(videoList);
            List<OptionValueDTO> optionValueDto = optionValueConverter.entitiesToDTOs(optionValueList);
            VariantDTO variantDto = variantConverterImpl.entityToDTO(variant);
            variantDto.setOptionValueDTOList(optionValueDto);
            variantDto.setImageDTOList(imageDTOList);
            variantDto.setVideoDTOList(videoDTOList);
            variantDto.setReviewDTOList(reviewList);
            variantDtoList.add(variantDto);
        }
        return variantDtoList;
    }


    @Override
    public Variant findById(Long id) {
        Variant variant = variantRepository.findById(id).orElse(null);
        return variant;
    }
    public VariantDTO getLowestPriceVariantByProductId(Long product_id){
        List<Variant> variants = variantRepository.findByProduct_Id(product_id);
        Variant minVariant = variants.get(0);
        for(Variant variant : variants){
            if(variant.getSalePrice() < minVariant.getSalePrice()){
                minVariant = variant;
            }
        }
        List<OptionValue> optionValueList = minVariant.getOptionValues();
        List<Image> images = minVariant.getImages();
        List<Video> videos = minVariant.getVideos();
        List<OptionValueDTO> optionValueDto = optionValueConverter.entitiesToDTOs(optionValueList);
        List<ImageDTO> imageDtoList = iImageConverter.entitiesToDTOs(images);
        List<VideoDTO> videoDtoList = iVideoConverter.entitiesToDTOs(videos);
        VariantDTO variantDto = variantConverterImpl.entityToDTO(minVariant);
        variantDto.setOptionValueDTOList(optionValueDto);
        variantDto.setImageDTOList(imageDtoList);
        variantDto.setVideoDTOList(videoDtoList);
        return variantDto;
    }
    @Override
    public VariantDTO getVariantByProductPriceMin(Long product_id) {
        Variant variant = variantRepository.findTopByProductIdOrderByPriceAsc(product_id);
        return variantConverterImpl.entityToDTO(variant);
    }

    @Override
    public Variant createVariant(VariantDTO variantDto, Long product_id) {
        Variant variant= variantConverterImpl.dtoToEntity(variantDto);
        Product product = productRepository.findById(product_id).orElse(null);
        variant.setProduct(product);
        return variantRepository.save(variant);
    }

    @Override
    public VariantDTO updateVariant(Long variantId,VariantDTO variantDto) {
        Variant variant = variantRepository.findById(variantId).orElseThrow(() -> new EntityNotFoundException("variant not found"));
        variant.setName(variantDto.getName());
        variant.setSkuCode(variantDto.getSkuCode());
        variant.setStockQuantity(variantDto.getStockQuantity());
        variant.setWeight(variantDto.getWeight());
        variant.setPrice(variantDto.getPrice());
        variant.setSalePrice(variantDto.getSalePrice());
        Variant variant1 = variantRepository.save(variant);
        VariantDTO variantDTO = variantConverterImpl.entityToDTO(variant1);
        return variantDTO;
    }
    @Override
    public void deleteVariant(Long variantId) {
        Variant variant = variantRepository.findById(variantId).orElseThrow(() -> new EntityNotFoundException("variant not found"));
        List<Image> images = variant.getImages();
        List<Video> videos = variant.getVideos();
        for(Image ele : images){
            imageRepository.deleteById(ele.getId());
        }
        for(Image ele1 : images){
            videoRepository.deleteById(ele1.getId());
        }
        variantRepository.deleteById(variantId);
    }
    public Page<VariantDTO> getVariantsByContaining(String text, Pageable pageable){
        Page<Variant> variantPage = variantRepository.findByNameContaining(text, pageable);
        return variantPage.map(variantConverterImpl::entityToDTO);
    }

    @Override
    public Page<VariantDTO> getVariantsByNameContainingAndPriceBetween(String text, double minPrice, double maxPrice, Pageable pageable ) {
        Page<Variant> variantPage = variantRepository.findVariantsByNameContainingAndPriceBetween(text,minPrice,maxPrice,pageable);
        System.out.println("888888888888888888888"+variantPage.getSize());
        return variantPage.map(variantConverterImpl::entityToDTO);
    }
    @Override
    public Page<VariantDTO> getVariantsBySearchTextAndRating(String text, long star, Pageable pageable) {
        List<VariantDTO> variantList = new ArrayList<>();
        Page<Variant> variantPage = variantRepository.findByNameContaining(text, pageable);
        if (variantPage!=null){
            for (Variant variant : variantPage) {
                if (variantRepository.findAverageStarByReview(variant)!=null) {
                    long rating = Math.round(variantRepository.findAverageStarByReview(variant));
                    System.out.println("******************************************"+ rating);
                    if (rating == star) {
                        variantList.add(variantConverterImpl.entityToDTO(variant));
                    }

                }
            }
        }

        return new PageImpl<>(variantList, pageable, variantPage.getTotalElements());
    }

    public VariantDTO createRawVariant(List<Long> valueIdList, Long productId) {
        List<OptionValue> optionValueList = new ArrayList<>();
        for(Long ele : valueIdList){
            OptionValue optionValue = optionValueRepository.findById(ele).orElse(new OptionValue());
            optionValueList.add(optionValue);
        }
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("product not found"));
        Variant variant = new Variant();
        variant.setProduct(product);
        variant.setOptionValues(optionValueList);
        variantRepository.save(variant);
        List<OptionValue> optionValues = variant.getOptionValues();
        List<OptionValueDTO> optionValueDtoList = optionValueConverter.entitiesToDTOs(optionValues);
        VariantDTO variantDto = variantConverterImpl.entityToDTO(variant);
        variantDto.setOptionValueDTOList(optionValueDtoList);
        return variantDto;
    }

    @Override
    public VariantDTO getVariantInfoById(Long id) {
        Variant variant = variantRepository.findById(id).orElse(null);
        if(variant == null){
            return null;
        } else {
            List<OptionValue> optionValueList = variant.getOptionValues();
            List<Image> images = variant.getImages();
            List<Video> videos = variant.getVideos();
            List<OptionValueDTO> optionValueDto = optionValueConverter.entitiesToDTOs(optionValueList);
            List<ImageDTO> imageDtoList = iImageConverter.entitiesToDTOs(images);
            List<VideoDTO> videoDtoList = iVideoConverter.entitiesToDTOs(videos);
            VariantDTO variantDto = variantConverterImpl.entityToDTO(variant);
            variantDto.setOptionValueDTOList(optionValueDto);
            variantDto.setImageDTOList(imageDtoList);
            variantDto.setVideoDTOList(videoDtoList);
            return variantDto;
        }

    }
    @Override
    public Page<VariantDTO> getNewestVariantsByText(String text, Pageable pageable){
        Page<Variant> variantPage = variantRepository.findByNameContaining(text, pageable);
        List<Variant> variants = variantPage.getContent();
        List<Variant> sortedVariants = variants.stream()
                .sorted((variant1, variant2) -> {
                    Date createdAt1 = variantRepository.findCreatedAtByVariantId(variant1.getId());
                    Date createdAt2 = variantRepository.findCreatedAtByVariantId(variant2.getId());
                    return createdAt1.compareTo(createdAt2); // xep ngay theo giam dan
                })
                .collect(Collectors.toList());
        Page<Variant> sortedVariantPage = new PageImpl<>(sortedVariants, pageable, variantPage.getTotalElements());
        List<VariantDTO> sortedVariantDTOs = sortedVariants.stream()
                .map(variant -> variantConverterImpl.entityToDTO(variant))
                .collect(Collectors.toList());
        return new PageImpl<>(sortedVariantDTOs, pageable, sortedVariantPage.getTotalElements());

    }

}

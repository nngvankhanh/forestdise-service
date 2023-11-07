package com.forestdise.service.impl;

import com.forestdise.converter.StoreCategoryConverter;
import com.forestdise.dto.StoreCategoryDTO;
import com.forestdise.entity.Store;
import com.forestdise.entity.StoreCategory;
import com.forestdise.repository.StoreCategoryRepository;
import com.forestdise.repository.StoreRepository;
import com.forestdise.service.StoreCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
public class StoreCategoryServiceImpl implements StoreCategoryService {
    @Autowired
    private StoreCategoryRepository storeCategoryRepository;
    @Autowired
    private StoreRepository storeRepository;
    @Autowired
    private StoreCategoryConverter storeCategoryConverter;
    @Override
    public List<StoreCategoryDTO> createStoreCategorys(List<String> storeCateList, Long storeId) {
        List<StoreCategoryDTO> storeCategoryDTOS = new ArrayList<>();
        List<StoreCategory> storeCategories = new ArrayList<>();
        Store store = storeRepository.findById(storeId)
                .orElseThrow(()-> new EntityNotFoundException("Store not found"));
        for(String element: storeCateList){
            StoreCategoryDTO storeCategoryDTO = new StoreCategoryDTO();
            storeCategoryDTO.setHeroImage("https://m.media-amazon.com/images/S/aplus-media-library-service-media/721c2b2d-4528-481e-b722-6b5b44937046.__CR0,0,300,225_PT0_SX300_V1___.jpg");
            storeCategoryDTO.setSquareImage("https://m.media-amazon.com/images/S/stores-image-uploads-na-prod/e/AmazonStores/ATVPDKIKX0DER/946799e337c1bb687ede3fabb0c39d27.w784.h816._CR72%2C206%2C624%2C400_SX200_.png");
            storeCategoryDTO.setName(element);
            StoreCategory storeCategory = storeCategoryConverter.dtoToEntity(storeCategoryDTO);
            storeCategory.setStore(store);
            storeCategories.add(storeCategoryRepository.save(storeCategory));
        }
        return storeCategoryConverter.convertEntitiesToDTOs(storeCategories);
    }

    @Override
    public StoreCategoryDTO createStoreCategory(StoreCategoryDTO storeCategoryDTO, Long storeCategoryId) {
        return null;
    }
}

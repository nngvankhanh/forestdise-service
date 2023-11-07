package com.forestdise.service.impl;

import com.forestdise.converter.StoreCategoryConverter;
import com.forestdise.converter.StoreConverter;
import com.forestdise.dto.StoreCategoryDTO;
import com.forestdise.dto.StoreDTO;
import com.forestdise.entity.Seller;
import com.forestdise.entity.Store;
import com.forestdise.entity.StoreCategory;
import com.forestdise.payload.request.AddStoreRequest;
import com.forestdise.repository.SellerRepository;
import com.forestdise.repository.StoreRepository;
import com.forestdise.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {
    private final StoreRepository storeRepository;
    private final StoreConverter storeConverter;

    @Autowired
    public StoreServiceImpl(StoreRepository storeRepository, StoreConverter storeConverter) {
        this.storeRepository = storeRepository;
        this.storeConverter = storeConverter;
    }


    @Autowired
    SellerRepository sellerRepository;

    @Autowired
    StoreCategoryConverter storeCategoryConverter;
    @Override
    public StoreDTO findStore(Long id) {
        Store store = storeRepository.findById(id).orElse(null);
        List<StoreCategory> storeCategories = store.getStoreCategoryList();
        List<StoreCategoryDTO> storeCategoryDTOS = new ArrayList<>();
        List<StoreCategoryDTO> parentStoreCategoryDTOS = new ArrayList<>();
        for (StoreCategory storeCategory:
             storeCategories) {
           StoreCategoryDTO storeCategoryDTO = storeCategoryConverter.convertEntityToDTO(storeCategory);
           storeCategoryDTOS.add(storeCategoryDTO);
           StoreCategory parentStoreCategory = storeCategory.getParentStoreCategory();
           if(parentStoreCategory != null){
               StoreCategoryDTO parentStoreCategoryDTO = storeCategoryConverter.convertEntityToDTO(parentStoreCategory);
               parentStoreCategoryDTOS.add(parentStoreCategoryDTO);
           }
        }
        StoreDTO storeDto = storeConverter.entityToDTO(store);
        storeDto.setStoreCategoryList(storeCategoryDTOS);
        return storeDto;
    }

    @Override
    public StoreDTO createStore(Long sellerId, AddStoreRequest storeDto) {
        Seller seller = sellerRepository.findById(sellerId).orElseThrow(
                () -> new UsernameNotFoundException("seller not found"));
        Store store = new Store();
        store.setName(storeDto.getName());
        store.setDealsImage("https://m.media-amazon.com/images/S/al-na-9d5791cf-3faf/1f6cbd86-1e6a-42d5-bd7c-e137bfef3fc6._CR0%2C0%2C3000%2C600_SX1920_.jpg");
        store.setHomeImage("https://congluan-cdn.congluan.vn/files/dieulinh/2020/07/16/st2-0904.jpg");
        store.setDealsSquareImage("https://i.ytimg.com/vi/YczCAwQ3wgs/maxresdefault.jpg");
        store.setInteractiveImage("https://png.pngtree.com/background/20210715/original/pngtree-black-friday-neon-lights-pink-background-banner-picture-image_1266832.jpg");
        store.setLogo("https://img.pikbest.com/origin/06/53/62/292pIkbEsTqDa.jpg!w700wp");
        store.setSeller(seller);
        storeRepository.save(store);
        StoreDTO storeDto1 = storeConverter.entityToDTO(store);
        return storeDto1;
    }

}

package com.forestdise.service;

import com.forestdise.dto.StoreDTO;
import com.forestdise.payload.request.AddStoreRequest;

public interface StoreService {

    StoreDTO findStore(Long id);

    StoreDTO createStore(Long sellerId, AddStoreRequest storeDto);
}

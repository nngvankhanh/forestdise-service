package com.forestdise.repository;

import com.forestdise.entity.Store;
import com.forestdise.entity.StoreCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StoreCategoryRepository extends JpaRepository<StoreCategory, Long> {
    List<StoreCategory> findAllByParentStoreCategory(StoreCategory parentCategory);
    List<StoreCategory> findAllByStore(Store store);
    StoreCategory findByName(String name);
}

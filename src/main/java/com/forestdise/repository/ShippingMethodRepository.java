package com.forestdise.repository;

import com.forestdise.entity.ShippingMethod;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ShippingMethodRepository extends JpaRepository<ShippingMethod, Long> {
}

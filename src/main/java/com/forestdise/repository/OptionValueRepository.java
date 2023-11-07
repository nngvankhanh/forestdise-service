package com.forestdise.repository;

import com.forestdise.entity.OptionValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionValueRepository extends JpaRepository<OptionValue,Long> {
}

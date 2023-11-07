package com.forestdise.repository;

import com.forestdise.entity.OptionTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OptionTableRepository  extends JpaRepository<OptionTable, Long> {
}

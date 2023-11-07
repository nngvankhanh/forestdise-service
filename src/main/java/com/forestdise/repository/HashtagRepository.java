package com.forestdise.repository;

import com.forestdise.entity.HashTag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HashtagRepository extends JpaRepository<HashTag,Long> {
}

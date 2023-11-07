package com.forestdise.repository;

import com.forestdise.entity.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface VideoRepository extends JpaRepository<Video,Long> {
    List<Video> findVideosByVariant_Id(Long id) ;
}

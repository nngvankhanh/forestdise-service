package com.forestdise.service.impl;
import com.forestdise.converter.impl.VideoConverterImpl;
import com.forestdise.dto.VideoDTO;
import com.forestdise.entity.Variant;
import com.forestdise.entity.Video;
import com.forestdise.repository.VariantRepository;
import com.forestdise.repository.VideoRepository;
import com.forestdise.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl implements VideoService {
    private final VideoRepository videoRepository;
    private final VideoConverterImpl videoConverterImpl;
    private final VariantRepository variantRepository;

    @Autowired
    public VideoServiceImpl(VideoRepository videoRepository,
                            VideoConverterImpl videoConverterImpl,
                            VariantRepository variantRepository) {
        this.videoRepository = videoRepository;
        this.videoConverterImpl = videoConverterImpl;
        this.variantRepository = variantRepository;

    }
    public List<VideoDTO> getVideosByVariantId(Long variant_id){
        List<Video> videos = videoRepository.findVideosByVariant_Id(variant_id);
        return videoConverterImpl.entitiesToDTOs(videos);
    }

    @Override
    public Video createVideo(VideoDTO videoDto, Long variant_id) {
        Variant variant = variantRepository.findById(variant_id).orElse(null);
        Video video = videoConverterImpl.dtoToEntity(videoDto);
        video.setVariant(variant);
        return videoRepository.save(video);
    }
}

package com.forestdise.converter.impl;

import com.forestdise.converter.VideoConverter;
import com.forestdise.dto.VideoDTO;
import com.forestdise.entity.Video;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class VideoConverterImpl implements VideoConverter {
    @Override
    public List<VideoDTO> entitiesToDTOs(List<Video> element) {
        return element.stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public VideoDTO entityToDTO(Video element) {
        VideoDTO result = new VideoDTO();
        BeanUtils.copyProperties(element, result);
        return result;
    }

    @Override
    public Video dtoToEntity(VideoDTO element) {
        Video result = new Video();
        BeanUtils.copyProperties(element, result);
        return result;
    }
}

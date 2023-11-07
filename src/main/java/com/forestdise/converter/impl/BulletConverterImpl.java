package com.forestdise.converter.impl;

import com.forestdise.converter.BulletConverter;
import com.forestdise.dto.BulletDTO;
import com.forestdise.entity.Bullet;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class BulletConverterImpl implements BulletConverter {
    @Override
    public List<BulletDTO> entitiesToDTOs(List<Bullet> element) {
        return element.stream()
                .map(this::entityToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public BulletDTO entityToDTO(Bullet element) {
        BulletDTO result = new BulletDTO();
        BeanUtils.copyProperties(element, result);
        return result;    }

    @Override
    public Bullet dtoToEntity(BulletDTO element) {
        Bullet result = new Bullet();
        BeanUtils.copyProperties(element, result);
        return result;    }
}

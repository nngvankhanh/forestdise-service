package com.forestdise.converter.impl;

import com.forestdise.converter.CartConverter;
import com.forestdise.converter.SaveForLaterConverter;
import com.forestdise.converter.VariantConverter;
import com.forestdise.dto.CartDTO;
import com.forestdise.dto.SaveForLaterDTO;
import com.forestdise.dto.VariantDTO;
import com.forestdise.entity.Cart;
import com.forestdise.entity.SaveForLater;
import com.forestdise.entity.Variant;
import com.forestdise.service.CartService;
import com.forestdise.service.VariantService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SaveForLaterConverterImpl implements SaveForLaterConverter {
    @Autowired
    private CartService cartService;

    @Autowired
    private VariantService variantService;

    @Autowired
    private CartConverter cartConverter;

    @Autowired
    private VariantConverter variantConverter;

    @Override
    public SaveForLater convertDtoToEntity(SaveForLaterDTO saveForLaterDto) {
        SaveForLater saveForLater = new SaveForLater();
        BeanUtils.copyProperties(saveForLaterDto, saveForLater);
        Cart cart = cartService.findById(saveForLaterDto.getCartDto().getId());
        Variant variant = variantService.findById(saveForLaterDto.getVariantDto().getId());
        saveForLater.setCart(cart);
        saveForLater.setVariant(variant);
        return saveForLater;
    }

    @Override
    public SaveForLaterDTO convertEntityToDto(SaveForLater saveForLater) {
        SaveForLaterDTO saveForLaterDto = new SaveForLaterDTO();
        Long id = saveForLater.getId();
        int quantity = saveForLater.getQuanity();
        CartDTO cartDto = cartConverter.convertEntityToDto(saveForLater.getCart());
        VariantDTO variantDto = variantConverter.entityToDTO(saveForLater.getVariant());
        saveForLaterDto.setId(id);
        saveForLaterDto.setQuantity(quantity);
        saveForLaterDto.setCartDto(cartDto);
        saveForLaterDto.setVariantDto(variantDto);
        return saveForLaterDto;
    }

    @Override
    public List<SaveForLaterDTO> convertEntitiesToDtos(List<SaveForLater> saveForLaters) {
//        return saveForLaters.stream()
//                .map(this::convertEntityToDto)
//                .collect(Collectors.toList());

        List<SaveForLaterDTO> saveForLaterDTOList = new ArrayList<>();
        for (SaveForLater saveForLater: saveForLaters) {
            SaveForLaterDTO saveForLaterDto = convertEntityToDto(saveForLater);
            saveForLaterDTOList.add(saveForLaterDto);
        }
        return saveForLaterDTOList;
    }

    @Override
    public List<SaveForLater> convertDtoToEntities(List<SaveForLaterDTO> cartLineDtos) {
        return null;
    }
}

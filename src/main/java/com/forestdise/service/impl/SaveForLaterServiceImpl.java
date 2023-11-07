package com.forestdise.service.impl;

import com.forestdise.converter.SaveForLaterConverter;
import com.forestdise.dto.SaveForLaterDTO;
import com.forestdise.entity.Cart;
import com.forestdise.entity.SaveForLater;
import com.forestdise.entity.Variant;
import com.forestdise.payload.request.SaveForLaterRequest;
import com.forestdise.repository.CartRepository;
import com.forestdise.repository.SaveForLaterRepository;
import com.forestdise.repository.VariantRepository;
import com.forestdise.service.SaveForLaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SaveForLaterServiceImpl implements SaveForLaterService {
    private final CartRepository cartRepository;
    private final SaveForLaterRepository saveForLaterRepository;
    private final SaveForLaterConverter saveForLaterConverter;
    private final VariantRepository variantRepository;

    @Autowired
    public SaveForLaterServiceImpl(CartRepository cartRepository, SaveForLaterRepository saveForLaterRepository, SaveForLaterConverter saveForLaterConverter, VariantRepository variantRepository) {
        this.cartRepository = cartRepository;
        this.saveForLaterRepository = saveForLaterRepository;
        this.saveForLaterConverter = saveForLaterConverter;
        this.variantRepository = variantRepository;
    }

    @Override
    public List<SaveForLaterDTO> findSaveForLaterByCartId(Long cartId) {
        Cart cart = cartRepository.findById(cartId).orElse(null);
        List<SaveForLater> saveForLaters = saveForLaterRepository.findSaveForLaterByCart(cart);
        List<SaveForLaterDTO> saveForLaterDTOS = saveForLaterConverter.convertEntitiesToDtos(saveForLaters);
        return saveForLaterDTOS;
    }

    @Override
    public void removeSaveForLater(Long saveForLaterId) {
        saveForLaterRepository.deleteById(saveForLaterId);
    }

    @Override
    public SaveForLaterDTO addSaveForLater(SaveForLaterRequest saveForLaterRequest) {
        SaveForLater saveForLater = new SaveForLater();
        Cart cart = cartRepository.findById(saveForLaterRequest.getCartId()).orElse(null);
        Variant variant = variantRepository.findById(saveForLaterRequest.getVariantId()).orElse(null);
        saveForLater.setCart(cart);
        saveForLater.setVariant(variant);
        saveForLater.setQuanity(saveForLaterRequest.getQuantity());
        saveForLaterRepository.save(saveForLater);
        SaveForLaterDTO saveForLaterDto = saveForLaterConverter.convertEntityToDto(saveForLater);
        return saveForLaterDto;
    }
}

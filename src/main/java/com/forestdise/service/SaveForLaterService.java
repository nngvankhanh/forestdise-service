package com.forestdise.service;

import com.forestdise.dto.SaveForLaterDTO;
import com.forestdise.payload.request.SaveForLaterRequest;

import java.util.List;

public interface SaveForLaterService {
    List<SaveForLaterDTO> findSaveForLaterByCartId (Long cartId);
    void removeSaveForLater(Long saveForLaterId);
    SaveForLaterDTO addSaveForLater(SaveForLaterRequest saveForLaterRequest);
}

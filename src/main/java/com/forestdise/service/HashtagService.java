package com.forestdise.service;

import com.forestdise.entity.HashTag;

import java.util.List;

public interface HashtagService {
    List<HashTag> createHashtag(List<String> hashtagList, Long productId);
}

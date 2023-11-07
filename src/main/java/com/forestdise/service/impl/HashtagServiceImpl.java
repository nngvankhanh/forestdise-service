package com.forestdise.service.impl;

import com.forestdise.entity.HashTag;
import com.forestdise.entity.Product;
import com.forestdise.repository.HashtagRepository;
import com.forestdise.repository.ProductRepository;
import com.forestdise.service.HashtagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
@Service
public class HashtagServiceImpl implements HashtagService {
    @Autowired
    private HashtagRepository hashtagRepository;
    @Autowired
    private ProductRepository productRepository;
    @Override
    public List<HashTag> createHashtag(List<String> hashtagList, Long productId) {
        Product product = productRepository.findById(productId)
                .orElseThrow(()-> new EntityNotFoundException("Product is not found"));
        if(product != null){
            List<HashTag> hashTagList = new ArrayList<>();
            for(String element :hashtagList ){
                HashTag hashTag = new HashTag();
                hashTag.setName(element);
                hashTag.setProduct(product);
                hashtagRepository.save(hashTag);
                hashTagList.add(hashTag);
            }
        }
        return product.getHashTagList();
    }
}

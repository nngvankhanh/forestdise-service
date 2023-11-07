package com.forestdise.controller.sellingController;

import com.forestdise.entity.HashTag;
import com.forestdise.payload.response.HashtagResponse;
import com.forestdise.service.HashtagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin(origins = "https://forestdise.vercel.app")
@RequestMapping("/api/hashtag/{product_id}")
public class HashtagController {
    @Autowired
    private HashtagService hashtagService;
    @PostMapping
    public ResponseEntity<HashtagResponse> createHashtagListOfProduct(@PathVariable Long product_id, @RequestBody List<String> hashtagList){
        HashtagResponse hashtagResponse = new HashtagResponse();
         List<HashTag> hashTagList = hashtagService.createHashtag(hashtagList,product_id);
         if(hashTagList != null){
             hashtagResponse.setMessage("create hashtag list successful");
             return new ResponseEntity<>(hashtagResponse, HttpStatus.OK);
         } else {
             hashtagResponse.setMessage("fail to create hashtag for product");
             return new ResponseEntity<>(hashtagResponse,HttpStatus.BAD_REQUEST);
         }
    }
}

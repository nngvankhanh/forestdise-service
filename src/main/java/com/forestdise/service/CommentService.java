package com.forestdise.service;

import com.forestdise.dto.CommentDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {
    public List<CommentDTO> getCommentsByReviewId(Long reviewId);
}

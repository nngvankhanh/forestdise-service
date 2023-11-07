package com.forestdise.service.impl;

import com.forestdise.converter.CommentConverter;
import com.forestdise.dto.CommentDTO;
import com.forestdise.entity.Comment;
import com.forestdise.repository.CommentRepository;
import com.forestdise.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final CommentConverter commentConverter;


    public CommentServiceImpl(CommentRepository commentRepository, CommentConverter commentConverter) {
        this.commentRepository = commentRepository;
        this.commentConverter = commentConverter;
    }

    @Override
    public List<CommentDTO> getCommentsByReviewId(Long reviewId) {
        List<Comment> commentList = commentRepository.getCommentsByReviewId(reviewId);
        List<CommentDTO> commentDtoList = commentConverter.entitiesToDTOs(commentList);
        return commentDtoList;
    }
}

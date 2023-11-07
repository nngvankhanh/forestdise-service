package com.forestdise.converter;

import com.forestdise.dto.CommentDTO;
import com.forestdise.entity.Comment;

import java.util.List;

public interface CommentConverter {
    List<CommentDTO> entitiesToDTOs(List<Comment> element);
    CommentDTO entityToDTO(Comment element);
    Comment dtoToEntity(CommentDTO element);
}

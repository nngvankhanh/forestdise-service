package com.forestdise.payload.response;

import com.forestdise.dto.CommentDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {

    private List<CommentDTO> commentDTOList;
}

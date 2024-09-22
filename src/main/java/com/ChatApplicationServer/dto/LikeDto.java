package com.ChatApplicationServer.dto;

import lombok.Data;

@Data
public class LikeDto {

    private Long id;
    private Long userId;
    private Long postId;
}

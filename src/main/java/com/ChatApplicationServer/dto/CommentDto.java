package com.ChatApplicationServer.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CommentDto {

    private Long id;
    private Long userId;
    private Long postId;

    private String text;

    private String postedBy;
    private Date date;
}

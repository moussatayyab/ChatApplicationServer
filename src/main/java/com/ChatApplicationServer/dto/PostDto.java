package com.ChatApplicationServer.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Data
public class PostDto {

    private Long id;

    private String description;
    private MultipartFile img;
    private byte[] returnedImg;

    private Long viewCount;
    private Long likeCount;
    private Long commentCount;

    private Long userId;
    private Date date;
    private String postedBy;

    private boolean liked;
}

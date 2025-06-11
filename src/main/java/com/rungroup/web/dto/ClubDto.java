package com.rungroup.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class ClubDto {
    private long id;
    private String title;
    private String photourl;
    private String content;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}

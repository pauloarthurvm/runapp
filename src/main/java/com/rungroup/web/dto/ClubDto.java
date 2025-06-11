package com.rungroup.web.dto;

import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Builder
public class ClubDto {
    private long id;
    private String title;
    private String photourl;
    private String content;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
}

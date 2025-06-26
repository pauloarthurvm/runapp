package com.rungroup.web.dto;

import com.rungroup.web.models.Event;
import com.rungroup.web.models.UserEntity;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ClubDto {
    private Long id;
    @NotEmpty(message = "Club Title should not be empty")
    private String title;
    @NotEmpty(message = "Photo URL should not be empty")
    private String photoUrl;
    @NotEmpty(message = "Content should not be empty")
    private String content;
    private UserEntity createdBy;
    private LocalDateTime createdOn;
    private LocalDateTime updatedOn;
    private List<EventDto> eventDtoList;
}

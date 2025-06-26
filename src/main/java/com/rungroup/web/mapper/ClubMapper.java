package com.rungroup.web.mapper;

import com.rungroup.web.dto.ClubDto;
import com.rungroup.web.models.Club;

import java.util.stream.Collectors;

public class ClubMapper {

    public static Club mapToClub(ClubDto clubDto) {
        Club club = Club.builder()
                .id(clubDto.getId())
                .title(clubDto.getTitle())
                .photoUrl(clubDto.getPhotoUrl())
                .content(clubDto.getContent())
                .createdBy(clubDto.getCreatedBy())
                .createdOn(clubDto.getCreatedOn())
                .updatedOn(clubDto.getUpdatedOn())
                .build();
        return club;
    }

    public static ClubDto mapToClubDto(Club club) {
        ClubDto clubDto = ClubDto.builder()
                .id(club.getId())
                .title(club.getTitle())
                .photoUrl(club.getPhotoUrl())
                .content(club.getContent())
                .createdBy(club.getCreatedBy())
                .createdOn(club.getCreatedOn())
                .updatedOn(club.getUpdatedOn())
                .eventDtoList(club.getEvents()
                        .stream()
                        .map(EventMapper::mapToEventDto)
                        .collect(Collectors.toList()))
                .build();
        return clubDto;
    }

}

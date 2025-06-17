package com.rungroup.web.services;

import com.rungroup.web.dto.ClubDto;
import com.rungroup.web.models.Club;

import java.util.List;

public interface ClubService {
    List<ClubDto> findAllClubs();
    Club saveClub(ClubDto clubDto);

    ClubDto getClubById(long clubId);

    void updateClub(ClubDto clubDto);

    void deleteById(Long clubId);

    List<ClubDto> searchClubs(String query);
}

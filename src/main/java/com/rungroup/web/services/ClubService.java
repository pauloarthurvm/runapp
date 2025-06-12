package com.rungroup.web.services;

import com.rungroup.web.dto.ClubDto;

import java.util.List;

public interface ClubService {
    List<ClubDto> findAllClubs();
}

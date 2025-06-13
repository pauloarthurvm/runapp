package com.rungroup.web.services.impl;

import com.rungroup.web.dto.ClubDto;
import com.rungroup.web.models.Club;
import com.rungroup.web.repository.ClubRepository;
import com.rungroup.web.services.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreateServiceImpl implements ClubService {

    private ClubRepository clubRepository;

    @Autowired
    public CreateServiceImpl(ClubRepository clubRepository) {
        this.clubRepository = clubRepository;
    }

    @Override
    public List<ClubDto> findAllClubs() {
        List<Club> clubs = clubRepository.findAll();
        System.out.println(clubs);
        return clubs.stream().map((club) -> mapToClubDto(club)).collect(Collectors.toList());
    }

    @Override
    public Club saveClub(Club club) {
        return clubRepository.save(club);
    }

    private ClubDto mapToClubDto(Club club) {
        return new ClubDto(
                club.getId(),
                club.getTitle(),
                club.getPhotoUrl(),
                club.getContent(),
                club.getCreatedOn(),
                club.getUpdatedOn()
        );
    }
}

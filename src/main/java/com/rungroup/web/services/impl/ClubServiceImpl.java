package com.rungroup.web.services.impl;

import com.rungroup.web.dto.ClubDto;
import com.rungroup.web.mapper.ClubMapper;
import com.rungroup.web.models.Club;
import com.rungroup.web.models.UserEntity;
import com.rungroup.web.repository.ClubRepository;
import com.rungroup.web.repository.UserRepository;
import com.rungroup.web.security.SecurityUtil;
import com.rungroup.web.services.ClubService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static com.rungroup.web.mapper.ClubMapper.mapToClub;
import static com.rungroup.web.mapper.ClubMapper.mapToClubDto;

@Service
public class ClubServiceImpl implements ClubService {

    private ClubRepository clubRepository;
    private UserRepository userRepository;

    @Autowired
    public ClubServiceImpl(ClubRepository clubRepository,  UserRepository userRepository) {
        this.clubRepository = clubRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<ClubDto> findAllClubs() {
        List<Club> clubs = clubRepository.findAll();
        return clubs.stream().map((club) -> mapToClubDto(club)).collect(Collectors.toList());
    }

    @Override
    public Club saveClub(ClubDto clubDto) {
        String username = SecurityUtil.getSessionUser();
        UserEntity userEntity = userRepository.findByUsername(username);
        Club club = mapToClub(clubDto);
        club.setCreatedBy(userEntity);
        return clubRepository.save(club);
    }

    @Override
    public ClubDto getClubById(long clubId) {
        Club club = clubRepository.findById(clubId).get();
        return mapToClubDto(club);
    }

    @Override
    public void updateClub(ClubDto clubDto) {
        String username = SecurityUtil.getSessionUser();
        UserEntity userEntity = userRepository.findByUsername(username);
        Club club = mapToClub(clubDto);
        club.setCreatedBy(userEntity);
        clubRepository.save(club);
    }

    @Override
    public void deleteById(Long clubId) {
        clubRepository.deleteById(clubId);
    }

    @Override
    public List<ClubDto> searchClubs(String query) {
        List<Club> clubs = clubRepository.searchClubs(query);
        return clubs.stream().map(ClubMapper::mapToClubDto).collect(Collectors.toList());
    }

}

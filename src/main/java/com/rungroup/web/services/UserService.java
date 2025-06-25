package com.rungroup.web.services;

import com.rungroup.web.dto.RegistrationDto;
import com.rungroup.web.models.UserEntity;
import jakarta.validation.constraints.NotEmpty;

public interface UserService {

    void saveUser(RegistrationDto registrationDto);

    UserEntity findByEmail(String email);

    UserEntity findByUsername(String username);
}

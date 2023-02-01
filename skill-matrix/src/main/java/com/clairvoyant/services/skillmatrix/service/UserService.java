package com.clairvoyant.services.skillmatrix.service;

import com.clairvoyant.services.skillmatrix.dto.UserDto;
import com.clairvoyant.services.skillmatrix.dto.UserResponseDto;
import com.clairvoyant.services.skillmatrix.enums.Status;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public Status addOrUpdateUser(UserDto userDto);

    public UserResponseDto findById(String id,Optional<Boolean> isActive);

    public Status delete(String userId);

    public List<UserResponseDto> findAll(Optional<Boolean> isActive);
}

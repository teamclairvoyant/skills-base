package com.clairvoyant.clarise.service;

import com.clairvoyant.clarise.dto.UserDto;
import com.clairvoyant.clarise.dto.UserResponseDto;
import com.clairvoyant.clarise.enums.Status;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public Status addOrUpdateUser(UserDto userDto);

    public UserResponseDto findById(String id,Optional<Boolean> isActive);

    public Status delete(String userId);

    public List<UserResponseDto> findAll(Optional<Boolean> isActive);
}

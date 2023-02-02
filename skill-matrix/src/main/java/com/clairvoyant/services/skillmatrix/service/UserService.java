package com.clairvoyant.services.skillmatrix.service;

import com.clairvoyant.services.skillmatrix.dto.UserDto;
import com.clairvoyant.services.skillmatrix.dto.UserResponseDto;
import com.clairvoyant.services.skillmatrix.enums.Status;
import java.util.List;
import java.util.Optional;

public interface UserService {
    Status addOrUpdateUser(UserDto userDto);

    UserResponseDto findById(String id, Optional<Boolean> isActive);

    Status delete(String userId);

    List<UserResponseDto> findAll(Optional<Boolean> isActive);
}

package com.clairvoyant.clarise.service;

import com.clairvoyant.clarise.dto.UserDto;
import com.clairvoyant.clarise.dto.UserResponseDto;
import com.clairvoyant.clarise.enums.Status;
import com.clairvoyant.clarise.model.SkillsRating;
import com.clairvoyant.clarise.model.User;

import java.util.List;

public interface UserService {
    public UserResponseDto addOrUpdateUser(UserDto userDto);

    public UserResponseDto findById(String id);

    public Status delete(String userId);

    public List<UserResponseDto> findAll();
}

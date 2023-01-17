package com.clairvoyant.clarise.service.impl;

import com.clairvoyant.clarise.dto.UserCategoryDto;
import com.clairvoyant.clarise.dto.UserDto;
import com.clairvoyant.clarise.dto.UserRoleDto;
import com.clairvoyant.clarise.model.*;
import com.clairvoyant.clarise.repository.UserRepository;
import com.clairvoyant.clarise.repository.UserRoleRepository;
import com.clairvoyant.clarise.service.UserCategoryService;
import com.clairvoyant.clarise.service.UserDesignationService;
import com.clairvoyant.clarise.service.UserRoleService;
import com.clairvoyant.clarise.service.UserService;
import com.clairvoyant.clarise.util.PasswordUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    private UserRepository userRepository;

    private UserRoleService userRoleService;

    private UserCategoryService userCategoryService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserRoleService userRoleService, UserCategoryService userCategoryService) {
        this.userRepository = userRepository;
        this.userRoleService = userRoleService;
        this.userCategoryService = userCategoryService;
    }

    @Override
    public User addOrUpdateUser(UserDto userDto) {
        User user = new User();
        if (StringUtils.hasText(userDto.getId())) {
            Optional<User> result = userRepository.findById(userDto.getId());
            if (result.isPresent()) {
                if (StringUtils.hasLength(userDto.getName()))
                    result.get().setName(userDto.getName());
                if (StringUtils.hasLength(userDto.getEmailAddress()))
                    result.get().setEmailAddress(userDto.getEmailAddress());
                if (StringUtils.hasLength(userDto.getPassword()))
                    result.get().setPassword(PasswordUtil.encode(userDto.getPassword()));
                if (StringUtils.hasLength(userDto.getGrade()))
                    result.get().setGrade(userDto.getGrade());
                if (StringUtils.hasLength(userDto.getReportingManager()))
                    result.get().setReportingManager(userDto.getReportingManager());
            }
            user = result.get();
            userRepository.save(user);

        } else {
            BeanUtils.copyProperties(userDto, user);
            user.setActive(true);
            user.setPassword(PasswordUtil.encode(user.getPassword()));
            User savedUser = userRepository.save(user);
            UserRoleDto userRoleDto = new UserRoleDto();
            UserCategoryDto userCategoryDto = new UserCategoryDto();
            userRoleDto.setUserId(savedUser.getId());
            userCategoryDto.setUserId(savedUser.getId());
            userRoleDto.setRoleIds(userDto.getUserRoleIds());
            userCategoryDto.setCategoryIds(userDto.getUserCategoryIds());
            userRoleService.addOrUpdateUserRole(userRoleDto);
            userCategoryService.addOrUpdateUserCategory(userCategoryDto);
        }
        return user;
    }

    @Override
    public UserDto findById(String id) {
        Optional<User> result = userRepository.findByIdAndIsActive(id, true);
        UserDto userDto = new UserDto();
        BeanUtils.copyProperties(result.get(), userDto);
        List<String> userRoleIds = userRoleService.findRoleIdByUserId(id);
        userDto.setUserRoleIds(userRoleIds);
        List<String> userCategoryIds = userCategoryService.findCategoryIdByUserId(id);
        userDto.setUserCategoryIds(userCategoryIds);
        return userDto;
    }

    @Override
    public void delete(String userId) {

        Optional<User> optUser = userRepository.findById(userId);
        if (optUser.isPresent()) {
            User user = optUser.get();
            user.setActive(false);
            userRepository.save(user);
        }
    }

    @Override
    public List<UserDto> findAll() {
        List<User> users = userRepository.findByIsActive(true);
        List<UserDto> userDtos = new ArrayList<>();
        List<UserRoleMapping> userRoleMappings = userRoleService.findAllUserRoleMapping();
        List<UserCategoryMapping> userCategoryMappings = userCategoryService.findAllUserCategoryMapping();
        for (User user : users) {
            UserDto userDto = new UserDto();
            BeanUtils.copyProperties(user, userDto);
            List<String> roleIds = new ArrayList<>();
            for (UserRoleMapping userRoleMapping : userRoleMappings) {
                if (user.getId().equals(userRoleMapping.getUser().getId())) {
                    Role role = userRoleMapping.getRoles();
                    String id = role.getId();
                    roleIds.add(id);
                }
            }
            List<String> categoryIds = new ArrayList<>();
            for (UserCategoryMapping userCategoryMapping : userCategoryMappings) {
                if (user.getId().equals(userCategoryMapping.getUser().getId())) {
                    Category category = userCategoryMapping.getCategory();
                    String id = category.getId();
                    categoryIds.add(id);
                }
            }
            userDto.setUserRoleIds(roleIds);
            userDto.setUserCategoryIds(categoryIds);
            userDtos.add(userDto);
        }
        return userDtos;
    }
}

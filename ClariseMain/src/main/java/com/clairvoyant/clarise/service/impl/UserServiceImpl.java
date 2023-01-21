package com.clairvoyant.clarise.service.impl;

import com.clairvoyant.clarise.dto.*;
import com.clairvoyant.clarise.enums.Status;
import com.clairvoyant.clarise.exceptions.ResourceNotFoundException;
import com.clairvoyant.clarise.model.*;
import com.clairvoyant.clarise.repository.UserRepository;
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
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService {


    private UserRepository userRepository;

    private UserRoleService userRoleService;

    private UserCategoryService userCategoryService;

    private UserDesignationService userDesignationService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,UserDesignationService userDesignationService,UserRoleService userRoleService, UserCategoryService userCategoryService) {
        this.userRepository = userRepository;
        this.userRoleService = userRoleService;
        this.userCategoryService = userCategoryService;
        this.userDesignationService=userDesignationService;
    }

    @Override
    public UserResponseDto addOrUpdateUser(UserDto userDto) {
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
            userDesignationService.addOrUpdateUserDesignation(
                    UserDesignationDto
                            .builder()
                            .userId(savedUser.getId())
                            .designationId(userDto.getUserDesignationId())
                            .build()
            );
            userRoleService.addOrUpdateUserRole(
                    UserRoleDto
                            .builder()
                            .userId(savedUser.getId())
                            .roleIds(userDto.getUserRoleIds())
                            .build()
            );
            userCategoryService.addOrUpdateUserCategory(
                    UserCategoryDto
                            .builder()
                            .userId(savedUser.getId())
                            .categoryIds(userDto.getUserCategoryIds())
                            .build()
            );
        }
        UserResponseDto userResponseDto = findById(user.getId());
        return userResponseDto;
    }

    @Override
    public UserResponseDto findById(String id) {
        Optional<User> result = userRepository.findByIdAndIsActive(id, true);
        if (result.isEmpty()){
            throw new ResourceNotFoundException("User not found");
        }
        UserResponseDto userResponseDto = new UserResponseDto();
        BeanUtils.copyProperties(result.get(), userResponseDto);
        UserDesignationMapping userDesignationMapping = userDesignationService.findUserDesignationMappingByUserId(id);
        Designation designation = userDesignationMapping.getDesignation();
        userResponseDto.setDesignation(designation);
        Optional<List<UserRoleMapping>> userRoles = userRoleService.findUserRoleMappingByUserId(id);
        List<Role> roles = new ArrayList<>();
        for (UserRoleMapping userRoleMapping : userRoles.get()){
            Role role = userRoleMapping.getRoles();
            roles.add(role);
        }
        userResponseDto.setUserRoles(roles);

        Optional<List<UserCategoryMapping>> userCategories = userCategoryService.findUserCategoryMappingByUserId(id);
        List<Category> categories = new ArrayList<>();
        for (UserCategoryMapping userCategoryMapping : userCategories.get()){
            Category category = userCategoryMapping.getCategory();
            categories.add(category);
        }
        userResponseDto.setUserCategories(categories);
        return userResponseDto;
    }

    @Override
    public Status delete(String userId) {

        Optional<User> optUser = userRepository.findById(userId);
        if (optUser.isPresent()) {
            User user = optUser.get();
            user.setActive(false);
            userRepository.save(user);
            return Status.SUCCESS;
        }else {
            throw new ResourceNotFoundException("User not found");
        }
    }

    @Override
    public List<UserResponseDto> findAll() {
        List<User> users = userRepository.findByIsActive(true);
        List<UserResponseDto> userResponse = new ArrayList<>();
        List<UserDesignationMapping> userDesignationMappings = userDesignationService.findAllUserDesignationMappings();
        List<UserRoleMapping> userRoleMappings = userRoleService.findAllUserRoleMapping();
        List<UserCategoryMapping> userCategoryMappings = userCategoryService.findAllUserCategoryMapping();
        for (User user : users) {
            UserResponseDto userResponseDto = new UserResponseDto();
            BeanUtils.copyProperties(user, userResponseDto);

            Designation designation = userDesignationMappings.stream()
                    .filter(userDesignationMapping -> user.getId().equals(userDesignationMapping.getUser().getId()))
                    .map(userDesignationMapping -> userDesignationMapping.getDesignation()).findFirst().get();

            List<Role> roles = userRoleMappings.stream()
                    .filter(userRoleMapping -> user.getId().equals(userRoleMapping.getUser().getId()))
                    .map(userRoleMapping -> userRoleMapping.getRoles()).collect(Collectors.toList());

            List<Category> categories = userCategoryMappings.stream()
                    .filter(userCategoryMapping -> user.getId().equals(userCategoryMapping.getUser().getId()))
                    .map(userCategoryMapping -> userCategoryMapping.getCategory()).collect(Collectors.toList());

            userResponseDto.setDesignation(designation);
            userResponseDto.setUserRoles(roles);
            userResponseDto.setUserCategories(categories);
            userResponse.add(userResponseDto);
        }
        return userResponse;
    }
}

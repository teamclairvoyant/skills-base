package com.clairvoyant.services.skillmatrix.service.impl;

import com.clairvoyant.services.skillmatrix.dto.UserCategoryDto;
import com.clairvoyant.services.skillmatrix.dto.UserDesignationDto;
import com.clairvoyant.services.skillmatrix.dto.UserDto;
import com.clairvoyant.services.skillmatrix.dto.UserResponseDto;
import com.clairvoyant.services.skillmatrix.dto.UserRoleDto;
import com.clairvoyant.services.skillmatrix.enums.Status;
import com.clairvoyant.services.skillmatrix.exceptions.ResourceNotFoundException;
import com.clairvoyant.services.skillmatrix.model.Category;
import com.clairvoyant.services.skillmatrix.model.Designation;
import com.clairvoyant.services.skillmatrix.model.Role;
import com.clairvoyant.services.skillmatrix.model.User;
import com.clairvoyant.services.skillmatrix.model.UserCategoryMapping;
import com.clairvoyant.services.skillmatrix.model.UserDesignationMapping;
import com.clairvoyant.services.skillmatrix.model.UserRoleMapping;
import com.clairvoyant.services.skillmatrix.repository.UserRepository;
import com.clairvoyant.services.skillmatrix.service.UserCategoryService;
import com.clairvoyant.services.skillmatrix.service.UserDesignationService;
import com.clairvoyant.services.skillmatrix.service.UserRoleService;
import com.clairvoyant.services.skillmatrix.service.UserService;
import com.clairvoyant.services.skillmatrix.util.PasswordUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.google.common.collect.Sets;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    private final UserRoleService userRoleService;

    private final UserCategoryService userCategoryService;

    private final UserDesignationService userDesignationService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserDesignationService userDesignationService, UserRoleService userRoleService,
                           UserCategoryService userCategoryService) {
        this.userRepository = userRepository;
        this.userRoleService = userRoleService;
        this.userCategoryService = userCategoryService;
        this.userDesignationService = userDesignationService;
    }

    @Override
    public Status addOrUpdateUser(UserDto userDto) {
        User user = new User();
        if (StringUtils.hasText(userDto.getId())) {
            Optional<User> result = userRepository.findById(userDto.getId());
            if (result.isPresent()) {
                if (StringUtils.hasLength(userDto.getName())) {
                    result.get().setName(userDto.getName());
                }
                if (StringUtils.hasLength(userDto.getEmailAddress())) {
                    result.get().setEmailAddress(userDto.getEmailAddress());
                }
                if (StringUtils.hasLength(userDto.getPassword())) {
                    result.get().setPassword(PasswordUtil.encode(userDto.getPassword()));
                }
                if (StringUtils.hasLength(userDto.getGrade())) {
                    result.get().setGrade(userDto.getGrade());
                }
                if (!userDto.getUserDesignationId().equals(
                        userDesignationService.findUserDesignationMappingByUserIdAndIsActive(userDto.getId(),true)
                                .getDesignation().getId())){
                    userDesignationService.addOrUpdateUserDesignation(
                            UserDesignationDto.builder()
                                    .userId(userDto.getId())
                                    .designationId(userDto.getUserDesignationId())
                                    .build()
                    );
                }
                List<String> difference = new ArrayList<>(Sets.difference(
                        Sets.newHashSet(userDto.getUserRoleIds()),
                        Sets.newHashSet(userRoleService
                                .findUserRoleMappingByUserIdAndIsActive(userDto.getId(), true)
                                .get().stream()
                                .map(UserRoleMapping::getRoles).collect(Collectors.toList()))));
                if (!difference.isEmpty()){
                    userRoleService.addOrUpdateUserRole(
                            UserRoleDto.builder()
                                    .userId(userDto.getId())
                                    .roleIds(userDto.getUserRoleIds())
                                    .build()
                    );
                }
                List<String> difference1 = new ArrayList<>(Sets.difference(
                        Sets.newHashSet(userDto.getUserCategoryIds()),
                        Sets.newHashSet(userCategoryService
                                .findUserCategoryMappingByUserIdAndIsActive(userDto.getId(), true)
                                .get().stream()
                                .map(UserCategoryMapping::getCategory).collect(Collectors.toList()))));
                if (!difference1.isEmpty()){
                    userCategoryService.addOrUpdateUserCategory(
                            UserCategoryDto.builder()
                                    .userId(userDto.getId())
                                    .categoryIds(userDto.getUserCategoryIds())
                                    .build()
                    );
                }
            }else {
                throw new ResourceNotFoundException("User not found");
            }
            try {
                user = result.get();
                userRepository.save(user);
                return Status.SUCCESS;
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            try {
                BeanUtils.copyProperties(userDto, user);
                if (StringUtils.hasText(userDto.getReportingManager())) {
                    Optional<User> reportingManager = userRepository.findById(userDto.getReportingManager());
                    if (reportingManager.isPresent()) {
                        user.setReportingManager(reportingManager.get());
                    } else {
                        throw new ResourceNotFoundException("Reporting Manager with ID : " + userDto.getReportingManager() + " is not present.");
                    }
                }
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
                return Status.SUCCESS;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return Status.SOMETHING_WENT_WRONG;
    }

    @Override
    public UserResponseDto findById(String id, Optional<Boolean> isActive) {
        Optional<User> result;
        if (isActive.isPresent()) {
            result = userRepository.findByIdAndIsActive(id, isActive.get());
        } else {
            result = userRepository.findByIdAndIsActive(id, true);
        }
        if (result.isEmpty()) {
            throw new ResourceNotFoundException("User not found");
        }
        UserResponseDto userResponseDto = new UserResponseDto();
        BeanUtils.copyProperties(result.get(), userResponseDto);
        UserDesignationMapping userDesignationMapping = userDesignationService.findUserDesignationMappingByUserIdAndIsActive(id,true);
        Designation designation = userDesignationMapping.getDesignation();
        userResponseDto.setDesignation(designation);
        Optional<List<UserRoleMapping>> userRoles = userRoleService.findUserRoleMappingByUserIdAndIsActive(id,true);
        List<Role> roles = new ArrayList<>();
        for (UserRoleMapping userRoleMapping : userRoles.get()) {
            Role role = userRoleMapping.getRoles();
            roles.add(role);
        }
        userResponseDto.setUserRoles(roles);

        Optional<List<UserCategoryMapping>> userCategories = userCategoryService.findUserCategoryMappingByUserIdAndIsActive(id,true);
        List<Category> categories = new ArrayList<>();
        for (UserCategoryMapping userCategoryMapping : userCategories.get()) {
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
            try {
                User user = optUser.get();
                user.setActive(false);
                userRepository.save(user);
                return Status.SUCCESS;
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            throw new ResourceNotFoundException("User not found");
        }
        return Status.SOMETHING_WENT_WRONG;
    }

    @Override
    public List<UserResponseDto> findAll(Optional<Boolean> isActive) {
        List<User> users;
        if (isActive.isPresent()) {
            users = userRepository.findByIsActive(isActive.get());
        } else {
            users = userRepository.findByIsActive(true);
        }
        List<UserResponseDto> userResponse = new ArrayList<>();
        List<UserDesignationMapping> userDesignationMappings = userDesignationService.findAllUserDesignationMappings();
        List<UserRoleMapping> userRoleMappings = userRoleService.findAllUserRoleMapping();
        List<UserCategoryMapping> userCategoryMappings = userCategoryService.findAllUserCategoryMapping();
        for (User user : users) {
            UserResponseDto userResponseDto = new UserResponseDto();
            BeanUtils.copyProperties(user, userResponseDto);

            Designation designation = userDesignationMappings.stream()
                .filter(userDesignationMapping -> user.getId().equals(userDesignationMapping.getUser().getId()))
                .map(UserDesignationMapping::getDesignation).findFirst().get();

            List<Role> roles = userRoleMappings.stream()
                .filter(userRoleMapping -> user.getId().equals(userRoleMapping.getUser().getId()))
                .map(UserRoleMapping::getRoles).collect(Collectors.toList());

            List<Category> categories = userCategoryMappings.stream()
                .filter(userCategoryMapping -> user.getId().equals(userCategoryMapping.getUser().getId()))
                .map(UserCategoryMapping::getCategory).collect(Collectors.toList());

            userResponseDto.setDesignation(designation);
            userResponseDto.setUserRoles(roles);
            userResponseDto.setUserCategories(categories);
            userResponse.add(userResponseDto);
        }
        return userResponse;
    }
}

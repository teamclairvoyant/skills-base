package com.clairvoyant.clarise.service.impl;

import com.clairvoyant.clarise.dto.*;
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

            UserDesignationDto userDesignationDto = new UserDesignationDto();
            String userDesignationId=userDto.getUserDesignationId();
            userDesignationDto.setUserId(savedUser.getId());
            userDesignationDto.setDesignationId(userDesignationId);

            UserRoleDto userRoleDto = new UserRoleDto();
            userRoleDto.setUserId(savedUser.getId());
            userRoleDto.setRoleIds(userDto.getUserRoleIds());

            UserCategoryDto userCategoryDto = new UserCategoryDto();
            userCategoryDto.setUserId(savedUser.getId());
            userCategoryDto.setCategoryIds(userDto.getUserCategoryIds());

            userDesignationService.addOrUpdateUserDesignation(userDesignationDto);
            userRoleService.addOrUpdateUserRole(userRoleDto);
            userCategoryService.addOrUpdateUserCategory(userCategoryDto);
        }
        return user;
    }

    @Override
    public UserResponseDto findById(String id) {
        Optional<User> result = userRepository.findByIdAndIsActive(id, true);
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
    public void delete(String userId) {

        Optional<User> optUser = userRepository.findById(userId);
        if (optUser.isPresent()) {
            User user = optUser.get();
            user.setActive(false);
            userRepository.save(user);
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

            Designation designation = new Designation();
            for (UserDesignationMapping userDesignationMapping:userDesignationMappings) {
                if (user.getId().equals(userDesignationMapping.getUser().getId())){
                    designation = userDesignationMapping.getDesignation();
                }
            }

            List<Role> roles = new ArrayList<>();
            for (UserRoleMapping userRoleMapping : userRoleMappings) {
                if (user.getId().equals(userRoleMapping.getUser().getId())) {
                    Role role = userRoleMapping.getRoles();
                    roles.add(role);
                }
            }
            List<Category> categories = new ArrayList<>();
            for (UserCategoryMapping userCategoryMapping : userCategoryMappings) {
                if (user.getId().equals(userCategoryMapping.getUser().getId())) {
                    Category category = userCategoryMapping.getCategory();
                    categories.add(category);
                }
            }
            userResponseDto.setDesignation(designation);
            userResponseDto.setUserRoles(roles);
            userResponseDto.setUserCategories(categories);
            userResponse.add(userResponseDto);
        }
        return userResponse;
    }
}

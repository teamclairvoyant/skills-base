package com.clairvoyant.services.skillmatrix.service.impl;

import com.clairvoyant.services.skillmatrix.dto.UserCategoryDto;
import com.clairvoyant.services.skillmatrix.dto.UserDesignationDto;
import com.clairvoyant.services.skillmatrix.dto.UserDto;
import com.clairvoyant.services.skillmatrix.dto.UserResponseDto;
import com.clairvoyant.services.skillmatrix.dto.UserRoleDto;
import com.clairvoyant.services.skillmatrix.dto.UserSearchQuery;
import com.clairvoyant.services.skillmatrix.dto.UserSearchResponseDto;
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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
        var user = new User();
        if (StringUtils.hasText(userDto.getId())) {
            Optional<User> result = userRepository.findById(userDto.getId());
            if (result.isPresent()) {
                result.get().setName(userDto.getName());
                result.get().setEmailAddress(userDto.getEmailAddress());
                result.get().setPassword(PasswordUtil.encode(userDto.getPassword()));
                result.get().setGrade(userDto.getGrade());

                user = result.get();
                userRepository.save(user);
                return Status.SUCCESS;
            } else {
                throw new ResourceNotFoundException("User does not exist with Id : " + userDto.getId());
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
                var savedUser = userRepository.save(user);
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
        var userResponseDto = new UserResponseDto();
        BeanUtils.copyProperties(result.get(), userResponseDto);
        var userDesignationMapping = userDesignationService.findUserDesignationMappingByUserId(id);
        var designation = userDesignationMapping.getDesignation();
        userResponseDto.setDesignation(designation);
        Optional<List<UserRoleMapping>> userRoles = userRoleService.findUserRoleMappingByUserId(id);
        List<Role> roles = new ArrayList<>();

        if (userRoles.isPresent()) {
            for (UserRoleMapping userRoleMapping : userRoles.get()) {
                var role = userRoleMapping.getRoles();
                roles.add(role);
            }
        } else {
            throw new ResourceNotFoundException("Roles is not available for user : " + result.get().getId());
        }
        userResponseDto.setUserRoles(roles);

        Optional<List<UserCategoryMapping>> userCategories = userCategoryService.findUserCategoryMappingByUserId(id);
        List<Category> categories = new ArrayList<>();

        if (userCategories.isPresent()) {

            for (UserCategoryMapping userCategoryMapping : userCategories.get()) {
                var category = userCategoryMapping.getCategory();
                categories.add(category);
            }
        } else {
            throw new ResourceNotFoundException("Categories are not available for user : " + result.get().getId());
        }

        userResponseDto.setUserCategories(categories);
        return userResponseDto;
    }

    /**
     * @param page    - It is a page number on which data should be paginated.
     * @param size    - Total number of records to be displayed on particular page
     * @param queries - Contains parameter name and value to be searched .
     * @return SearchUserResponse - It contains info about total number of records , current page
     *                              UserResponse , total No of pages .
     */
    @Override
    public UserSearchResponseDto search(int page, int size, List<UserSearchQuery> queries) {
        int pageNo = page-1;
        Pageable pageable = PageRequest.of(pageNo,size, Sort.Direction.ASC, "name");
        var wrapper = new Object() {
            String name = "";
            String email = "";
        };
        queries.stream().forEach(q -> {
            if ("name".equals(q.getColumn())) {
                wrapper.name = q.getValue();
            } else if ("email".equals(q.getColumn())) {
                wrapper.email = q.getValue();
            }
        });
        Page<User> searchedUser = userRepository.search(wrapper.name, wrapper.email,pageable);
        List<User> pagedUser = searchedUser.getContent().stream().distinct().collect(Collectors.toList());
        List<UserResponseDto> userResponseDtoList = getUserMappings(pagedUser);

        var userSearchResponseDto = new UserSearchResponseDto();
        userSearchResponseDto.setCurrentPage(searchedUser.getNumber()+1);
        userSearchResponseDto.setTotalPages(searchedUser.getTotalPages());
        userSearchResponseDto.setUserResponseDto(userResponseDtoList);
        userSearchResponseDto.setTotalCount(searchedUser.getTotalElements());

        return userSearchResponseDto;
    }

    @Override
    public Status delete(String userId) {
        Optional<User> optUser = userRepository.findById(userId);
        if (optUser.isPresent()) {
            try {
                var user = optUser.get();
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

        return getUserMappings(users);
    }

    private List<UserResponseDto> getUserMappings(List<User> users) {

        List<UserResponseDto> userResponse = new ArrayList<>();
        List<UserDesignationMapping> userDesignationMappings = userDesignationService.findAllUserDesignationMappings();
        List<UserRoleMapping> userRoleMappings = userRoleService.findAllUserRoleMapping();
        List<UserCategoryMapping> userCategoryMappings = userCategoryService.findAllUserCategoryMapping();
        for (User user : users) {
            var userResponseDto = new UserResponseDto();
            BeanUtils.copyProperties(user, userResponseDto);

            Designation designation;
            Optional<Designation> optionalDesignation = userDesignationMappings.stream()
                    .filter(userDesignationMapping -> user.getId().equals(userDesignationMapping.getUser().getId()))
                    .map(UserDesignationMapping::getDesignation).findFirst();
            if (optionalDesignation.isPresent()) {
                designation = optionalDesignation.get();
            } else {
                throw new ResourceNotFoundException("Designation is not mapped for User Id" + user.getId());
            }

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

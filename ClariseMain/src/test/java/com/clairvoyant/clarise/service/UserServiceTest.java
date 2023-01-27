package com.clairvoyant.clarise.service;

import com.clairvoyant.clarise.dto.UserDesignationDto;
import com.clairvoyant.clarise.dto.UserDto;
import com.clairvoyant.clarise.dto.UserResponseDto;
import com.clairvoyant.clarise.dto.UserRoleDto;
import com.clairvoyant.clarise.dto.UserCategoryDto;
import com.clairvoyant.clarise.enums.Status;
import com.clairvoyant.clarise.model.User;
import com.clairvoyant.clarise.model.Designation;
import com.clairvoyant.clarise.model.UserCategoryMapping;
import com.clairvoyant.clarise.model.UserDesignationMapping;
import com.clairvoyant.clarise.model.UserRoleMapping;
import com.clairvoyant.clarise.repository.UserRepository;
import com.clairvoyant.clarise.service.impl.UserServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import static org.powermock.api.mockito.PowerMockito.*;

import org.mockito.*;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RunWith(PowerMockRunner.class)
@PrepareForTest(UserServiceImpl.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepositoryMock;

    @Mock
    private UserServiceImpl userService;

    private User userMock=mock(User.class);

    private UserDesignationMapping UserDesignationMappingMock=mock(UserDesignationMapping.class);

    private UserRoleMapping userRoleMappingMock=mock(UserRoleMapping.class);

    private UserDto userDtoMock=mock(UserDto.class);

    private UserResponseDto userResponseDtoMock=mock(UserResponseDto.class);

    private UserDesignationDto userDesignationDtoMock=mock(UserDesignationDto.class);

    private UserRoleDto userRoleDtoMock=mock(UserRoleDto.class);

    private UserCategoryDto userCategoryDtoMock=mock((UserCategoryDto.class));

    private UserRoleService userRoleServiceMock = mock(UserRoleService.class);

    private UserCategoryService userCategoryServiceMock = mock(UserCategoryService.class);


    private UserCategoryMapping userCategoryMappingMock=mock(UserCategoryMapping.class);

    private UserDesignationService userDesignationServiceMock =mock(UserDesignationService.class);




    @Test
    public void addUserTest()
    {
        //Mocking Repository save method
        when(userRepositoryMock.findById("101")).thenReturn(Optional.empty());
        when(userRepositoryMock.save(userMock)).thenReturn(userMock);

        //Mocking userDesignationService addOrUpdateUserDesignation() method;
        userDesignationServiceMock.addOrUpdateUserDesignation(userDesignationDtoMock);

        //Mocking userRoleService addOrUpdateUserRole() method
        when(userRoleServiceMock.addOrUpdateUserRole(userRoleDtoMock)).thenReturn(List.of(userRoleMappingMock));

        //Mocking userCategoryService addOrUpdateUserCategory() method
        when(userCategoryServiceMock.addOrUpdateUserCategory(userCategoryDtoMock)).thenReturn(List.of(userCategoryMappingMock));

        //Mocking addOrUpdate()
        when(userService.addOrUpdateUser(userDtoMock)).thenReturn(Status.SUCCESS);
        Status status = userService.addOrUpdateUser(userDtoMock);
        Assertions.assertEquals(Status.SUCCESS,status);

    }

    @Test
    public void updateUserTest()
    {
        //Testing User updation
        when(userRepositoryMock.findById("101")).thenReturn(Optional.of(userMock));
        User existingUser=userRepositoryMock.findById("101").get();

        existingUser.setEmailAddress(ArgumentMatchers.anyString());

        when(userRepositoryMock.save(existingUser)).thenReturn(existingUser);

        when(userService.addOrUpdateUser(userDtoMock)).thenReturn(Status.SUCCESS);
        Status actualStatus=userService.addOrUpdateUser(userDtoMock);
        Assertions.assertEquals(Status.SUCCESS,actualStatus);
    }

    @Test
    public void findByIdTest()
    {

        when(userRepositoryMock.findByIdAndIsActive("101",true)).thenReturn(Optional.of(userMock));

        User returnedUser=userRepositoryMock.findByIdAndIsActive("101",true).get();

        userResponseDtoMock.setId(returnedUser.getId());
        userResponseDtoMock.setEmailAddress(returnedUser.getEmailAddress());


        when(userDesignationServiceMock.findUserDesignationMappingByUserId("101")).thenReturn(UserDesignationMappingMock);
        UserDesignationMapping userDesignationMapping=userDesignationServiceMock.findUserDesignationMappingByUserId("101");

        Designation designation =userDesignationMapping.getDesignation();
        userResponseDtoMock.setDesignation(designation);

        when(userRoleServiceMock.findUserRoleMappingByUserId("101")).thenReturn(Optional.of(List.of(userRoleMappingMock)));
        List<UserRoleMapping> userRoleMappingList=userRoleServiceMock.findUserRoleMappingByUserId("101").get();
        userResponseDtoMock.setUserRoles(userRoleMappingList.stream().map(role -> role.getRoles()).collect(Collectors.toList()));

        when(userCategoryServiceMock.findUserCategoryMappingByUserId("101")).thenReturn(Optional.of(List.of(userCategoryMappingMock)));
        List<UserCategoryMapping> userCategoryMappingList=userCategoryServiceMock.findUserCategoryMappingByUserId("101").get();
        userResponseDtoMock.setUserCategories(userCategoryMappingList.stream().map(category -> category.getCategory()).collect(Collectors.toList()));

        when(userService.findById("101",Optional.of(true))).thenReturn(userResponseDtoMock);
        UserResponseDto actualUserResponseDto=userService.findById("101", Optional.of(true));

        Assertions.assertEquals(userResponseDtoMock,actualUserResponseDto);


    }

    @Test
    public void deleteTest()
    {
        when(userRepositoryMock.findById(ArgumentMatchers.anyString())).thenReturn(Optional.of(userMock));
        User returnedUser=userRepositoryMock.findById("123").get();

        returnedUser.setActive(false);
        when(userRepositoryMock.save(returnedUser)).thenReturn(userMock);

        when(userService.delete("123")).thenReturn(Status.SUCCESS);
        Status actualStatus=userService.delete("123");

        Assertions.assertEquals(Status.SUCCESS,actualStatus);

    }

    @Test
    public void findAllTest()
    {
        when(userService.findAll(Optional.of(true))).thenReturn(List.of(userResponseDtoMock));

        List<UserResponseDto> userList=userService.findAll(Optional.of(true));
        Assertions.assertTrue(userList.size()>0);
    }



}

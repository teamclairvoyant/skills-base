package com.clairvoyant.clarise.service;

import com.clairvoyant.clarise.dto.UserCategoryDto;
import com.clairvoyant.clarise.dto.UserRoleDto;
import com.clairvoyant.clarise.model.UserCategoryMapping;
import com.clairvoyant.clarise.model.UserRoleMapping;

import java.util.List;

public interface UserCategoryService {

    List<UserCategoryMapping> addOrUpdateUserCategory(UserCategoryDto userCategoryDto);

    List<UserCategoryMapping> findAllUserCategoryMapping();

    List<String> findCategoryIdByUserId(String id);
}

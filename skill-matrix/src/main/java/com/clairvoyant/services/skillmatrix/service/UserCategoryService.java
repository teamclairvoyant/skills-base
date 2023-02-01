package com.clairvoyant.services.skillmatrix.service;

import com.clairvoyant.services.skillmatrix.dto.UserCategoryDto;
import com.clairvoyant.services.skillmatrix.model.UserCategoryMapping;

import java.util.List;
import java.util.Optional;

public interface UserCategoryService {

    List<UserCategoryMapping> addOrUpdateUserCategory(UserCategoryDto userCategoryDto);

    List<UserCategoryMapping> findAllUserCategoryMapping();

    Optional<List<UserCategoryMapping>> findUserCategoryMappingByUserId(String id);
}

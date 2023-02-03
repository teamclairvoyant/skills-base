package com.clairvoyant.services.skillmatrix.service.impl;

import com.clairvoyant.services.skillmatrix.dto.UserCategoryDto;
import com.clairvoyant.services.skillmatrix.model.Category;
import com.clairvoyant.services.skillmatrix.model.User;
import com.clairvoyant.services.skillmatrix.model.UserCategoryMapping;
import com.clairvoyant.services.skillmatrix.repository.UserCategoryRepository;
import com.clairvoyant.services.skillmatrix.service.UserCategoryService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCategoryServiceImpl implements UserCategoryService {

    @Autowired
    private UserCategoryRepository userCategoryRepository;

    @Override
    public List<UserCategoryMapping> addOrUpdateUserCategory(UserCategoryDto userCategoryDto) {
        Optional<List<UserCategoryMapping>> userCategoryMapping = userCategoryRepository.findByUserId(userCategoryDto.getUserId());

        List<UserCategoryMapping> userCategoryMappings = new ArrayList<>();
        if (userCategoryMapping.get().isEmpty()) {
            //Insert Categorys for the first time
            newUserCategoryMapping(userCategoryDto.getUserId(), userCategoryDto.getCategoryIds(), userCategoryMappings);
        } else {
            List<String> dbCategoryIds = userCategoryMapping.get().stream().map(userCategory -> userCategory.getCategory().getId()).collect(Collectors.toList());
            List<String> dbActiveCategoryIds = userCategoryMapping.get().stream().filter(UserCategoryMapping::isActive)
                    .map(userCategory -> userCategory.getCategory().getId()).collect(Collectors.toList());

            //Insert new Category for the category -- create
            List<String> newCategoryIds = new ArrayList<>(Sets.difference(Sets.newHashSet(userCategoryDto.getCategoryIds()), Sets.newHashSet(dbCategoryIds)));
            newUserCategoryMapping(userCategoryDto.getUserId(), newCategoryIds, userCategoryMappings);

            //removing newly inserted skillIds from request so that newly inserted ids will not come while updating in the below
            userCategoryDto.getCategoryIds().removeIf(newCategoryIds::contains);


            //update to inactive for existing mappings -- delete
            List<String> deletedCategoryIds = new ArrayList<>(Sets.difference(Sets.newHashSet(dbActiveCategoryIds), Sets.newHashSet(userCategoryDto.getCategoryIds())));
            for (String CategoryId : deletedCategoryIds) {
                UserCategoryMapping CategoryMapping =
                        userCategoryMapping.get().stream().filter(urm -> CategoryId.equals(urm.getCategory().getId())).findFirst().get();
                CategoryMapping.setActive(false);
                userCategoryMappings.add(CategoryMapping);
            }

            //update existing inactive mapping to true -- update
            List<String> updateCategoryIds = new ArrayList<>(Sets.difference(Sets.newHashSet(userCategoryDto.getCategoryIds()), Sets.newHashSet(dbActiveCategoryIds)));
            for (String CategoryId : updateCategoryIds) {
                UserCategoryMapping CategoryMapping =
                        userCategoryMapping.get().stream().filter(urm -> CategoryId.equals(urm.getCategory().getId())).findFirst().get();
                CategoryMapping.setActive(true);
                userCategoryMappings.add(CategoryMapping);
            }

        }
        if (Objects.nonNull(userCategoryMappings) && userCategoryMappings.size() > 0) {
            return userCategoryRepository.saveAll(userCategoryMappings);
        }
        return null;
    }

    @Override
    public List<UserCategoryMapping> findAllUserCategoryMapping() {
        return userCategoryRepository.findByIsActive(true);
    }

    @Override
    public Optional<List<UserCategoryMapping>> findUserCategoryMappingByUserId(String id) {
        return userCategoryRepository.findByUserId(id);
    }

    @Override
    public Optional<List<UserCategoryMapping>> findUserCategoryMappingByUserIdAndIsActive(String id, boolean b) {
        return userCategoryRepository.findByUserIdAndIsActive(id, b);
    }

    private void newUserCategoryMapping(String userId, List<String> categoryIds, List<UserCategoryMapping> userCategoryMappings) {

        for (String categoryId : categoryIds) {
            UserCategoryMapping userCategoryMapping = new UserCategoryMapping();
            User user = new User();
            user.setId(userId);
            Category category = new Category();
            category.setId(categoryId);
            userCategoryMapping.setUser(user);
            userCategoryMapping.setCategory(category);
            userCategoryMapping.setActive(true);
            userCategoryMappings.add(userCategoryMapping);
        }
    }
}

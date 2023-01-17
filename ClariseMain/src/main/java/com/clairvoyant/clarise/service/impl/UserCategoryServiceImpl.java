package com.clairvoyant.clarise.service.impl;

import com.clairvoyant.clarise.dto.UserCategoryDto;
import com.clairvoyant.clarise.model.*;
import com.clairvoyant.clarise.repository.UserCategoryRepository;
import com.clairvoyant.clarise.service.UserCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserCategoryServiceImpl implements UserCategoryService {

    @Autowired
    private UserCategoryRepository userCategoryRepository;

    @Override
    public List<UserCategoryMapping> addOrUpdateUserCategory(UserCategoryDto userCategoryDto) {
        Optional<UserCategoryMapping> userCategoryMapping = userCategoryRepository.findByUserId(userCategoryDto.getUserId());

        if (userCategoryMapping.isEmpty())
            newUserCategoryMapping(userCategoryDto.getUserId(), userCategoryDto.getCategoryIds());

        return userCategoryRepository.findAll();
    }

    @Override
    public List<UserCategoryMapping> findAllUserCategoryMapping() {
        return userCategoryRepository.findAll();
    }

    @Override
    public List<String> findCategoryIdByUserId(String id) {
        return userCategoryRepository.getCategoryIdByUserId(id);
    }

    private void newUserCategoryMapping(String userId, List<String> categoryIds) {

        for (String categoryId : categoryIds) {
            UserCategoryMapping userCategoryMapping = new UserCategoryMapping();
            User user = new User();
            user.setId(userId);
            Category category = new Category();
            category.setId(categoryId);
            userCategoryMapping.setUser(user);
            userCategoryMapping.setCategory(category);
            userCategoryRepository.save(userCategoryMapping);
        }
    }
}

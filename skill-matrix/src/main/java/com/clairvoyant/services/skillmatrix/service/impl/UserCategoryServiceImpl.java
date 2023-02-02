package com.clairvoyant.services.skillmatrix.service.impl;

import com.clairvoyant.services.skillmatrix.dto.UserCategoryDto;
import com.clairvoyant.services.skillmatrix.model.Category;
import com.clairvoyant.services.skillmatrix.model.User;
import com.clairvoyant.services.skillmatrix.model.UserCategoryMapping;
import com.clairvoyant.services.skillmatrix.repository.UserCategoryRepository;
import com.clairvoyant.services.skillmatrix.service.UserCategoryService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCategoryServiceImpl implements UserCategoryService {

    @Autowired
    private UserCategoryRepository userCategoryRepository;

    @Override
    public List<UserCategoryMapping> addOrUpdateUserCategory(UserCategoryDto userCategoryDto) {
        Optional<List<UserCategoryMapping>> userCategoryMapping = userCategoryRepository.findByUserId(userCategoryDto.getUserId());

        if (userCategoryMapping.get().isEmpty()) {
            newUserCategoryMapping(userCategoryDto.getUserId(), userCategoryDto.getCategoryIds());
        }

        return userCategoryRepository.findAll();
    }

    @Override
    public List<UserCategoryMapping> findAllUserCategoryMapping() {
        return userCategoryRepository.findAll();
    }

    @Override
    public Optional<List<UserCategoryMapping>> findUserCategoryMappingByUserId(String id) {
        return userCategoryRepository.findByUserId(id);
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

package com.clairvoyant.services.skillmatrix.controller;

import com.clairvoyant.services.skillmatrix.dto.CategoryDto;
import com.clairvoyant.services.skillmatrix.enums.Status;
import com.clairvoyant.services.skillmatrix.exceptions.DuplicateNameException;
import com.clairvoyant.services.skillmatrix.exceptions.ResourceNotFoundException;
import com.clairvoyant.services.skillmatrix.service.CategoryService;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CategoryControllerTest {

    @InjectMocks
    CategoryController categoryController;

    @Mock
    CategoryService categoryService;

    @Test
    public void getCategories() {
        List<CategoryDto> categoryDtoList = new ArrayList<>();
        CategoryDto categoryDto = new CategoryDto("any-id", "any-name", "any-desc", true);
        categoryDtoList.add(categoryDto);
        Mockito.when(categoryService.findAll()).thenReturn(categoryDtoList);
        Assert.assertEquals(categoryDtoList, categoryController.findAll());
    }

    @Test
    public void getCategoriesEmpty() {
        Mockito.when(categoryService.findAll()).thenReturn(new ArrayList());
        Assert.assertEquals(new ArrayList(), categoryController.findAll());
    }

    @Test
    public void addCategory() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCatName("any-name");
        categoryDto.setDescription("any-desc");

        Mockito.when(categoryService.addOrUpdateCategory(categoryDto)).thenReturn(categoryDto);
        Assert.assertEquals(categoryDto, categoryController.addOrUpdateCategory(categoryDto));
    }

    @Test
    public void updateCategory() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId("any-id");
        categoryDto.setCatName("any-name");
        categoryDto.setDescription("any-desc");

        Mockito.when(categoryService.addOrUpdateCategory(categoryDto)).thenReturn(categoryDto);
        Assert.assertEquals(categoryDto, categoryController.addOrUpdateCategory(categoryDto));
    }

    @Test
    public void addOrUpdateCategoryWithResourceNotFoundException() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId("any-id");
        categoryDto.setCatName("any-name");
        categoryDto.setDescription("any-desc");

        Mockito.when(categoryService.addOrUpdateCategory(categoryDto)).thenThrow(ResourceNotFoundException.class);
        Assert.assertThrows(ResourceNotFoundException.class, () -> {
            categoryController.addOrUpdateCategory(categoryDto);
        });
    }

    @Test
    public void addOrUpdateCategoryWithDuplicateNameException() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId("any-id");
        categoryDto.setCatName("any-name");
        categoryDto.setDescription("any-desc");

        Mockito.when(categoryService.addOrUpdateCategory(categoryDto)).thenThrow(DuplicateNameException.class);
        Assert.assertThrows(DuplicateNameException.class, () -> {
            categoryController.addOrUpdateCategory(categoryDto);
        });
    }

    @Test
    public void findCategoryById() {
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId("any-id");
        categoryDto.setCatName("any-name");
        categoryDto.setDescription("any-desc");
        categoryDto.setActive(true);

        Mockito.when(categoryService.findCategory("any-id")).thenReturn(categoryDto);
        Assert.assertEquals(categoryDto, categoryController.find("any-id"));
    }

    @Test
    public void findCategoryByIdWithResourceNotFoundException() {
        Mockito.when(categoryService.findCategory("any-id")).thenThrow(ResourceNotFoundException.class);
        Assert.assertThrows(ResourceNotFoundException.class, () -> {
            categoryController.find("any-id");
        });
    }

    @Test
    public void deleteCategory() {
        Assert.assertEquals(Status.SUCCESS, categoryController.deleteCategory("any-id"));
    }

    @Test
    public void deleteCategoryWithResourceNotFoundException() {
        Mockito.doThrow(ResourceNotFoundException.class).when(categoryService).deleteCategory("any-id");
        Assert.assertThrows(ResourceNotFoundException.class, () -> {
            categoryController.deleteCategory("any-id");
        });
    }
}

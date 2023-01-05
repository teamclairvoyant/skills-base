package com.clairvoyant.clarise.resolvers;

import com.clairvoyant.clarise.model.Category;
import com.clairvoyant.clarise.service.impl.CategoryServiceImpl;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
import liquibase.pro.packaged.C;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.powermock.api.mockito.PowerMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doReturn;

/*@Import(CategoryServiceImpl.class)*/
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoryMutationTest {
    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;


    @MockBean
    CategoryServiceImpl categoryService;


    private Category category = new Category();

    @BeforeEach
    public void setUp() {
        category.setCatName("Article");
        category.setDescription("This is a Java Article");
        category.setActive(true);
        category.setId(UUID.randomUUID().toString());
    }

    @Test
    public void addOrUpdateCategory() throws IOException {
        doReturn(category).when(categoryService).addOrUpdateCategory(any(Category.class));
        GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/addorupdatecategory.graphql");
        assertThat(response.isOk()).isTrue();

        assertThat(response.get("$.data.addOrUpdateCategory.id")).isNotNull();
        assertThat(response.get("$.data.addOrUpdateCategory.catName")).isEqualTo("Article");
    }

    @Test
    public void deleteCategory() throws IOException {
        PowerMockito.doNothing().when(categoryService).deleteCategory(anyString());
        GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/deletecategory.graphql");
        assertThat(response.isOk()).isTrue();


    }

    @Test
    public void findCategoryById() throws IOException {
        doReturn(category).when(categoryService).findCategory(anyString());
        GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/findcategorybyid.graphql");
        assertThat(response.get("$.data.findCategoryById.id")).isNotNull();
        assertThat(response.get("$.data.findCategoryById.catName")).isEqualTo("Article");

    }

    @Test
    public void getCategories() throws IOException {
        List l= new ArrayList();
        l.add(category);
        doReturn(l).when(categoryService).findAll();
        GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/findcategorybyid.graphql");
        assertThat(response.isOk()).isTrue();

    }
}

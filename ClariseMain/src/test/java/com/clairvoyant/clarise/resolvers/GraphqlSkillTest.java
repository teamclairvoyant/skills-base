
package com.clairvoyant.clarise.resolvers;

import com.clairvoyant.clarise.model.Skill;
import com.clairvoyant.clarise.service.impl.SkillServiceImpl;
import com.graphql.spring.boot.test.GraphQLResponse;
import com.graphql.spring.boot.test.GraphQLTestTemplate;
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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GraphqlSkillTest {

    @Autowired
    private GraphQLTestTemplate graphQLTestTemplate;


    @MockBean
    SkillServiceImpl skillService;


    private Skill skill = new Skill();

    @BeforeEach

    public void setUp() {
        skill.setSkillName("java");
        skill.setDescription("java with multiple frameworks");
        skill.setActive(true);
        skill.setId(UUID.randomUUID().toString());
    }

    @Test
    public void addOrUpdateSkill() throws IOException {
        doReturn(skill).when(skillService).addOrUpdateSkill(any(Skill.class));
        GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/addorupdateskill.graphql");
        assertThat(response.isOk()).isTrue();

        assertThat(response.get("$.data.addOrUpdateSkill.id")).isNotNull();
        assertThat(response.get("$.data.addOrUpdateSkill.skillName")).isEqualTo("java");
    }

    @Test
    public void deleteSkill() throws IOException {
        PowerMockito.doNothing().when(skillService).deleteSkill(anyString());
        GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/deleteskill.graphql");
        assertThat(response.isOk()).isTrue();

        assertThat(response.get("$.data.deleteSkill")).isEqualTo("SUCCESS");

    }

    @Test
    public void findSkillById() throws IOException {
        doReturn(skill).when(skillService).findSkill(anyString());
        GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/findskillbyid.graphql");
        assertThat(response.get("$.data.findSkillById.id")).isNotNull();
        assertThat(response.get("$.data.findSkillById.skillName")).isEqualTo("java");

    }

    @Test
    public void getSkills() throws IOException {
        List l= new ArrayList();
        l.add(skill);
        doReturn(l).when(skillService).findAll();
        GraphQLResponse response = graphQLTestTemplate.postForResource("graphql/findskillbyid.graphql");
        assertThat(response.isOk()).isTrue();

    }
}


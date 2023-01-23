package com.clairvoyant.clarise.repository;

import com.clairvoyant.clarise.model.User;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@DataJpaTest
public class UserRepositoryTest {

    private final Logger log= LoggerFactory.getLogger(UserRepositoryTest.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager entityManager;

    private static User user=new User();


    @BeforeAll
    static void setUp() {
        user.setId("v1");
        user.setName("vijay");
        user.setEmailAddress("vijay@gmail.com");
        user.setPassword("vijay@123");
        user.setReportingManager("mark");
        user.setGrade("A");
        user.setActive(true);
        user.setCreatedBy("sanket");
        user.setModifiedBy("Bambalkar");
        user.setCreatedDate(ZonedDateTime.ofInstant(Instant.now(), ZoneId.of("UTC")));
        user.setModifiedDate(ZonedDateTime.ofInstant(Instant.now(), ZoneId.of("UTC")));

    }


    @Test
    public void should_find_no_user_if_repository_is_empty() {
        Iterable users = userRepository.findAll();

        assertThat(users).isEmpty();
    }


    @Test
    public void saveEmployeeTest()
    {
        User userResponse=userRepository.save(user);

        assertThat(userResponse).hasFieldOrPropertyWithValue("emailAddress", "vijay@gmail.com");
    }


    @Test
    public void findAllTest()
    {
        userRepository.save(user);

        List<User> allUser=userRepository.findAll();

        assertThat(allUser.size()).isGreaterThan(0);
    }

    @Test
    public void updateUserTest()
    {

        User userResponse=userRepository.save(user);

        userResponse.setEmailAddress("sanketb@gmail.com");

        User updatedUser=userRepository.save(userResponse);

        assertThat(updatedUser.getEmailAddress()).isEqualTo("sanketb@gmail.com");
    }



}

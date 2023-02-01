package com.clairvoyant.services.skillmatrix.repository;

import com.clairvoyant.services.skillmatrix.model.User;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.List;
import java.util.Optional;

@RunWith(PowerMockRunner.class)
@PrepareForTest(UserRepository.class)
public class UserRepositoryTest {
    private UserRepository userRepositoryMock= PowerMockito.mock(UserRepository.class);

    private User user=PowerMockito.mock(User.class);

    @Test
    public void saveTest()
    {
        PowerMockito.when(userRepositoryMock.save(user)).thenReturn(user);

        User savedUser=userRepositoryMock.save(user);

        Assertions.assertEquals(user,savedUser);
    }

    @Test
    public void findByIdTest()
    {
        PowerMockito.when(userRepositoryMock.findById("101")).thenReturn(Optional.of(user));

        User returnedUser=userRepositoryMock.findById("101").get();

        Assertions.assertNotNull(returnedUser);
    }

    @Test
    public void findAllTest()
    {
        PowerMockito.when(userRepositoryMock.findAll()).thenReturn(List.of(user));

        List<User> userList=userRepositoryMock.findAll();

        Assertions.assertTrue(userList.size()>0 , "Contains user list ");
    }

    @Test
    public void deleteTest()
    {
        userRepositoryMock.delete(user);
        userRepositoryMock.delete(user);

        Mockito.verify(userRepositoryMock,Mockito.times(2)).delete(user);
    }

    @Test
    public void findByIsActiveTest()
    {

        PowerMockito.when(userRepositoryMock.findByIsActive(true)).thenReturn(List.of(user));

        List<User> userList=userRepositoryMock.findByIsActive(true);

        Assertions.assertTrue(userList.size()>0);

    }

    @Test
    public void findByIdAndIsActiveTest()
    {
        PowerMockito.when(userRepositoryMock.findByIdAndIsActive("101",true)).thenReturn(Optional.of(user));

        User returnedUser=userRepositoryMock.findByIdAndIsActive("101",true).get();

        Assertions.assertNotNull(returnedUser);
    }

}

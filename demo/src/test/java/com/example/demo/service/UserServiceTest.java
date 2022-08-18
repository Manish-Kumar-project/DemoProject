package com.example.demo.service;

import com.example.demo.Entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.interf.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void testCreateTicket(){

        User user = new User();
        user.setUserUniqueId(200100L);
        user.setFirstName("jamestest");
        user.setLastName("andersontest");
        user.setEmail("jamestest@gmail.com");
        user.setGender("male");
        user.setMobileNo(12456780L);

        Mockito.when(userRepository.save(user)).thenReturn(user);

        assertThat(userService.saveUser(user)).isEqualTo(user);

    }

    @Test
    public void testGetUserById(){
        User user = new User();
        user.setUserUniqueId(200100L);
        user.setFirstName("jamestest");
        user.setLastName("andersontest");
        user.setEmail("jamestest@gmail.com");
        user.setGender("male");
        user.setMobileNo(12456780L);

        Mockito.when(userRepository.findByUserUniqueId(200100L)).thenReturn(user);
        assertThat(userService.getUser(200100L)).isEqualTo(user);
    }
    @Test
    public void testGetAllBookedTickets(){
        User user = new User();
        user.setUserUniqueId(1001L);
        user.setFirstName("kane");
        user.setLastName("anderson");
        user.setEmail("kane@gmail.com");
        user.setGender("male");
        user.setMobileNo(12456780L);

        User user1 = new User();
        user1.setUserUniqueId(1000L);
        user1.setFirstName("james");
        user1.setLastName("anderson");
        user1.setEmail("james@gmail.com");
        user1.setGender("female");
        user1.setMobileNo(12456780L);

        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(user1);

        Mockito.when(userRepository.findAll()).thenReturn(userList);

        assertThat(userService.getAllUsers()).isEqualTo(userList);
    }

}

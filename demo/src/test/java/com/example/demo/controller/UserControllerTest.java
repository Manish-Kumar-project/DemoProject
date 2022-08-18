package com.example.demo.controller;

import com.example.demo.Entity.User;
import com.example.demo.service.interf.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;


    @Test
    public void saveUserTest() throws Exception {
        User user = new User();
        user.setUserUniqueId(2000L);
        user.setFirstName("james");
        user.setLastName("anderson");
        user.setEmail("james@gmail.com");
        user.setGender("male");
        user.setMobileNo(12456780L);

        String inputInJson = this.mapToJson(user);
        String URI = "/api/user/user";

        Mockito.when(userService.saveUser(Mockito.any(User.class))).thenReturn(user);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post(URI)
                .accept(MediaType.APPLICATION_JSON).content(inputInJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse response = result.getResponse();
        String outputInJson = response.getContentAsString();

        assertThat(outputInJson).isEqualTo(inputInJson);
        assertEquals(HttpStatus.OK.value(), response.getStatus());
    }
    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }
    @Test
    public void testGetUserById() throws Exception {
        User user = new User();
        user.setUserUniqueId(2000L);
        user.setFirstName("james");
        user.setLastName("anderson");
        user.setEmail("james@gmail.com");
        user.setGender("male");
        user.setMobileNo(12456780L);
        Mockito.when(userService.getUser(Mockito.anyLong())).thenReturn(user);

        String URI = "/api/user/user/2000";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                URI).accept(
                MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expectedJson = this.mapToJson(user);
        String outputInJson = result.getResponse().getContentAsString();
        assertThat(outputInJson).isEqualTo(expectedJson);

    }
    @Test
    public void testGetAllUsers() throws Exception {
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

        Mockito.when(userService.getAllUsers()).thenReturn(userList);

        String URI = "/api/user/users";
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                URI).accept(
                MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String expectedJson = this.mapToJson(userList);
        String outputInJson = result.getResponse().getContentAsString();
        assertThat(outputInJson).isEqualTo(expectedJson);

    }

    }

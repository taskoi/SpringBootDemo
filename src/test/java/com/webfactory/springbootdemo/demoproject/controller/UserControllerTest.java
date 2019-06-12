package com.webfactory.springbootdemo.demoproject.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.webfactory.springbootdemo.demoproject.AbstractTest;
import com.webfactory.springbootdemo.demoproject.exeptions.exception.response.ExceptionResponse;
import com.webfactory.springbootdemo.demoproject.model.Location;
import com.webfactory.springbootdemo.demoproject.model.Post;
import com.webfactory.springbootdemo.demoproject.model.Role;
import com.webfactory.springbootdemo.demoproject.model.User;
import com.webfactory.springbootdemo.demoproject.model.reguest.bodies.UserForm;
import com.webfactory.springbootdemo.demoproject.service.PostService;
import com.webfactory.springbootdemo.demoproject.service.UserService;
import com.webfactory.springbootdemo.demoproject.web.UserController;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.results.ResultMatchers;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest extends AbstractTest {

    @Autowired
    MockMvc mockMvc;

    @Mock
    UserService userService;

    @Mock
    PostService postService;

    @InjectMocks
    UserController userController;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    @Test
    public void createUser() throws Exception {
        User user = new User();
        Location location = new Location();
        location.setCountry("Macedonia");
        location.setCity("Skopje");
        location.setLatitude((float) 22);
        location.setLongitude((float) 33);
        user.setLocation(location);
        user.setNickname("tasko");
        user.setUsername("tasko");
        user.setFirstName("ivan");
        user.setLastName("tasevski");
        user.setPassword("Tasevski0101!@#");
        Role role = new Role();
        role.setRole("USER");
        user.getRoles().add(role);
        user.setEmail("tivan997@hotmail.com");

        Mockito.when(userService.createUser(Mockito.any(UserForm.class))).thenReturn(user);


        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/createUser")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(asJsonString(user));

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        Assert.assertEquals(200,mockHttpServletResponse.getStatus());
    }

    @Test
    public void createUserTrue() throws Exception {
        UserForm userForm = new UserForm();
        Location location = new Location();
        location.setCountry("Macedonia");
        location.setCity("Skopje");
        location.setLatitude((float) 22);
        location.setLongitude((float) 33);
        userForm.setLocation(location);
        userForm.setNickname("tasko");
        userForm.setUsername("tasko");
        userForm.setFirstName("ivan");
        userForm.setLastName("tasevski");
        userForm.setEmail("tivan997@hotmail.com");
        userForm.setPassword("Tasevski0101!@#");
        Role role = new Role();
        role.setRole("USER");
        userForm.setRoles(Arrays.asList(role));

        mockMvc.perform(MockMvcRequestBuilders
            .post("/api/createUser")
            .content(asJsonString(userForm))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
                //.andExpect(MockMvcResultMatchers.jsonPath("$.firstName").exists());
    }

    @Test
    public void createUserFalse() throws Exception{
        UserForm userForm = new UserForm();
        Location location = new Location();
        location.setCountry("Macedonia");
        location.setCity("Skopje");
        location.setLatitude((float) 22);
        location.setLongitude((float) 33);
        userForm.setLocation(location);
        userForm.setNickname("tasko");
        userForm.setUsername("tasko");
        userForm.setFirstName("ivan");
        userForm.setLastName("tasevski");
        userForm.setEmail("tivan997@hotmail.com");
        userForm.setPassword("");
        Role role = new Role();
        role.setRole("USER");
        userForm.setRoles(Arrays.asList(role));


        mockMvc.perform(MockMvcRequestBuilders
            .post("/api/createUser")
        .content(asJsonString(userForm))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void createUserNonJsonFormat() throws Exception{
        UserForm userForm = new UserForm();
        Location location = new Location();
        location.setCountry("Macedonia");
        location.setCity("Skopje");
        location.setLatitude((float) 22);
        location.setLongitude((float) 33);
        userForm.setLocation(location);
        userForm.setNickname("tasko");
        userForm.setUsername("tasko");
        userForm.setFirstName("ivan");
        userForm.setLastName("tasevski");
        userForm.setEmail("tivan997@hotmail.com");
        userForm.setPassword("IVanTAsevsak3123!@#");
        Role role = new Role();
        role.setRole("USER");
        userForm.setRoles(Arrays.asList(role));

        mockMvc.perform(MockMvcRequestBuilders
            .post("/api/createUser")
            .content(String.valueOf(userForm))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void updateUserTrue() throws Exception{
        UserForm userForm = new UserForm();
        Location location = new Location();
        location.setCountry("Macedonia");
        location.setCity("Skopje");
        location.setLatitude((float) 22);
        location.setLongitude((float) 33);
        userForm.setLocation(location);
        userForm.setNickname("tasko");
        userForm.setUsername("tasko");
        userForm.setFirstName("ivan");
        userForm.setLastName("tasevski");
        userForm.setEmail("tivan997@hotmail.com");
        userForm.setPassword("IVanTAsevsak3123!@#");
        Role role = new Role();
        role.setRole("USER");
        userForm.setRoles(Arrays.asList(role));

        mockMvc.perform(MockMvcRequestBuilders
            .patch("/api/updateUser/{id}",1)
            .content(asJsonString(userForm))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void updateUserNonJsonFormat() throws Exception{
        UserForm userForm = new UserForm();
        Location location = new Location();
        location.setCountry("Macedonia");
        location.setCity("Skopje");
        location.setLatitude((float) 22);
        location.setLongitude((float) 33);
        userForm.setLocation(location);
        userForm.setNickname("asdasd");
        userForm.setUsername("tasko");
        userForm.setFirstName("ivan");
        userForm.setLastName("tasevski");
        userForm.setEmail("tivan997@hotmail.com");
        userForm.setPassword("");
        Role role = new Role();
        role.setRole("USER");
        userForm.setRoles(Arrays.asList(role));

        ResultActions perform = mockMvc.perform(MockMvcRequestBuilders
                .patch("/api/updateUser/{id}", 1231231)
                .content(String.valueOf(userForm))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        MockHttpServletResponse response = perform.andReturn().getResponse();
        ExceptionResponse exceptionResponse = new ObjectMapper().readValue(response.getContentAsString(), ExceptionResponse.class);
        Assert.assertEquals(exceptionResponse.getMessage(), "Password cannot be null");

        perform.andExpect(status().isBadRequest());
    }
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

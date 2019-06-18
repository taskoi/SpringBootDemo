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
import org.hibernate.mapping.Collection;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.PathVariable;
import org.thymeleaf.spring5.expression.Mvc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    public void createUserTest() throws Exception {
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
        Assert.assertEquals(200, mockHttpServletResponse.getStatus());
        Assert.assertTrue(mockHttpServletResponse.getContentAsString().contains("tasko"));
        Assert.assertTrue(mockHttpServletResponse.getContentAsString().contains("USER"));
    }

    @Test
    public void createUserMissingUserFormParameterTest() throws Exception {
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
        //userForm.setEmail("tivan997@hotmail.com");
        userForm.setPassword("IVanTAsevsak3123!@#");
        Role role = new Role();
        role.setRole("USER");
        userForm.setRoles(Arrays.asList(role));

        Mockito.when(userService.createUser(Mockito.any(UserForm.class))).thenReturn(new User());

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/createUser")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userForm));

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), mockHttpServletResponse.getStatus());
    }

    @Test
    public void createUserNoHandlerFoundTest() throws Exception {
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

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/noHandlerFound")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userForm));

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        Assert.assertEquals(HttpStatus.NOT_FOUND.value(), mockHttpServletResponse.getStatus());
    }

    @Test
    public void createUserNonJsonFormatTest() throws Exception {
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

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/createUser")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_PDF)
                .content(asJsonString(userForm));

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        Assert.assertEquals(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), mockHttpServletResponse.getStatus());
    }

    @Test
    public void createUserMethodMethodNotSupportedTest() throws Exception {
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

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/createUser")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userForm));

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse mockHttpServletResponse = result.getResponse();
        Assert.assertEquals(HttpStatus.METHOD_NOT_ALLOWED.value(), mockHttpServletResponse.getStatus());
    }

    @Test
    public void createUserMethodArgumentNotValidTest() throws Exception {
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
        userForm.setEmail("tivan997l.com");
        userForm.setPassword("IVanTAsevsak3123!@#");
        Role role = new Role();
        role.setRole("USER");
        userForm.setRoles(Arrays.asList(role));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/api/createUser")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userForm));

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        Assert.assertEquals(HttpStatus.BAD_REQUEST.value(), mockHttpServletResponse.getStatus());
    }

    @Test
    public void updateUserTest() throws Exception {
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

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .patch("/api/updateUser/{id}", 1)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userForm));

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
    }

    @Test
    public void updateUserNonJsonFormatTest() throws Exception {
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

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .patch("/api/updateUser/{id}", 1)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_PDF)
                .content(asJsonString(userForm));
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        Assert.assertEquals(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), mockHttpServletResponse.getStatus());
    }


    @Test
    public void updateUserNotFoundTest() throws Exception {
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
        userForm.setPassword("PASdas12312!@#");
        Role role = new Role();
        role.setRole("USER");
        userForm.setRoles(Arrays.asList(role));


        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .patch("/api/updateUser/")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(userForm));
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        Assert.assertEquals(HttpStatus.NOT_FOUND.value(), mockHttpServletResponse.getStatus());

    }

    @Test
    public void updateUserMediaTypenotsupportedTest() throws Exception {
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
        userForm.setPassword("PASdas12312!@#");
        Role role = new Role();
        role.setRole("USER");
        userForm.setRoles(Arrays.asList(role));


        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .patch("/api/updateUser/1")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_ATOM_XML)
                .content(String.valueOf(userForm));
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        Assert.assertEquals(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), mockHttpServletResponse.getStatus());

    }

    @Test
    public void findAllUsersTest() throws Exception {
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/findAll");

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();

        Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
    }

    @Test
    public void findUserByIdTest() throws Exception {
        User user = new User("tivan997@hotmail.com", "ivan1", "Password012301!@#", "nicknam", "ivan", "tase", new Location((float) 22, (float) 33, "skopje", "makeconija"), Arrays.asList(new Role("USER")));
        Mockito.when(userService.findById((long) 1)).thenReturn(java.util.Optional.of(user));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/findById/{id}", 1);

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        Assert.assertEquals(200, mockHttpServletResponse.getStatus());
        Assert.assertTrue(mockHttpServletResponse.getContentAsString().contains("ivan1"));
    }

    @Test
    public void findUserByIdFalseTest() throws Exception {
        User user = new User("tivan997@hotmail.com", "ivan1", "Password012301!@#", "nicknam", "ivan", "tase", new Location((float) 22, (float) 33, "skopje", "makeconija"), Arrays.asList(new Role("USER")));
        Mockito.when(userService.findById((long) 1)).thenReturn(java.util.Optional.of(user));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/findById/{id}", 2);
        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        Assert.assertEquals(HttpStatus.NOT_FOUND.value(), mockHttpServletResponse.getStatus());
    }

    @Test
    public void deleteUserTest() throws Exception {
        User user = new User("tivan997@hotmail.com", "ivan1", "Password012301!@#", "nicknam", "ivan", "tase", new Location((float) 22, (float) 33, "skopje", "makeconija"), Arrays.asList(new Role("USER")));
        Mockito.when(userService.findById((long) 1)).thenReturn(java.util.Optional.of(user));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/api/deleteUser/1");

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse mockHttpServletResponse = mvcResult.getResponse();
        Assert.assertEquals(HttpStatus.OK.value(), mockHttpServletResponse.getStatus());
    }


    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

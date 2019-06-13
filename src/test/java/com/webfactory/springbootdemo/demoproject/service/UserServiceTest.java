package com.webfactory.springbootdemo.demoproject.service;

        import com.webfactory.springbootdemo.demoproject.web.UserController;
        import org.junit.runner.RunWith;
        import org.mockito.InjectMocks;
        import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    // ...

    @InjectMocks
    private UserService userService;
}

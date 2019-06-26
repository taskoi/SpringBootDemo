//package com.webfactory.springbootdemo.demoproject.service;
//
//import com.webfactory.springbootdemo.demoproject.exeptions.user.exceptions.NicknameNotValidException;
//import com.webfactory.springbootdemo.demoproject.exeptions.user.exceptions.UserExistsException;
//import com.webfactory.springbootdemo.demoproject.exeptions.user.exceptions.UserNotFoundException;
//import com.webfactory.springbootdemo.demoproject.model.Location;
//import com.webfactory.springbootdemo.demoproject.model.Role;
//import com.webfactory.springbootdemo.demoproject.model.User;
//import com.webfactory.springbootdemo.demoproject.model.reguest.bodies.UserForm;
//import com.webfactory.springbootdemo.demoproject.persistance.LocationRepository;
//import com.webfactory.springbootdemo.demoproject.persistance.RoleRepository;
//import com.webfactory.springbootdemo.demoproject.persistance.UserRepository;
//import org.junit.Test;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.function.Executable;
//import org.junit.runner.RunWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.MockitoAnnotations;
//import org.mockito.junit.MockitoJUnitRunner;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import java.util.*;
//import static junit.framework.TestCase.assertTrue;
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
//import static org.junit.Assert.assertNotEquals;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//@RunWith(MockitoJUnitRunner.Silent.class)
//public class UserServiceTest {
//
//    @Mock
//    UserRepository userRepository;
//
//    @Mock
//    LocationRepository locationRepository;
//
//    @Mock
//    RoleRepository roleRepository;
//
//    @Mock
//    PasswordEncoder passwordEncoder;
//
//    @InjectMocks
//    UserService userService;
//
//    @BeforeAll
//    public void init() {
//        MockitoAnnotations.initMocks(this);
//    }
//
//
//    @Test
//    public void createUserTest() throws UserExistsException {
//        UserForm userForm = new UserForm();
//        Location location = new Location();
//        location.setCountry("Macedonia");
//        location.setCity("Skopje");
//        location.setLatitude((float) 22);
//        location.setLongitude((float) 33);
//        userForm.setLocation(location);
//        userForm.setNickname("asdasd");
//        userForm.setUsername("tasko");
//        userForm.setFirstName("ivan");
//        userForm.setLastName("tasevski");
//        userForm.setEmail("tivan997@hotmail.com");
//        userForm.setPassword("PASdas12312!@#");
//        Role role = new Role();
//        role.setRole("USER");
//        userForm.setRoles(Collections.singletonList(role));
//
//        Location location1 = new Location((float) 22, (float) 33, "Skopje", "Macedonia");
//        Role role1 = new Role("USER");
//        String pass = passwordEncoder.encode("Password012301!@#");
//        User user = new User("tivan997@hotmail.com", "tasko", pass, "asdasd", "ivan", "tasevski", location, Arrays.asList(role));
//        List<User> listNickname = new ArrayList<>();
//
//        Mockito.when(userRepository.findAllByNickname(userForm.getNickname())).thenReturn(listNickname);
//        Mockito.when(userRepository.findByEmail(userForm.getEmail())).thenReturn(null);
//        Mockito.when(roleRepository.save(Mockito.any(Role.class))).thenReturn(role1);
//        Mockito.when(locationRepository.save(Mockito.any(Location.class))).thenReturn(location1);
//        Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(user);
//
//        User result = userService.createUser(userForm);
//
//        assertEquals("asdasd", result.getNickname());
//        assertEquals("tasko", result.getUsername());
//    }
//
//    @Test
//    public void updateUserTest() throws UserNotFoundException {
//        UserForm userForm = new UserForm();
//        Location location = new Location();
//        location.setCountry("Macedonia");
//        location.setCity("Skopje");
//        location.setLatitude((float) 22);
//        location.setLongitude((float) 33);
//        userForm.setLocation(location);
//        userForm.setNickname("WUHU");
//        userForm.setUsername("tasko0");
//        userForm.setFirstName("ivan");
//        userForm.setLastName("tasevski");
//        userForm.setEmail("tivan997@hotmail.com");
//        userForm.setPassword("PASdas12312!@#");
//        Role role = new Role();
//        role.setRole("USER");
//        userForm.setRoles(Collections.singletonList(role));
//
//        Location location1 = new Location((float) 22, (float) 33, "Skopje", "Macedonia");
//        Role role1 = new Role("USER");
//        String pass = passwordEncoder.encode("PASdas12312!@#");
//        User user = new User("tivan997@hotmail.com", "tasko", pass, "asdasd", "ivan", "tasevski", location, Arrays.asList(role));
//
//        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(java.util.Optional.of(user));
//        Mockito.when(locationRepository.save(Mockito.any())).thenReturn(location1);
//        Mockito.when(userRepository.findAllByNickname(userForm.getNickname())).thenReturn(Collections.<User>emptyList());
//        Mockito.when(roleRepository.save(Mockito.any())).thenReturn(role1);
//        Mockito.when(userRepository.save(Mockito.any())).thenReturn(user);
//
//        User result = userService.updateUser(userForm, 1L);
//
//        System.out.println(result.getUsername().length());
//        assertEquals("USER", result.getRoles().get(0).getRole());
//        assertNotEquals(1, result.getRoles().size());
//        assertTrue(result.getEmail().contains("ivan"));
//    }
//
//    @Test
//    public void findAllTest() throws UserNotFoundException {
//        List<User> all = new ArrayList<>();
//        User user1 = new User("tivan997@hotmail.com", "ivan1", "Password012301!@#", "nicknam", "ivan", "tase", new Location((float) 22, (float) 33, "skopje", "makeconija"), Arrays.asList(new Role("USER")));
//        User user2 = new User("tivan9972@hotmail.com", "ivan2", "Password012301!@#", "nicknam1", "ivan", "tase", new Location((float) 22, (float) 33, "skopje", "makeconija"), Arrays.asList(new Role("USER")));
//        all.add(user1);
//        all.add(user2);
//
//        Mockito.when(userRepository.findAll()).thenReturn(all);
//
//        List<User> result = userService.findAll();
//
//        assertEquals(2, result.size());
//        assertNotEquals(1, result.size());
//        assertEquals("ivan1", result.get(0).getUsername());
//        assertEquals("ivan2", result.get(1).getUsername());
//    }
//
//    @Test
//    public void findByIdTest() throws UserNotFoundException {
//        User user1 = new User("tivan997@hotmail.com", "ivan1", "Password012301!@#", "nicknam", "ivan", "tase", new Location((float) 22, (float) 33, "skopje", "makeconija"), Arrays.asList(new Role("USER")));
//
//        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(java.util.Optional.of(user1));
//
//        Optional<User> result = userService.findById(1L);
//
//        assertEquals("ivan1", result.get().getUsername());
//        assertEquals("nicknam", result.get().getNickname());
//        assertNotEquals("sample", result.get().getEmail());
//    }
//
//
//    @Test
//    public void deleteUser() throws UserNotFoundException {
//        User user1 = new User("tivan997@hotmail.com", "ivan1", "Password012301!@#", "nicknam", "ivan", "tase", new Location((float) 22, (float) 33, "skopje", "makeconija"), Arrays.asList(new Role("USER")));
//
//        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(user1));
//        userService.deleteUser(1L);
//        Mockito.verify(userRepository).deleteById(1L);
//    }
//
//    @Test
//    public void findByNicknameTest() throws UserNotFoundException {
//        User user1 = new User("tivan997@hotmail.com", "ivan1", "Password012301!@#", "nicknam", "ivan", "tase", new Location((float) 22, (float) 33, "skopje", "makeconija"), Arrays.asList(new Role("USER")));
//        List<User> all = new ArrayList<>(Collections.singletonList(user1));
//
//        Mockito.when(userRepository.findAllByNickname(Mockito.any())).thenReturn(all);
//
//        List<User> result = userService.findByNickname("nicknam");
//
//        assertEquals(1, result.size());
//        assertEquals("ivan1", result.get(0).getUsername());
//        assertEquals(1, result.get(0).getRoles().size());
//    }
//
//    @Test
//    public void findByLocationCityTest() throws UserNotFoundException {
//        User user1 = new User("tivan997@hotmail.com", "ivan1", "Password012301!@#", "nicknam", "ivan", "tase", new Location((float) 22, (float) 33, "skopje", "makeconija"), Arrays.asList(new Role("USER")));
//        List<User> all = new ArrayList<>(Collections.singletonList(user1));
//
//        Mockito.when(userRepository.findAllByLocationCityContaining(Mockito.any())).thenReturn(all);
//
//        List<User> result = userService.findByLocationCity("Skopje");
//
//        assertEquals(1, result.size());
//        assertEquals("ivan1", result.get(0).getUsername());
//    }
//
//    @Test
//    public void findAllByUsername() {
//        User user1 = new User("tivan997@hotmail.com", "ivan1", "Password012301!@#", "nicknam", "ivan", "tase", new Location((float) 22, (float) 33, "skopje", "makeconija"), Arrays.asList(new Role("USER")));
//        List<User> all = new ArrayList<>(Collections.singletonList(user1));
//
//        Mockito.when(userRepository.findAllByUsername(Mockito.any())).thenReturn(all);
//
//        List<User> result = userService.findAllByUsername("username");
//
//        assertEquals(1, result.size());
//        assertNotEquals(2, result.size());
//    }
//
//    @Test
//    public void loadUserByUsernameTest() {
//        User user1 = new User("tivan997@hotmail.com", "ivan1", "Password012301!@#", "nicknam", "ivan", "tase", new Location((float) 22, (float) 33, "skopje", "makeconija"), Arrays.asList(new Role("USER")));
//
//        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(user1));
//
//        UserDetailsImpl userDetails = new UserDetailsImpl(user1);
//
//        assertEquals("ivan1", userDetails.getUsername());
//        assertTrue(userDetails.getPassword().contains("Password012301"));
//    }
//
//    @Test
//    public void loadUserByIdTest() {
//        User user1 = new User("tivan997@hotmail.com", "ivan1", "Password012301!@#", "nicknam", "ivan", "tase", new Location((float) 22, (float) 33, "skopje", "makeconija"), Arrays.asList(new Role("USER")));
//
//        Mockito.when(userRepository.findById(Mockito.any())).thenReturn(Optional.of(user1));
//
//        UserDetailsImpl userDetails = new UserDetailsImpl(user1);
//
//        assertEquals("ivan1", userDetails.getUsername());
//    }
//
//    @Test
//    public void createUserNicknameNotValidExceptionTest() {
//        User user1 = new User("tivan997@hotmail.com", "ivan1", "Password012301!@#", "nicknam", "ivan", "tase", new Location((float) 22, (float) 33, "skopje", "makeconija"), Arrays.asList(new Role("USER")));
//        List<User> all = new ArrayList<>(Arrays.asList(user1));
//
//        Mockito.when(userRepository.findAllByNickname(Mockito.any())).thenReturn(all);
//
//        UserForm userForm = new UserForm();
//        Location location = new Location();
//        location.setCountry("Macedonia");
//        location.setCity("Skopje");
//        location.setLatitude((float) 22);
//        location.setLongitude((float) 33);
//        userForm.setLocation(location);
//        userForm.setNickname("nicknam");
//        userForm.setUsername("tasko0");
//        userForm.setFirstName("ivan");
//        userForm.setLastName("tasevski");
//        userForm.setEmail("tivan997@hotmail.com");
//        userForm.setPassword("PASdas12312!@#");
//        Role role = new Role();
//        role.setRole("USER");
//        userForm.setRoles(Collections.singletonList(role));
//
//        Exception exception = assertThrows(NicknameNotValidException.class, new Executable() {
//            @Override
//            public void execute() throws Throwable {
//                userService.createUser(userForm);
//            }
//        });
//
//        System.out.println(exception.getMessage());
//
//        assertThat(exception).hasMessageContaining(userForm.getNickname());
//        assertTrue(exception.getMessage().contains(userForm.getNickname()));
//        assertThatExceptionOfType(NicknameNotValidException.class)
//                .as("check nickname")
//                .isThrownBy(() -> {throw new NicknameNotValidException(userForm.getNickname());})
//                .withMessageContaining(userForm.getNickname());
//    }
//
//    @Test
//    public void createUserExistsExceptionTest (){
//        User user1 = new User("tivan997@hotmail.com", "ivan1", "Password012301!@#", "nicknam", "ivan", "tase", new Location((float) 22, (float) 33, "skopje", "makeconija"), Arrays.asList(new Role("USER")));
//
//        Mockito.when(userRepository.findByEmail(Mockito.anyString())).thenReturn(user1);
//
//        UserForm userForm = new UserForm();
//        Location location = new Location();
//        location.setCountry("Macedonia");
//        location.setCity("Skopje");
//        location.setLatitude((float) 22);
//        location.setLongitude((float) 33);
//        userForm.setLocation(location);
//        userForm.setNickname("nicknam");
//        userForm.setUsername("tasko0");
//        userForm.setFirstName("ivan");
//        userForm.setLastName("tasevski");
//        userForm.setEmail("tivan997@hotmail.com");
//        userForm.setPassword("PASdas12312!@#");
//        Role role = new Role();
//        role.setRole("USER");
//        userForm.setRoles(Collections.singletonList(role));
//
//        Exception exception = assertThrows(UserExistsException.class, new Executable() {
//            @Override
//            public void execute() throws Throwable {
//                userService.createUser(userForm);
//            }
//        });
//
//        System.out.println(exception.getMessage());
//
//        assertThat(exception).hasMessageContaining(userForm.getEmail());
//        assertThatExceptionOfType(UserExistsException.class)
//                .isThrownBy(() -> {throw new UserExistsException(userForm.getEmail());})
//                .withMessageContaining(userForm.getEmail());
//    }
//
//    @Test
//    public void UserNotFoundExceptionTest(){
//
//        UserForm userForm = new UserForm();
//        Location location = new Location();
//        location.setCountry("Macedonia");
//        location.setCity("Skopje");
//        location.setLatitude((float) 22);
//        location.setLongitude((float) 33);
//        userForm.setLocation(location);
//        userForm.setNickname("nicknam");
//        userForm.setUsername("tasko0");
//        userForm.setFirstName("ivan");
//        userForm.setLastName("tasevski");
//        userForm.setEmail("tivan997@hotmail.com");
//        userForm.setPassword("PASdas12312!@#");
//        Role role = new Role();
//        role.setRole("USER");
//        userForm.setRoles(Collections.singletonList(role));
//
//        Exception exception = assertThrows(UserNotFoundException.class, () ->{
//            userService.findById(1L);
//        });
//
//        assertThatExceptionOfType(UserNotFoundException.class)
//                .isThrownBy(() -> {throw new UserNotFoundException("User is not found");})
//                .withMessageContaining("is not found");
//
//        assertThat(exception).hasMessageContaining("User with that id does not exist");
//    }
//
//    @Test
//    public void updateUserThrowsUserNotFoundTest(){
//        UserForm userForm = new UserForm();
//        Location location = new Location();
//        location.setCountry("Macedonia");
//        location.setCity("Skopje");
//        location.setLatitude((float) 22);
//        location.setLongitude((float) 33);
//        userForm.setLocation(location);
//        userForm.setNickname("nicknam");
//        userForm.setUsername("tasko0");
//        userForm.setFirstName("ivan");
//        userForm.setLastName("tasevski");
//        userForm.setEmail("tivan997@hotmail.com");
//        userForm.setPassword("PASdas12312!@#");
//        Role role = new Role();
//        role.setRole("USER");
//        userForm.setRoles(Collections.singletonList(role));
//
//        User user1 = new User("tivan997@hotmail.com", "ivan1", "Password012301!@#", "nicknam", "ivan", "tase", new Location((float) 22, (float) 33, "skopje", "makeconija"), Arrays.asList(new Role("USER")));
//
//        //Mockito.when(userRepository.findById(1L)).thenReturn(null);
//
//        Exception exception = assertThrows(UserNotFoundException.class, new Executable() {
//            @Override
//            public void execute() throws Throwable {
//                userService.updateUser(userForm,1L);
//            }
//        });
//
//        assertThatExceptionOfType(UserNotFoundException.class)
//                .isThrownBy(() -> {
//                    throw new UserNotFoundException("The user you searched for is not found!");
//                }).withMessageContaining("user you searched for");
//
//        System.out.println(exception.getMessage());
//    }
//
//    @Test
//    public void findAllUserNotFoundExceptionTest(){
//        //Mockito.when(userRepository.findAll()).thenReturn(null);
//
//        Exception exception = assertThrows(UserNotFoundException.class, new Executable() {
//            @Override
//            public void execute() throws Throwable {
//                userService.findAll();
//            }
//        });
//
//        System.out.println(exception.getMessage());
//
//        assertThatExceptionOfType(UserNotFoundException.class)
//                .isThrownBy(() -> {throw new UserNotFoundException("There are no users!");})
//                .withMessageContaining("no users");
//    }
//
//    @Test
//    public void findByIdUserNotFoundExceptionTest(){
//        Exception exception = assertThrows(UserNotFoundException.class, new Executable() {
//            @Override
//            public void execute() throws Throwable {
//                userService.findById(1L);
//            }
//        });
//
//        System.out.println(exception.getMessage());
//
//        assertThatExceptionOfType(UserNotFoundException.class)
//                .isThrownBy(() -> {throw new UserNotFoundException("User with that id does not exist");})
//                .withMessageContaining("with that");
//    }
//
//    @Test
//    public void deleteUserUserNotFoundExceptionTest(){
//        Exception exception = assertThrows(UserNotFoundException.class, new Executable() {
//            @Override
//            public void execute() throws Throwable {
//                userService.deleteUser(1L);
//            }
//        });
//
//        System.out.println(exception.getMessage());
//
//        assertThatExceptionOfType(UserNotFoundException.class)
//                .isThrownBy(() -> {throw new UserNotFoundException("User with that id does not exist");})
//                .withMessageContaining("that id does not exist");
//    }
//
//    @Test
//    public void findByNicknameUserNotFoundExceptionTest(){
//        final String nickname = "TestNickname";
//
//        Exception exception = assertThrows(UserNotFoundException.class, new Executable() {
//            @Override
//            public void execute() throws Throwable {
//                userService.findByNickname(nickname);
//            }
//        });
//
//        System.out.println(exception.getMessage());
//
//        assertThatExceptionOfType(UserNotFoundException.class)
//                .isThrownBy(() -> {throw new UserNotFoundException(nickname);})
//                .withMessageContaining(nickname);
//    }
//
//    @Test
//    public void findByLocationCityUserNotFoundExceptionTest(){
//        Exception exception = assertThrows(UserNotFoundException.class, new Executable() {
//            @Override
//            public void execute() throws Throwable {
//                userService.findByLocationCity("city");
//            }
//        });
//
//        System.out.println(exception.getMessage());
//
//        assertThatExceptionOfType(UserNotFoundException.class)
//                .isThrownBy(() -> {throw new UserNotFoundException("There are no users with that location city");})
//                .withMessageContaining("location");
//    }
//
//    @Test
//    public void findAllByUsernameUsernameNotFoundExceptionTest(){
//        Exception exception = assertThrows(UsernameNotFoundException.class, new Executable() {
//            @Override
//            public void execute() throws Throwable {
//                userService.findAllByUsername("username");
//            }
//        });
//
//        System.out.println(exception.getMessage());
//
//        assertThatExceptionOfType(UsernameNotFoundException.class)
//                .isThrownBy(() -> {throw new UsernameNotFoundException("There are no users with that username");})
//                .withMessageContaining("no users");
//    }
//
////    @Test
////    public void loadUserByUsernameUsernameNotFoundExceptionTest(){
////        Exception exception = assertThrows(UsernameNotFoundException.class, new Executable() {
////            @Override
////            public void execute() throws Throwable {
////                u
////            }
////        })
////    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//

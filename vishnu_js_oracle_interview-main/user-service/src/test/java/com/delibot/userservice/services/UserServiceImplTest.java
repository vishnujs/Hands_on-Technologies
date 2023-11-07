package com.delibot.userservice.services;

import com.delibot.userservice.util.UserType;
import com.delibot.userservice.web.controller.UserController;
import com.delibot.userservice.web.models.User;
import com.delibot.userservice.web.models.UserAuthority;
import org.checkerframework.checker.units.qual.A;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserServiceImplTest {


    @Autowired
    UserService userService;


    @BeforeAll
    void setUp() {
        User user = new User();
        user.setAddress("Trivandrum");
        user.setUserName("testUser");
        user.setEmail("testuser@gmail.com");
        user.setCreatedAt("2021-07-25");
        user.setLastName("junit");
        user.setPassword("a3876fafbc8b9b9d3820b6e3a610e3d2");
        user.setPhoneNumber("3216549877");
        user.setUpdatedAt("2021-07-25");
        user.setFirstName("TestUser");

        UserAuthority userAuthority = new UserAuthority();
        userAuthority.setUserType(UserType.CUSTOMER);
        Set<UserAuthority> userAuthSet = new HashSet<>();
        userAuthSet.add(userAuthority);
        user.setUserType(userAuthSet);

        userService.saveUser(user);

        User user2 = new User();
        user2.setAddress("Trivandrum2");
        user2.setUserName("testUser2");
        user2.setEmail("testuser2@gmail.com");
        user2.setCreatedAt("2021-07-25");
        user2.setLastName("junit2");
        user2.setPassword("a3876fafbc8b9b9d3820b6e3a610e3d2");
        user2.setPhoneNumber("3216249877");
        user2.setUpdatedAt("2021-07-25");
        user2.setFirstName("TestUser2");

        UserAuthority userAuthority2 = new UserAuthority();
        userAuthority2.setUserType(UserType.CUSTOMER);
        Set<UserAuthority> userAuthSet2 = new HashSet<>();
        userAuthSet2.add(userAuthority2);
        user2.setUserType(userAuthSet2);

        userService.saveUser(user2);
    }

    @Test
    void getUserById() {
        User user1 = new User();
        user1.setAddress("Trivandrum");
        user1.setUserName("testUser1");
        user1.setEmail("testuser1@gmail.com");
        user1.setCreatedAt("2021-07-25");
        user1.setLastName("junit1");
        user1.setPassword("a3876fafbc8b9b9d3820b6e3a610e3d2");
        user1.setPhoneNumber("3216548877");
        user1.setUpdatedAt("2021-07-25");
        user1.setFirstName("TestUser1");

        UserAuthority userAuthority1 = new UserAuthority();
        userAuthority1.setUserType(UserType.CUSTOMER);
        Set<UserAuthority> userAuthSet1 = new HashSet<>();
        userAuthSet1.add(userAuthority1);
        user1.setUserType(userAuthSet1);

        Optional<User> user = userService.saveUser(user1);
        Optional<User> userById = userService.getUserById(user.get().getUserId());
        if(user.isPresent() && userById.isPresent()) {

            Assert.assertEquals(
                    user.get().getUserId(), userById.get().getUserId()
            );
        } else
            Assert.fail();
    }

    @Test
    void getAllUsers() {
        List<User> allUsers = userService.getAllUsers();
        if (!allUsers.isEmpty())
            Assert.assertNotNull(allUsers);
        else
            Assert.fail();
    }

    @Test
    void saveUser() {
        User user1 = new User();
        user1.setAddress("Trivandrum");
        user1.setUserName("testUser1");
        user1.setEmail("testuser1@gmail.com");
        user1.setCreatedAt("2021-07-25");
        user1.setLastName("junit1");
        user1.setPassword("a3876fafbc8b9b9d3820b6e3a610e3d2");
        user1.setPhoneNumber("3216548877");
        user1.setUpdatedAt("2021-07-25");
        user1.setFirstName("TestUser1");

        UserAuthority userAuthority1 = new UserAuthority();
        userAuthority1.setUserType(UserType.CUSTOMER);
        Set<UserAuthority> userAuthSet1 = new HashSet<>();
        userAuthSet1.add(userAuthority1);
        user1.setUserType(userAuthSet1);

        Optional<User> user = userService.saveUser(user1);
        if(user.isPresent())
            Assert.assertNotNull(user.get().getUserId());
        else
            Assert.fail();
    }

    @Test
    void updateUser() {
        User user = new User();
        user.setAddress("Trivandrum");
        user.setUserName("testUser4");
        user.setEmail("testuser4@gmail.com");
        user.setCreatedAt("2021-07-25");
        user.setLastName("junit4");
        user.setPassword("a3876fafbc8b9b9d3820b6e3a610e3d2");
        user.setPhoneNumber("3246549877");
        user.setUpdatedAt("2021-07-25");
        user.setFirstName("TestUser4");

        UserAuthority userAuthority = new UserAuthority();
        userAuthority.setUserType(UserType.CUSTOMER);
        Set<UserAuthority> userAuthSet = new HashSet<>();
        userAuthSet.add(userAuthority);
        user.setUserType(userAuthSet);

        Optional<User> user1 = userService.saveUser(user);

        if (user1.isPresent()){
            user1.get().setPhoneNumber("1277345678");
            Optional<User> user2 = userService.updateUser(user1.get());

            if(user2.isPresent())
                Assert.assertTrue(user2.get().getPhoneNumber().equals("1277345678"));
            else
                Assert.fail();
        }
        else
            Assert.fail();


    }

    @Test
    void saveDeliverySExecutiveStatus() {
    }
}
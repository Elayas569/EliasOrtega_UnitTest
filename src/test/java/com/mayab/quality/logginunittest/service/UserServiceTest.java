package com.mayab.quality.logginunittest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.mayab.quality.loginunittest.dao.IDAOUser;
import com.mayab.quality.loginunittest.model.User;
import com.mayab.quality.loginunittest.service.UserService;

public class UserServiceTest {
    private IDAOUser mockDaoUser;
    private UserService mockUserService;
    private User mockUser;
    private HashMap<Integer, User> db;

    @BeforeEach
    public void setUp() {
        mockDaoUser = mock(IDAOUser.class);
        mockUserService = new UserService(mockDaoUser);
        mockUser = mock(User.class);
        db = new HashMap<>();

    }

    @Test
    public void testPasswordShort() {
        String name = "TestName";
        String email = "TestEmail";
        String password = "A";
        assertEquals(null, mockUserService.createUser(name, email, password));

    }

    @Test
    public void testPasswordLong() {
        String name = "TestName";
        String email = "TestEmail";
        String password = "AAAAAAAAAAAAAAAA";
        assertEquals(null, mockUserService.createUser(name, email, password));

    }

    @Test
    public void testEmailInvalid() {
        String name = "TestName";
        String email = "TestEmail";
        String password = "TestPassword";
        User existingUser = new User(name, email, password);
        when(mockDaoUser.findUserByEmail(anyString())).thenReturn(existingUser);

        assertEquals(existingUser, mockUserService.createUser(name, email, password));
    }

    @Test
    public void testAllDataValid() {
        String name = "TestName";
        String email = "TestEmail";
        String password = "TestPassword";
        when(mockDaoUser.save(any(User.class))).thenReturn(1);
        when(mockDaoUser.findUserByEmail(anyString())).thenReturn(null);
        User testUser = new User(name, email, password);
        testUser.setId(1);

        assertEquals(1, testUser.getId(), "User ID should be set to 1");
    }

    @Test
    public void UpdateUserTest() {
        User oldUser = new User("oldUser", "oldEmail", "oldPassword");
        db.put(1, oldUser);
        oldUser.setId(1);
        User newUser = new User("oldEmail", "newUser", "newPassword");
        newUser.setId(1);
        when(mockDaoUser.findById(1)).thenReturn(oldUser);

        when(mockDaoUser.updateUser(any(User.class))).thenAnswer(new Answer<User>() {
            public User answer(InvocationOnMock invocation) throws Throwable {
                User arg = (User) invocation.getArguments()[0];
                db.replace(arg.getId(), arg);

                return db.get(arg.getId());
            }
        });

        User result = mockUserService.updateUser(newUser);
        // Verification
        assertThat(result.getUsername(), is("newUser"));
        assertThat(result.getPassword(), is("newPassword"));
    }
}

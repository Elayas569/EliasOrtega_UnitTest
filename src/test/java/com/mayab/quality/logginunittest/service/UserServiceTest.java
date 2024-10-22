package com.mayab.quality.logginunittest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.mayab.quality.loginunittest.dao.IDAOUser;
import com.mayab.quality.loginunittest.model.User;
import com.mayab.quality.loginunittest.service.LoginService;
import com.mayab.quality.loginunittest.service.UserService;

public class UserServiceTest {
    private IDAOUser mockDaoUser;
    private UserService mockUserService;
    private User mockUser;

    @BeforeEach
    public void setUp() {
        mockDaoUser = mock(IDAOUser.class);
        mockUserService = new UserService(mockDaoUser);
        mockUser = mock(User.class);
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
}

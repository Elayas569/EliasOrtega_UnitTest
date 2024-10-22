package com.mayab.quality.logginunittest.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.mayab.quality.loginunittest.dao.IDAOUser;
import com.mayab.quality.loginunittest.model.User;
import com.mayab.quality.loginunittest.service.LoginService;

public class LoginServiceTest {

    User userMock;
    IDAOUser daoMock;
    LoginService loginService;

    @BeforeEach

    void setUp() throws Exception {
        userMock = mock(User.class);
        daoMock = mock(IDAOUser.class);
        loginService = new LoginService(daoMock);
    }

    @Test
    // Test to check if the login service is able to login a user with the correct
    // credentials

    void testCredentialsCorrect() {
        when(userMock.getUsername()).thenReturn("User");
        when(userMock.getPassword()).thenReturn("Password");
        when(daoMock.findUserByUsername(anyString())).thenAnswer(new Answer<User>() {
            public User answer(InvocationOnMock invocation) throws Throwable {
                return userMock;
            }
        });

        assertEquals(true, loginService.login(userMock.getUsername(), userMock.getPassword()));
    }

    // Test to check if the login service is able to login a user with incorrect
    // password
    @Test
    void testPasswordIncorrect() {
        String incorrectPassword = "Wrong";
        when(userMock.getUsername()).thenReturn("User");
        when(userMock.getPassword()).thenReturn("Password");
        when(daoMock.findUserByUsername(anyString())).thenReturn(userMock);

        // When
        boolean loginResult = loginService.login(userMock.getUsername(), incorrectPassword);

        // Then
        assertFalse(loginResult);
    }

    @Test
    void testNoUser() {
        when(daoMock.findUserByUsername(anyString())).thenReturn(null);
        when(userMock.getPassword()).thenReturn("Password");

        assertEquals(false, loginService.login(userMock.getUsername(), userMock.getPassword()));
    }

}

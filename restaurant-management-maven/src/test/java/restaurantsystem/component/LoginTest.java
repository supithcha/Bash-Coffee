/* Copyright (C) 2024 Russarin Eaimrittikrai, Supithcha Jongphoemwatthanaphon,
 * Sasasuang Pattanakitjaroenchai, Chaninan Phetpangun, Runchida Ananartyasit,
 * Phacharaphan Chalitchaiya, Pimmada Chompurat - All Rights Reserved
 * You may use, distribute and modify this code under the terms of the MUICT Echo license.
 */

/*
 This file includes test suite: testLogin
 */
package restaurantsystem.component;

import org.junit.Before;
import org.junit.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import restaurantsystem.component.auth.Login;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;


public class LoginTest {
    private Login login;
    private JTextField userNameField;
    private JPasswordField passwordField;
    private ActionEvent evt;
    private String dialogMessage;


    @Before
    public void setUp() throws Exception {
        login = new Login();
        login.setVisible(true);  // Ensure the JFrame and components are initialized

        // Access the userNameField and set it for testing
        Field userNameField = Login.class.getDeclaredField("userNameField");
        userNameField.setAccessible(true);
        this.userNameField = (JTextField) userNameField.get(login);

        // Set the mocked ActionEvent
        evt = new ActionEvent(login, ActionEvent.ACTION_PERFORMED, "loginButtonActionPerformed");

        // Reset dialogMessage before each test
        dialogMessage = null;
    }

    // Interface-based characteristic
    // Test Case T1 (C1b2, C2b1)
    // Login with correct userNameField
    // loginButtonActionPerformed( userNameField = “Shahin” )
    @Test
    public void testLoginSuccess() throws Exception {
        // Set the username to "shahin" to simulate successful login
        userNameField.setText("shahin");

        // Use MockedStatic to mock JOptionPane.showMessageDialog
        try (MockedStatic<JOptionPane> mockedJOptionPane = Mockito.mockStatic(JOptionPane.class)) {
            mockedJOptionPane.when(() -> JOptionPane.showMessageDialog(any(), anyString())).thenAnswer(invocation -> {
                dialogMessage = invocation.getArgument(1); // Capture the message
                return null;
            });

            // Access and invoke the private loginButtonActionPerformed method
            Method loginButtonActionPerformed = Login.class.getDeclaredMethod("loginButtonActionPerformed", ActionEvent.class);
            loginButtonActionPerformed.setAccessible(true);
            loginButtonActionPerformed.invoke(login, evt);

            // Verify that "Access granted" message is shown
            assertEquals("Access granted", dialogMessage);
        }

        // After successful login, check that login frame is disposed and MainMenu is shown
        assertFalse("Login window should be closed after successful login", login.isShowing());
    }

    // Interface-based characteristic
    // Test Case T2 (C1b2, C2b3)
    // Login with empty userNameField
    // loginButtonActionPerformed( userNameField = “” )
    @Test
    public void testLoginFailure() throws Exception {
        userNameField.setText("");

        try (MockedStatic<JOptionPane> mockedJOptionPane = Mockito.mockStatic(JOptionPane.class)) {
            mockedJOptionPane.when(() -> JOptionPane.showMessageDialog(any(), anyString())).thenAnswer(invocation -> {
                dialogMessage = invocation.getArgument(1);
                return null;
            });

            Method loginButtonActionPerformed = Login.class.getDeclaredMethod("loginButtonActionPerformed", ActionEvent.class);
            loginButtonActionPerformed.setAccessible(true);
            loginButtonActionPerformed.invoke(login, evt);

            assertEquals(" Access Denied", dialogMessage);
        }

        assertTrue("Login window should remain open after failed login", login.isShowing());
    }

    // Functionality-based characteristic
    // Test Case T3 (C1b1)
    // Login with correct userNameField
    // loginButtonActionPerformed( userNameField = “Shahin” )
    @Test
    public void testLoginWithMatchUsername() throws Exception {
        userNameField.setText("shahin");

        try (MockedStatic<JOptionPane> mockedJOptionPane = Mockito.mockStatic(JOptionPane.class)) {
            mockedJOptionPane.when(() -> JOptionPane.showMessageDialog(any(), anyString())).thenAnswer(invocation -> {
                dialogMessage = invocation.getArgument(1);
                return null;
            });

            Method loginButtonActionPerformed = Login.class.getDeclaredMethod("loginButtonActionPerformed", ActionEvent.class);
            loginButtonActionPerformed.setAccessible(true);
            loginButtonActionPerformed.invoke(login, evt);

            assertEquals("Access granted", dialogMessage);
        }

        assertFalse("Login window should be closed after successful login", login.isShowing());
    }

    // Functionality-based characteristic
    // Test Case T4 (C1b2)
    // Login with correct userNameField
    // loginButtonActionPerformed( userNameField = “wrongUser”)
    @Test
    public void testLoginWithNotMatchUsername() throws Exception {
        userNameField.setText("wrongUser");

        try (MockedStatic<JOptionPane> mockedJOptionPane = Mockito.mockStatic(JOptionPane.class)) {
            mockedJOptionPane.when(() -> JOptionPane.showMessageDialog(any(), anyString())).thenAnswer(invocation -> {
                dialogMessage = invocation.getArgument(1);
                return null;
            });

            Method loginButtonActionPerformed = Login.class.getDeclaredMethod("loginButtonActionPerformed", ActionEvent.class);
            loginButtonActionPerformed.setAccessible(true);
            loginButtonActionPerformed.invoke(login, evt);

            assertEquals(" Access Denied", dialogMessage);
        }

        assertTrue("Login window should remain open after failed login", login.isShowing());
    }

}
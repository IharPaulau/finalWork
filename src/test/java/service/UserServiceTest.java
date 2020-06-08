package service;

import models.Role;
import models.User;
import forms.RegistrationForm;

import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static utils.Constants.ROLE_USER_NAME;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration("file:src/main/webapp/WEB-INF/application-context.xml")
public class UserServiceTest {

    private static final String TEST_USERNAME = "TestUsername";
    private static final String TEST_USERNAME_2 = "TestUsername2";
    private static final String TEST_NOT_EXISTING_USERNAME = "TestNotExistingUsername";
    private static final String TEST_USER_PASSWORD = "password";
    private static final String TEST_USER_EMAIL = "test@gmail.com";
    private static final String TEST_USER_EMAIL_2 = "test2@gmail.com";
    private static final String TEST_NOT_EXISTING_USER_EMAIL = "notexisting@gmail.com";

    @Autowired
    private UserService userService;
    @Autowired
    private BCryptPasswordEncoder encoder;

    private User testUser;

    @Before
    public void init() {
        testUser = userService.saveUserWithRole(createTestUser(TEST_USERNAME, TEST_USER_EMAIL));
    }

    @Test
    public void test_saveUserWithRole() {
        User user = createTestUser(TEST_USERNAME_2, TEST_USER_EMAIL_2);
        assertNull(user.getRoles());

        user = userService.saveUserWithRole(user);
        Set<Role> userRoles = user.getRoles();
        assertFalse(userRoles.isEmpty());
        assertEquals(ROLE_USER_NAME, userRoles.iterator().next().getName());
    }

    @Test
    public void test_createNewRegisteredUser() {
        RegistrationForm form = new RegistrationForm();
        form.setName(TEST_USERNAME);
        form.setPassword(TEST_USER_PASSWORD);
        form.setConfirmPassword(TEST_USER_PASSWORD);
        form.setEmail(TEST_USER_EMAIL);

        User newRegisteredUser = userService.createNewRegisteredUser(form);
        assertNotNull(newRegisteredUser);
        assertEquals(TEST_USERNAME, newRegisteredUser.getUsername());
        assertEquals(TEST_USER_EMAIL, newRegisteredUser.getEmail());
        assertTrue(encoder.matches(TEST_USER_PASSWORD, newRegisteredUser.getPassword()));
    }

    @Test
    public void test_getUserByName() {
        User user = userService.getUserByName(TEST_USERNAME);
        assertNotNull(user);
        assertEquals(testUser.getEmail(), user.getEmail());
    }

    @Test
    public void test_getUserByName_returnNullIfUserNotExist() {
        User user = userService.getUserByName(TEST_NOT_EXISTING_USERNAME);
        assertNull(user);
    }

    @Test
    public void test_getUserByEmail() {
        User user = userService.getUserByEmail(TEST_USER_EMAIL);
        assertNotNull(user);
        assertEquals(testUser.getUsername(), user.getUsername());
    }

    @Test
    public void test_getUserByEmail_returnNullIfUserNotExist() {
        User user = userService.getUserByEmail(TEST_NOT_EXISTING_USER_EMAIL);
        assertNull(user);
    }

    private User createTestUser(String testUsername, String testUserEmail) {
        User testUser = new User();
        testUser.setUsername(testUsername);
        testUser.setPassword(TEST_USER_PASSWORD);
        testUser.setEmail(testUserEmail);
        return testUser;
    }
}

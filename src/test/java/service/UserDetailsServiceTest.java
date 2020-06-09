package service;


import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static utils.Constants.ROLE_USER_NAME;

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@ContextConfiguration("file:src/main/webapp/WEB-INF/test_application-context.xml")
public class UserDetailsServiceTest extends AbstractServiceTest {

    @Autowired
    private UserDetailsService userDetailsService;

    @Test
    public void test_loadUserByUsername() {
        saveTestUser();
        UserDetails userDetails = userDetailsService.loadUserByUsername(TEST_USERNAME);
        assertEquals(TEST_USERNAME, userDetails.getUsername());
        assertEquals(TEST_USER_PASSWORD, userDetails.getPassword());

        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        assertEquals(1, authorities.size());
        assertEquals(ROLE_USER_NAME, authorities.iterator().next().getAuthority());
    }

    @Test(expected = UsernameNotFoundException.class)
    public void test_loadUserByUsername_expectException_whenUserNotExist() {
        userDetailsService.loadUserByUsername(TEST_NOT_EXISTING_USERNAME);
    }
}
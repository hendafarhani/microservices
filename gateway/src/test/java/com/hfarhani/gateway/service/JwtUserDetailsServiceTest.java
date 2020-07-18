package com.hfarhani.gateway.service;

import com.hfarhani.gateway.model.JpaUser;
import com.hfarhani.gateway.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
public class JwtUserDetailsServiceTest {

    @TestConfiguration
    static class EmployeeServiceImplTestContextConfiguration {

        @Bean
        public JwtUserDetailsService employeeService() {
            return new JwtUserDetailsService();
        }
    }

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @MockBean
    private UserRepository userRepository;

    @Before
    public void setUp() {
        Calendar c = Calendar.getInstance(); // starts with today's date and time
        c.add(Calendar.DAY_OF_YEAR, 2);  // advances day by 2
        Date passwordExpirationDate = c.getTime();
        JpaUser user = new JpaUser("test", "password", passwordExpirationDate);


        Mockito.when(userRepository.findUserByUsername(user.getUsername()))
                .thenReturn(user);
    }

    @Test
    public void whenValidUsername_thenUserShouldBeFound() {
        String userName = "test";
        UserDetails foundUser = jwtUserDetailsService.loadUserByUsername(userName);

        assertThat(foundUser.getUsername())
                .isEqualTo(userName);
    }
}

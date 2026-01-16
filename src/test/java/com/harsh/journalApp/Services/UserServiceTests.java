package com.harsh.journalApp.Services;

import com.harsh.journalApp.entities.UserEntity;
import com.harsh.journalApp.repository.UserRepository;
import com.harsh.journalApp.services.UserDetailsServiceImpl;
import com.harsh.journalApp.services.UserServices;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    private UserRepository userRepo;

    @InjectMocks
    private UserDetailsServiceImpl userServiceImpl;

    @Test
    public void findByUserNameTest(){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName("Ram");
        userEntity.setPassword("Ram");
        userEntity.setRoles(List.of("USER"));

        when(userRepo.findByUserName(anyString()))
                .thenReturn(userEntity);

        UserDetails userDetails =
                userServiceImpl.loadUserByUsername("Ram");

        Assertions.assertNotNull(userDetails);
        Assertions.assertEquals("Ram", userDetails.getUsername());
        Assertions.assertTrue(
                userDetails.getAuthorities()
                        .stream()
                        .anyMatch(a -> a.getAuthority().equals("ROLE_USER")));
    }

    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,2,4"
    })
    public void addTest(int a, int b, int expected){
        Assertions.assertEquals(expected, a+b);
    }
}

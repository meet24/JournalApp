package com.example.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.api.repository.UserRepository;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByUserName() {
        assertNotNull(userRepository.findByUserName("meet"));
    }

    @ParameterizedTest
    @CsvSource({
        "1,1,2",
        "2,2,12",
        "3,3,9"
    })
    public void Test(int a, int b, int expected) {
        assertEquals(expected, a+b);
    }
}

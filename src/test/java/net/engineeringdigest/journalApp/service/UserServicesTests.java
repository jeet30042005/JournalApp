package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.Repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserServicesTests {

    @Autowired
    private UserRepository userRepository;
    @Test
    public void testfindByUserName(){
        assertNotNull(userRepository.getByUsername("jeet"));
    }
    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,10,12",
    })
    public void test(int a, int b, int expected){
        assertEquals(expected, a + b);
    }
}

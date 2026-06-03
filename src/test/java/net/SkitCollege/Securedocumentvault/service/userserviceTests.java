//package net.engineeringdigest.journalApp.service;
//
//import net.engineeringdigest.journalApp.Entity.UserEntity;
//import net.engineeringdigest.journalApp.Repository.UserRepository;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.ArgumentsSource;
//import org.junit.jupiter.params.provider.CsvSource;
//import org.junit.jupiter.params.provider.ValueSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//public class userserviceTests {
//
//    @Autowired
//    UserRepository userRepository;
////
//    @Test
//    public void testadd()
//    {
//        assertEquals(4,2+2);
//       assertNotNull(userRepository.findByEmail("rakhiparwani@gmail.com"));
//       assertTrue(5>2);
//    }
////
//    @ParameterizedTest
//    @CsvSource({
//            "1,1,2",
//            "2,2,4",
////            "3,3,0"//btadega ki ye fail hua h
//
//    })
//    public void testadd(int a,int b,int expect) {
//        assertEquals(expect, a + b);
//    }
//
////
//    @ParameterizedTest
//    @ValueSource(strings={
//              "ram",
//            "rakesh"
//
//    })
//    public void testadd(String s) {
//        assertNotNull(userRepository.findByEmail(s));
//    }
////
//    @ParameterizedTest
//    @ArgumentsSource(MyProvider.class)
//    void testEven(int num) {
//        assertTrue(num % 2 == 0);
//    }
//
//
//}

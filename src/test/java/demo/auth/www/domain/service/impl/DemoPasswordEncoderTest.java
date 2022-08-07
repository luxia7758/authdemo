package demo.auth.www.domain.service.impl;

import demo.auth.www.domain.service.PasswordEncoder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DemoPasswordEncoderTest {

    private PasswordEncoder test;

    @BeforeEach
    void init(){
        test = new DemoPasswordEncoder();
    }

    @Test
    void checkFail() {
        String encrypted = test.encrypt("test");
        assertFalse(test.check("123456",encrypted));
    }

    @Test
    void checkPass() {
        String encrypted = test.encrypt("test");
        assertTrue(test.check("test",encrypted));
    }
}
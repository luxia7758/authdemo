package demo.auth.www.domain.service.impl;

import demo.auth.www.domain.service.PasswordEncoder;

public class DemoPasswordEncoder implements PasswordEncoder {
    @Override
    public String encrypt(String rawPassword) {
        return "{cipher}" + rawPassword;
    }

    @Override
    public boolean check(String rawPassword, String encrypted) {
        return encrypted.equals("{cipher}" + rawPassword);
    }
}

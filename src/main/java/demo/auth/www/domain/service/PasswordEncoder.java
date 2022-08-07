package demo.auth.www.domain.service;


public interface PasswordEncoder {
    String encrypt(String rawString);
    boolean check(String input,String stored);


}

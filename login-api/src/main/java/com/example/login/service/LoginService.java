package com.example.login.service;


import com.example.common.domain.User;
import com.example.common.domain.UserRepository;
import com.example.common.error.EmailNotExistedException;
import com.example.common.error.PasswordWrongException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class LoginService {

    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public LoginService(UserRepository userRepository,PasswordEncoder passwordEncoder){
        this.userRepository=userRepository;
        this.passwordEncoder=passwordEncoder;
    }

    public User authenticate(String email,String password) throws PasswordWrongException {
        User user= userRepository.findByEmail(email).orElseThrow(() -> new EmailNotExistedException(email));
        if(!passwordEncoder.matches(password,user.getPassword())){
            throw new PasswordWrongException();
        }
        return user;
    }
}

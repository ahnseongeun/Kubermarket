package com.example.admin.service;

import com.example.common.domain.User;
import com.example.common.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service //자동으로 UserService 빈생성
@Slf4j
@RequiredArgsConstructor //final , @Notnull이 붙은 생성자를 자동으로 생정해준다.
@Transactional
public class UserService {

    private final UserRepository userRepository;

    /**
     * @RequiredArgsConstructor //final , @Notnull이 붙은 생성자를 자동으로 생정해준다.
     *
     * @RequiredArgsConstructor 이용하지 않았을 때.
     * private UserRepository userRepository;
     *
     * @Autowired
     * public UserService(UserRepository userRepository){
     *     this.userRepository=userRepository;
     * }
     */

    public List<User> getUsers() {
        List<User> userList= (List<User>) userRepository.findAll();
        return userList;
    }
}

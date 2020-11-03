package com.example.admin.service;

import com.example.common.domain.User;
import com.example.common.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public List<User> getUsers() {
        List<User> userList= (List<User>) userRepository.findAll();
        return userList;
    }
}

package com.example.common.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<com.example.common.domain.User,Long> {

    Optional<com.example.common.domain.User> findBynickNameOrEmail(String nickName, String Email);

    Optional<com.example.common.domain.User> findByEmail(String email);

    User findByNickName(String userId);
}

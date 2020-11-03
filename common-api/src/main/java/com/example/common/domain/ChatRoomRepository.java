package com.example.common.domain;

import org.springframework.data.repository.CrudRepository;

public interface ChatRoomRepository extends CrudRepository<ChatRoom,Long> {
    void deleteById(Long sellerId);
}

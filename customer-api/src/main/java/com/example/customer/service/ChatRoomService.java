package com.example.customer.service;

import com.example.common.domain.ChatRoom;
import com.example.common.domain.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ChatRoomService {

    private final ChatRoomRepository chatRoomRepository;

    public List<ChatRoom> getChatRooms(){
        List<ChatRoom> chatRooms= (List<ChatRoom>) chatRoomRepository.findAll();
        return chatRooms;
    }
}

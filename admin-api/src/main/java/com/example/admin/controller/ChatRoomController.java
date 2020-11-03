package com.example.admin.controller;

import com.example.common.domain.ChatRoom;
import com.example.admin.service.ChatRoomService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/api")
public class ChatRoomController {

    private final ChatRoomService chatRoomService;

    @Autowired
    public ChatRoomController(ChatRoomService chatRoomService) {
        this.chatRoomService=chatRoomService;
    }

    @ResponseBody
    @RequestMapping(value = "/chatrooms", method = RequestMethod.GET)
    @ApiOperation(value = "ChatRoom List(관리자 기능)", notes = "채팅방 목록 불러오기")
    public List<ChatRoom> list(){
        List<ChatRoom> chatRooms = chatRoomService.getChatRooms();

        return chatRooms;
    }


}

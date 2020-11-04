package com.example.common.domain;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface ChatMessageRepository extends CrudRepository<ChatMessage,Long> {
}

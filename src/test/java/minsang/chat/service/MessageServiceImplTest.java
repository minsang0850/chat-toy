package minsang.chat.service;

import minsang.chat.domain.ChatMessageDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MessageServiceImplTest {

    MessageService messageService;

    MessageServiceImplTest(@Autowired MessageService messageService) {
        this.messageService = messageService;
    }

    @Test
    void saveMessage() {
        var message = ChatMessageDTO.builder()
                .chatRoomId(1)
                .memberNo(1)
                .memberName("min")
                .text("첫번째 메시지")
                .build();
        messageService.saveMessage(message);
    }


}
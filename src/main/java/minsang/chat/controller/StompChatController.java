package minsang.chat.controller;

import minsang.chat.domain.ChatMessageDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;

@Slf4j
@RequiredArgsConstructor
@Controller
public class StompChatController {

	private final KafkaTemplate<String, ChatMessageDTO> kafkaTemplate;

    /**
	 * enter 메시지 호출 -> 입장 시 메시지를 얻음 으로 수정
	 * @param message
	 */
	@MessageMapping(value = "/chat/enter")
	public void enter(ChatMessageDTO message) {
		kafkaTemplate.send("chatRoomEnter", message);
	}

	@MessageMapping(value = "/chat/message")
	public void message(ChatMessageDTO message) {
		kafkaTemplate.send("chat", message);
	}
}

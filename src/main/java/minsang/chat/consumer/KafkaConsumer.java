package minsang.chat.consumer;

import lombok.RequiredArgsConstructor;
import minsang.chat.domain.ChatMessageDTO;
import minsang.chat.service.ChatRoomService;
import minsang.chat.service.MessageReaderService;
import minsang.chat.service.MessageService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class KafkaConsumer {

	private final SimpMessagingTemplate template;
	private final MessageService messageService;
	private final MessageReaderService messageReaderService;
	private final ChatRoomService chatRoomService;

	@KafkaListener(topics = "chat", groupId = "chat_message", containerFactory = "messageListner")
	public void consumeChat(ChatMessageDTO message) {
		//메시지 저장
		messageService.saveMessage(message);
		//웹소켓 메시지 전송
		template.convertAndSend("/sub/chat/room/" + message.chatRoomId(), message);
	}

	@KafkaListener(topics = "chatRoomEnter", groupId = "message", containerFactory = "messageListner")
	public void consumeChatRoomEnter(ChatMessageDTO param) {
		messageReaderService.enter(param);
	}

	@KafkaListener(topics = "chatRoomExit", groupId = "message", containerFactory = "messageListner")
	public void consumeChatRoomExit(ChatMessageDTO message) {
		messageReaderService.exit(message.chatRoomId(), message.memberNo());
	}
}

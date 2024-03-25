package minsang.chat.service;


import minsang.chat.domain.ChatMessageDTO;
import minsang.chat.domain.LatestMessageDTO;

import java.util.List;
import java.util.Map;

public interface MessageService {
	void saveMessage(ChatMessageDTO message);

	List<ChatMessageDTO> getPreviousMessage(long chatRoomId);

	Map<Long, LatestMessageDTO> getLatestMessagesMap(List<Long> chatRoomIds);
}

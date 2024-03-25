package minsang.chat.service;


import minsang.chat.domain.ChatMessageDTO;
import minsang.chat.entity.Message;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface MessageReaderService {

    void enter(ChatMessageDTO message);

    Map<Long, Set<Long>> findMessageReaders(List<Message> messages);

    void exit(long chatRoomId, long memberNo);

    void save(long messageId, long memberNo, long chatRoomId);
}

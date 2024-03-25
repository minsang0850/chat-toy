package minsang.chat.repository;


import minsang.chat.entity.MessageReader;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MessageReaderRepository extends MongoRepository<MessageReader, String> {

    List<MessageReader> findByMessageIdIn(List<Long> messageIds);

    void deleteAllByChatRoomIdAndMemberNo(Long chatRoomId, Long memberNo);
}

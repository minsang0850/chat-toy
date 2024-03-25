package minsang.chat.repository;

import minsang.chat.entity.Message;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface MessageMongoReposiory extends MongoRepository<Message, String> {

	List<Message> findAllByChatRoomIdOrderByCreateDate(long chatRoomId);

	long countByIdGreaterThanAndChatRoomId(long id, long chatRoomId);
}

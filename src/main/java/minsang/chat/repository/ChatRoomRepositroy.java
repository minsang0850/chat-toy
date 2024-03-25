package minsang.chat.repository;

import minsang.chat.entity.ChatRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ChatRoomRepositroy extends JpaRepository<ChatRoom, Long> {

	Optional<ChatRoom> findById(Long chatRoomId);

	@Modifying
	@Query("update ChatRoom r set r.chattersCount = r.chattersCount - 1 where r.id=:id")
	int decrementChattersCount(@Param(value = "id") long id);
}

package minsang.chat.repository;

import minsang.chat.entity.Chatter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatterRepository extends JpaRepository<Chatter, Long> {

	List<Chatter> findAllByMember_Id(Long memberNo);

	Chatter findByMember_IdAndChatRoom_Id(Long member_id, Long chatRoom_id);

	void deleteChattersByIdAndMember_Id(Long chatRoomId, long memberNo);

	Chatter findByChatRoom_IdAndMember_Id(Long chatRoomId, long memberNo);
}
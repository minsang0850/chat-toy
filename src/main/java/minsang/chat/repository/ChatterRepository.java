package minsang.chat.repository;

import minsang.chat.entity.Chatter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ChatterRepository extends JpaRepository<Chatter, Long> {

	List<Chatter> findAllByMember_MemberNo(Long memberNo);

	Chatter findByMember_MemberNoAndChatRoom_Id(Long memberNo, Long chatRoom_id);

	void deleteChattersByIdAndMember_MemberNo(Long chatRoomId, long memberNo);

	Chatter findByChatRoom_IdAndMember_MemberNo(Long chatRoomId, long memberNo);
}
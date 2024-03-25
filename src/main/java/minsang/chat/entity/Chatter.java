package minsang.chat.entity;

import jakarta.persistence.*;
import lombok.Data;
import minsang.member.entity.Members;

@Data
@Entity
public class Chatter {

	@Id
	@GeneratedValue
	@Column(name = "chatter_id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "chat_room_id")
	private ChatRoom chatRoom;

	@ManyToOne
	@JoinColumn(name = "member_no")
	private Members member;

	@Column(name = "chat_room_name")
	private String chatRoomName;

	@Column(name = "read_message_no")
	private Long readMessageNo;

	public static Chatter of(ChatRoom chatRoom, Members member, String chatRoomName) {
		var chatter = new Chatter();
		chatter.setChatRoom(chatRoom);
		chatter.setMember(member);
		chatter.setChatRoomName(chatRoomName);
		chatter.setReadMessageNo(0L);
		return chatter;
	}
}

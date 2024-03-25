package minsang.chat.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class ChatRoom {

	@Id
	@GeneratedValue
	@Column(name = "chat_room_id")
	private Long id;

	private Long chattersCount;

	@OneToMany(mappedBy = "chatRoom")
	private List<Chatter> chatters;
}

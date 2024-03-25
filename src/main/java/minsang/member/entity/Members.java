package minsang.member.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import minsang.chat.entity.Chatter;
import minsang.member.domain.MemberDTO;

import java.util.List;

@Getter
@Setter
@ToString(exclude = "chatters")
@Entity
public class Members {

	@Id
	@GeneratedValue
	@Column(name = "member_no")
	private Long id;

	@Column(name = "member_name")
	private String memberName;

	@OneToMany(mappedBy = "member")
	private List<Chatter> chatters;

	public static Members of(MemberDTO member) {
		var members = new Members();
		members.setId(member.memberNo());
		members.setMemberName(member.memberName());
		return members;
	}
}

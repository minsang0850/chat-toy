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
	private Long memberNo;

	@Column(name = "member_id")
	private String memberId;

	@Column(name = "password")
	private String password;

	@Column(name = "member_name")
	private String memberName;

	@OneToMany(mappedBy = "member")
	private List<Chatter> chatters;

	public static Members of(MemberDTO member) {
		var members = new Members();
		members.setMemberNo(member.memberNo());
		members.setMemberName(member.memberName());
		return members;
	}
}

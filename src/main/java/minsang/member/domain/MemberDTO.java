package minsang.member.domain;

import lombok.Builder;
import minsang.member.entity.Members;

@Builder
public record MemberDTO(long memberNo, String memberName) {

	public static MemberDTO of(Members member) {
		return MemberDTO.builder().memberNo(member.getId()).memberName(member.getMemberName()).build();
	}

	public static MemberDTO emptyOf() {
		return MemberDTO.builder().build();
	}
}

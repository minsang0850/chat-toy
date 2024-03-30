package minsang.member.domain;

import lombok.Builder;
import minsang.member.entity.Members;

@Builder
public record MemberDTO(long memberNo, String memberName) {

	public static MemberDTO from(Members member) {
		return MemberDTO.builder()
				.memberNo(member.getMemberNo())
				.memberName(member.getMemberName())
				.build();
	}

	public static MemberDTO emptyOf() {
		return MemberDTO.builder().build();
	}
}

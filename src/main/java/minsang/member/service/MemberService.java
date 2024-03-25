package minsang.member.service;


import minsang.member.domain.MemberDTO;

public interface MemberService {
	MemberDTO getMember(long memberNo);

	String getUserName(long memberNo);
}

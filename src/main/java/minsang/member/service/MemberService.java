package minsang.member.service;


import minsang.member.domain.MemberDTO;
import minsang.member.domain.SignInDTO;

public interface MemberService {
	MemberDTO getMember(long memberNo);

	String getUserName(long memberNo);

	MemberDTO signIn(SignInDTO signInDTO);
}

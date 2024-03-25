package minsang.member.controller;

import lombok.RequiredArgsConstructor;
import minsang.member.domain.MemberDTO;
import minsang.member.service.MemberService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Deprecated
@RequiredArgsConstructor
@RestController
public class MemberController {

	private final MemberService memberService;

	@GetMapping("/message/members/{memberNo}")
	public MemberDTO getMember(@PathVariable long memberNo) {
		if (memberNo <= 0) {
			return MemberDTO.emptyOf();
		}
		return memberService.getMember(memberNo);
	}

}

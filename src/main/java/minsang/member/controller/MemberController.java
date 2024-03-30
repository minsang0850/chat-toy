package minsang.member.controller;

import lombok.RequiredArgsConstructor;
import minsang.member.domain.MemberDTO;
import minsang.member.domain.SignInDTO;
import minsang.member.service.MemberService;
import org.springframework.web.bind.annotation.*;

@Deprecated
@RequiredArgsConstructor
@RestController
public class MemberController {

	private final MemberService memberService;

	@PostMapping("/sign-in")
	public MemberDTO signIn(@RequestBody SignInDTO signInDTO) {
		return memberService.signIn(signInDTO);
	}

}

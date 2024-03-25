package minsang.chat.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import minsang.chat.service.ChatRoomService;
import minsang.chat.service.MessageService;
import minsang.member.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Deprecated
@Slf4j
@RequiredArgsConstructor
@Controller
public class ChatRoomContoller {

	private final MemberService memberService;
	private final ChatRoomService chatRoomService;
	private final MessageService messageService;

	@GetMapping("/message/chatRooms/{chatRoomId}")
	public String chatGET(@PathVariable long chatRoomId, @RequestParam("memberNo") long memberNo, Model model) {

		var userName = memberService.getUserName(memberNo);
		var chatRoom = chatRoomService.getChatRoom(chatRoomId, memberNo);
		var previousMessages = messageService.getPreviousMessage(chatRoomId);
		model.addAttribute("userName", userName);
		model.addAttribute("userNo", memberNo);
		model.addAttribute("room", chatRoom);
		model.addAttribute("previousMessages", previousMessages);
		return "room";
	}
}

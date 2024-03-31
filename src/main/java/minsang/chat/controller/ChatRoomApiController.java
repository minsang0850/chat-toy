package minsang.chat.controller;

import lombok.RequiredArgsConstructor;
import minsang.chat.controller.dto.ChatRoomExitParam;
import minsang.chat.controller.dto.ChatRoomRegisterParam;
import minsang.chat.controller.dto.ChatRoomRegisterResponse;
import minsang.chat.controller.dto.ChatRoomWithMessageDTO;
import minsang.chat.domain.ChatMessageDTO;
import minsang.chat.domain.ChatRoomDetails;
import minsang.chat.service.ChatRoomService;
import minsang.chat.service.MessageService;
import minsang.member.service.MemberService;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class ChatRoomApiController {

    private final ChatRoomService chatRoomService;
    private final MessageService messageService;
    private final MemberService memberService;
    private final KafkaTemplate<String, ChatMessageDTO> kafkaTemplate;

    @GetMapping("/message/v1/chatRooms")
    public ChatRoomDetails getChatRoomsWithMessage(@RequestParam("memberNo") long memberNo) {
        return chatRoomService.getChatRoomsWithMessage(memberNo);
    }

    @GetMapping("/message/v1/chatRooms/{chatRoomId}")
    public ChatRoomWithMessageDTO getChatRoomWithMessage(@PathVariable("chatRoomId") long chatRoomId,
                                                         @RequestParam("memberNo") long memberNo) {
        var chatRoom = chatRoomService.getChatRoom(chatRoomId, memberNo);
        var messages = messageService.getPreviousMessage(chatRoomId);
        var member = memberService.getMember(memberNo);
        return ChatRoomWithMessageDTO.of(chatRoom, messages, member);
    }

    @PostMapping("/message/v1/chatRooms")
    public ChatRoomRegisterResponse registerChatRoom(@RequestBody ChatRoomRegisterParam param) {
        return chatRoomService.registerChatRoom(param);
    }

    @PostMapping("/message/v1/chatRooms/{chatRoomId}/exit")
    public void exitChatRoom(@PathVariable("chatRoomId") long chatRoomId,
                             @RequestBody ChatRoomExitParam param) {
        chatRoomService.exit(chatRoomId, param.memberNo());
        kafkaTemplate.send("chatRoomExit", ChatMessageDTO.of(chatRoomId, param.memberNo()));
    }

}

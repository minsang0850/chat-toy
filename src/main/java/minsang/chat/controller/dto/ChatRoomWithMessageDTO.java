package minsang.chat.controller.dto;

import lombok.Builder;
import minsang.chat.domain.ChatMessageDTO;
import minsang.chat.domain.ChatRoomDetail;
import minsang.member.domain.MemberDTO;

import java.util.List;

@Builder
public record ChatRoomWithMessageDTO(
        ChatRoomDetail chatRoom,
        List<ChatMessageDTO> messages,
        MemberDTO member
) {

    public static ChatRoomWithMessageDTO of(ChatRoomDetail chatRoom, List<ChatMessageDTO> messages, MemberDTO member) {
        return ChatRoomWithMessageDTO.builder()
                .chatRoom(chatRoom)
                .messages(messages)
                .member(member)
                .build();
    }
}

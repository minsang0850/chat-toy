package minsang.chat.controller.dto;

import lombok.Builder;
import minsang.chat.domain.ChatMessageDTO;
import minsang.chat.domain.ChatRoomSummary;
import minsang.member.domain.MemberDTO;

import java.util.List;

@Builder
public record ChatRoomWithMessageDTO(ChatRoomSummary chatRoom, List<ChatMessageDTO> messages, MemberDTO member) {

    public static ChatRoomWithMessageDTO of(ChatRoomSummary chatRoom, List<ChatMessageDTO> messages, MemberDTO member) {
        return ChatRoomWithMessageDTO.builder()
                .chatRoom(chatRoom)
                .messages(messages)
                .member(member)
                .build();
    }
}

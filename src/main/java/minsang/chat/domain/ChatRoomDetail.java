package minsang.chat.domain;

import lombok.Builder;
import minsang.chat.entity.Chatter;

import java.util.List;

@Builder
public record ChatRoomDetail(long chatRoomId,
                             String chatRoomName,
                             List<ChatMessageDTO> messages,
                             long chattersCount
) {

    public static ChatRoomDetail of(long chatRoomId,
                                    String chatRoomName,
                                    List<ChatMessageDTO> messages,
                                    long chattersCount) {
        return ChatRoomDetail.builder()
                .chatRoomId(chatRoomId)
                .chatRoomName(chatRoomName)
                .messages(messages)
                .chattersCount(chattersCount)
                .build();
    }

    public static ChatRoomDetail of(Chatter chatRoom, Long chattersCount) {
        return ChatRoomDetail.builder()
                .chatRoomId(chatRoom.getChatRoom().getId())
                .chatRoomName(chatRoom.getChatRoomName())
                .chattersCount(chattersCount)
                .build();
    }

    public static ChatRoomDetail emptyOf() {
        return ChatRoomDetail.builder().build();
    }

}

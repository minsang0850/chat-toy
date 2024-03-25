package minsang.chat.domain;

import lombok.Builder;
import lombok.With;
import minsang.chat.entity.Chatter;

@Builder
public record ChatRoomSummary(long chatRoomId,
                              String chatRoomName,
                              long chattersCount,
                              @With LatestMessageDTO latestMessage,
                              String unReadCount) {

    public static ChatRoomSummary of(long chatRoomId,
                                     String chatRoomName,
                                     long chattersCount,
                                     LatestMessageDTO latestMessage,
                                     long unReadCount) {
        return ChatRoomSummary.builder()
                .chatRoomId(chatRoomId)
                .chatRoomName(chatRoomName)
                .chattersCount(chattersCount)
                .latestMessage(latestMessage)
                .unReadCount(String.valueOf(unReadCount))
                .build();
    }

    public static ChatRoomSummary of(Chatter chatRoom, Long chattersCount) {
        return ChatRoomSummary.builder()
                .chatRoomId(chatRoom.getChatRoom().getId())
                .chatRoomName(chatRoom.getChatRoomName())
                .chattersCount(chattersCount)
                .build();
    }

    public static ChatRoomSummary emptyOf() {
        return ChatRoomSummary.builder().build();
    }

}

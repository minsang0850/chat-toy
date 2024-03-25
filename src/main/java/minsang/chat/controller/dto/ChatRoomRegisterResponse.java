package minsang.chat.controller.dto;

import lombok.Builder;

@Builder
public record ChatRoomRegisterResponse(
        long chatRoomId,
        String chatRoomName,
        long chattersCount
) {
    public static ChatRoomRegisterResponse of(long chatRoomId,
                                              String chatRoomName,
                                              long chattersCount) {
        return ChatRoomRegisterResponse.builder()
                .chatRoomId(chatRoomId)
                .chatRoomName(chatRoomName)
                .chattersCount(chattersCount)
                .build();
    }
}

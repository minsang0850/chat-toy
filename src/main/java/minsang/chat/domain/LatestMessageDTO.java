package minsang.chat.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import minsang.chat.entity.Message;
import minsang.util.MessageDateFormatter;

@Builder
public record LatestMessageDTO(
        long chatRoomId,
        String text,
        String createdate
) {

    public static LatestMessageDTO of(Message message) {
        return LatestMessageDTO.builder()
                .chatRoomId(message.getChatRoomId())
                .text(message.getText())
                .createdate(MessageDateFormatter.aHmmToString(message.getCreateDate()))
                .build();
    }

    public static LatestMessageDTO emptyOf() {
        return LatestMessageDTO.builder()
                .build();
    }

    @JsonIgnore
    public boolean isNotEmpty() {
        return chatRoomId != 0;
    }
}

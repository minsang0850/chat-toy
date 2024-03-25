package minsang.chat.domain;

import lombok.Builder;
import minsang.chat.entity.Message;
import minsang.util.MessageDateFormatter;

import java.util.Set;

@Builder
public record ChatMessageDTO(
        long chatRoomId,
        long memberNo,
        String memberName,
        String text,
        String createdate,
        Set<Long> readerNos
) {

    public static ChatMessageDTO of(Message message, Set<Long> readerNos) {
        return ChatMessageDTO.builder()
                .chatRoomId(message.getChatRoomId())
                .memberNo(message.getMemberNo())
                .memberName(message.getMemberName())
                .text(message.getText())
                .createdate(MessageDateFormatter.aHmmToString(message.getCreateDate()))
                .readerNos(readerNos)
                .build();
    }

    public static ChatMessageDTO of(long chatRoomId, long memberNo) {
        return ChatMessageDTO.builder()
                .chatRoomId(chatRoomId)
                .memberNo(memberNo)
                .build();
    }
}

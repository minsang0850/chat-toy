package minsang.chat.domain;

import lombok.Builder;
import minsang.chat.entity.ChatRoom;
import minsang.chat.entity.Chatter;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

@Builder
public record ChatRoomDetails(List<ChatRoomDetail> chatRoomDetails) {

    public static ChatRoomDetails of(List<Chatter> chatters,
                                     Map<Long, ChatRoom> chatRoomsMap,
                                     Map<Long, List<ChatMessageDTO>> messagesMap) {
        var chatRoomSummaries = chatters.stream()
                .map(chatter -> {
                    var chatRoomId = chatter.getChatRoom() == null ? 0 : chatter.getChatRoom().getId();
                    var messages = messagesMap.get(chatRoomId);
                    return ChatRoomDetail.of(chatRoomId,
                            chatter.getChatRoomName(),
                            messages,
                            chatRoomsMap.get(chatRoomId).getChattersCount());
                })
                .toList();
        return ChatRoomDetails.of(chatRoomSummaries);
    }

    public static ChatRoomDetails of(List<ChatRoomDetail> chatRoomSummaries) {
        return ChatRoomDetails.builder()
                .chatRoomDetails(chatRoomSummaries)
                .build();
    }

    public int getSize() {
        if (CollectionUtils.isEmpty(chatRoomDetails)) {
            return 0;
        }
        return this.chatRoomDetails.size();
    }
}

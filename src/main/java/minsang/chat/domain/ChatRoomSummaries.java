package minsang.chat.domain;

import lombok.Builder;
import minsang.chat.entity.ChatRoom;
import minsang.chat.entity.Chatter;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

@Builder
public record ChatRoomSummaries(List<ChatRoomSummary> chatRoomSummaries) {

    public static ChatRoomSummaries of(List<Chatter> chatters,
                                       Map<Long, ChatRoom> chatRoomsMap,
                                       Map<Long, LatestMessageDTO> latestMessagesMap,
                                       Map<Long, Long> unReadCountsMap) {
        var chatRoomSummaries = chatters.stream()
                .map(chatter -> {
                    var chatRoomId = chatter.getChatRoom() == null ? 0 : chatter.getChatRoom().getId();
                    return ChatRoomSummary.of(chatRoomId,
                            chatter.getChatRoomName(),
                            chatRoomsMap.get(chatRoomId).getChattersCount(),
                            latestMessagesMap.get(chatRoomId),
                            unReadCountsMap.get(chatRoomId));
                })
                .toList();
        return ChatRoomSummaries.of(chatRoomSummaries);
    }

    public static ChatRoomSummaries of(List<ChatRoomSummary> chatRoomSummaries) {
        return ChatRoomSummaries.builder()
                .chatRoomSummaries(chatRoomSummaries)
                .build();
    }

    public int getSize() {
        if (CollectionUtils.isEmpty(chatRoomSummaries)) {
            return 0;
        }
        return this.chatRoomSummaries.size();
    }
}

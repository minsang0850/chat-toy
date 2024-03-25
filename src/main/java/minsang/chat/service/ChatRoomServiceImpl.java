package minsang.chat.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import minsang.chat.controller.dto.ChatRoomRegisterParam;
import minsang.chat.controller.dto.ChatRoomRegisterResponse;
import minsang.chat.domain.ChatRoomSummaries;
import minsang.chat.domain.ChatRoomSummary;
import minsang.chat.entity.ChatRoom;
import minsang.chat.entity.Chatter;
import minsang.chat.repository.ChatRoomRepositroy;
import minsang.chat.repository.ChatterRepository;
import minsang.chat.repository.MessageMongoReposiory;
import minsang.member.entity.Members;
import minsang.member.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.testng.internal.collections.Pair;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class ChatRoomServiceImpl implements ChatRoomService {

    private final ChatterRepository chatterRepository;
    private final ChatRoomRepositroy chatRoomRepositroy;
    private final MemberRepository memberRepository;
    private final MessageService messageService;
    private final MessageMongoReposiory messageMongoReposiory;

    /**
     * 채팅방들의 기본 정보를 가져옴
     * 최근 메시지 Map, 안읽은 메시지 Map, 채팅방들을 가져와서 summaries로 리턴
     *
     * @param memberNo
     * @return
     */
    @Override
    public ChatRoomSummaries getChatRoomSummaries(long memberNo) {
        var chatters = chatterRepository.findAllByMember_Id(memberNo);
        var chatRoomIds = chatters.stream()
                .map(chatter -> chatter.getChatRoom().getId())
                .toList();
        var chatRoomsMap = chatters.stream()
                .map(Chatter::getChatRoom)
                .filter(Objects::nonNull)
                .collect(Collectors.toMap(ChatRoom::getId, Function.identity()));
        var latestMessagesMap = messageService.getLatestMessagesMap(chatRoomIds);
        var unReadCountsMap = getUnReadCountsMap(chatters);
        return ChatRoomSummaries.of(chatters, chatRoomsMap, latestMessagesMap, unReadCountsMap);
    }

    private Map<Long, Long> getUnReadCountsMap(List<Chatter> chatters) {
        return chatters.stream()
                .map(chatter -> new Pair<>(chatter.getChatRoom().getId(),
                        messageMongoReposiory.countByIdGreaterThanAndChatRoomId(
                                chatter.getReadMessageNo(),
                                chatter.getChatRoom().getId())))
                .collect(Collectors.toMap(Pair<Long, Long>::first, Pair<Long, Long>::second));
    }

    @Override
    @Transactional
    public ChatRoomSummary getChatRoom(long chatRoomId, long memberNo) {
        var chatRoom = chatRoomRepositroy.findById(chatRoomId);
        if (chatRoom.isEmpty()) {
            return ChatRoomSummary.emptyOf();
        }
        var chatter = chatterRepository.findByMember_IdAndChatRoom_Id(memberNo, chatRoomId);
        return ChatRoomSummary.of(chatter, chatRoom.get().getChattersCount());
    }

    @Override
    @Transactional
    public ChatRoomRegisterResponse registerChatRoom(ChatRoomRegisterParam param) {
        var members = getChatMembers(param);
        if (CollectionUtils.isEmpty(members)) {
            return null;
        }
        var chatRoom = new ChatRoom();
        chatRoom.setChattersCount((long) members.size());
        chatRoomRepositroy.save(chatRoom);
        chatterRepository.saveAll(members.stream()
                .map(member -> Chatter.of(chatRoom, member, param.chatRoomName()))
                .toList());
        return ChatRoomRegisterResponse.of(chatRoom.getId(), param.chatRoomName(), chatRoom.getChattersCount());
    }

    private List<Members> getChatMembers(ChatRoomRegisterParam param) {
        var userOptional = memberRepository.findById(param.memberNo());
        if (userOptional.isEmpty()) {
            return Collections.emptyList();
        }
        var user = userOptional.get();
        var members = memberRepository.findAllById(param.memberNos());
        members.add(user);
        return members;
    }

    @Override
    @Transactional
    public void exit(long chatRoomId, long memberNo) {
        chatterRepository.deleteChattersByIdAndMember_Id(chatRoomId, memberNo);
        chatRoomRepositroy.decrementChattersCount(chatRoomId);
    }
}

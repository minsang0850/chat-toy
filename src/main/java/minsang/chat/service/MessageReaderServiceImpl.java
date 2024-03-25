package minsang.chat.service;

import lombok.RequiredArgsConstructor;
import minsang.chat.domain.ChatMessageDTO;
import minsang.chat.entity.Message;
import minsang.chat.entity.MessageReader;
import minsang.chat.repository.ChatterRepository;
import minsang.chat.repository.MessageMongoReposiory;
import minsang.chat.repository.MessageReaderRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MessageReaderServiceImpl implements MessageReaderService {

    private final MessageReaderRepository messageReaderRepository;
    private final MessageMongoReposiory messageMongoReposiory;
    private final ChatterRepository chatterRepository;

    @Override
    /**
     * 1. 채팅방에 들어온 유저가 읽지 않은 메시지들을 가져옴
     * 2. messageReader에 저장
     * 3. chatter의 latestMessage 업데이트
     */
    public void enter(ChatMessageDTO param) {
        var messages = messageMongoReposiory.findAllByChatRoomIdOrderByCreateDate(param.chatRoomId());
        if (CollectionUtils.isEmpty(messages)) {
            return;
        }
        saveMessageReaders(param.memberNo(), messages);
        var latestMessage = messages.get(messages.size() - 1);
        updateReadMessageNo(param, latestMessage);
    }

    /**
     * 전달받은 메시지들을 읽음표시
     *
     * @param memberNo 회원 번호
     * @param messages 메시지
     */
    private void saveMessageReaders(long memberNo, List<Message> messages) {
        var messageReaders = messages.stream()
                .map(message -> MessageReader.of(message.getId(), memberNo, message.getChatRoomId()))
                .toList();
        messageReaderRepository.saveAll(messageReaders);
    }

    /**
     * chatter의 readMessageNo를 update
     *
     * @param param
     * @param latestMessage
     */
    private void updateReadMessageNo(ChatMessageDTO param, Message latestMessage) {
        var chatter = chatterRepository.findByChatRoom_IdAndMember_Id(
                param.chatRoomId(),
                param.memberNo());
        chatter.setReadMessageNo(latestMessage.getId());
        chatterRepository.save(chatter);
    }

    @Override
    public Map<Long, Set<Long>> findMessageReaders(List<Message> messages) {
        var messageReaders = messageReaderRepository.findByMessageIdIn(
                messages.stream()
                        .map(Message::getId)
                        .toList());
        return messageReaders.stream()
                .collect(Collectors.groupingBy(MessageReader::getMessageId,
                        Collectors.mapping(MessageReader::getMemberNo, Collectors.toSet())));
    }

    @Override
    public void exit(long chatRoomId, long memberNo) {
        messageReaderRepository.deleteAllByChatRoomIdAndMemberNo(chatRoomId, memberNo);
    }

    @Override
    public void save(long messageId, long memberNo, long chatRoomId) {
        messageReaderRepository.save(MessageReader.of(messageId, memberNo, chatRoomId));
    }
}

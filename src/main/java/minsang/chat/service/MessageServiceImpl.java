package minsang.chat.service;

import lombok.RequiredArgsConstructor;
import minsang.chat.domain.ChatMessageDTO;
import minsang.chat.domain.LatestMessageDTO;
import minsang.chat.entity.Message;
import minsang.chat.repository.ChatterRepository;
import minsang.chat.repository.MessageMongoReposiory;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MessageServiceImpl implements MessageService {

    private final MessageMongoReposiory messageMongoReposiory;
    private final SequenceGeneratorService sequenceGeneratorService;
    private final MessageReaderService messageReaderService;
    private final ChatterRepository chatterRepository;

    /**
     * 1. 메시지 저장
     * 2. update readMessageNo
     * 3. save messageReader
     *
     * @param message
     */
    @Override
    public void saveMessage(ChatMessageDTO message) {
        Message savedMessage = getSavedMessage(message);
        var chatter = chatterRepository.findByChatRoom_IdAndMember_MemberNo(message.chatRoomId(), message.memberNo());
        chatter.setReadMessageNo(savedMessage.getId());
        chatterRepository.save(chatter);
        messageReaderService.save(savedMessage.getId(), savedMessage.getMemberNo(), savedMessage.getChatRoomId());
    }

    private Message getSavedMessage(ChatMessageDTO message) {
        var messageEntity = Message.of(message);
        messageEntity.setId(sequenceGeneratorService.generateSequence(Message.SEQUENCE_NAME));
        var savedMessage = messageMongoReposiory.save(messageEntity);
        return savedMessage;
    }

    @Override
    public List<ChatMessageDTO> getPreviousMessage(long chatRoomId) {
        var previousMessages = messageMongoReposiory.findAllByChatRoomIdOrderByCreateDate(chatRoomId);
        var messageReadersMap = messageReaderService.findMessageReaders(previousMessages);
        return previousMessages.stream()
                .map(message -> ChatMessageDTO.of(message, messageReadersMap.get(message.getId())))
                .toList();
    }

    @Override
    public Map<Long, LatestMessageDTO> getLatestMessagesMap(List<Long> chatRoomIds) {
        return chatRoomIds.stream()
                .map(chatRoomId -> {
                    var messages = messageMongoReposiory.findAllByChatRoomIdOrderByCreateDate(chatRoomId);
                    if (CollectionUtils.isEmpty(messages)) {
                        return LatestMessageDTO.emptyOf();
                    }
                    return LatestMessageDTO.of(messages.get(messages.size() - 1));
                })
                .filter(LatestMessageDTO::isNotEmpty)
                .collect(Collectors.toMap(LatestMessageDTO::chatRoomId, Function.identity()));
    }
}

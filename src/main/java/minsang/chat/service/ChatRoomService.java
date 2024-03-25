package minsang.chat.service;


import minsang.chat.controller.dto.ChatRoomRegisterParam;
import minsang.chat.controller.dto.ChatRoomRegisterResponse;
import minsang.chat.domain.ChatRoomSummaries;
import minsang.chat.domain.ChatRoomSummary;

public interface ChatRoomService {

	ChatRoomSummaries getChatRoomSummaries(long memberNo);

	ChatRoomSummary getChatRoom(long chatRoomId, long memberNo);

	ChatRoomRegisterResponse registerChatRoom(ChatRoomRegisterParam param);

	void exit(long chatRoomId, long memberNo);
}

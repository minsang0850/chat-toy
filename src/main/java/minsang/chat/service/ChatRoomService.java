package minsang.chat.service;


import minsang.chat.controller.dto.ChatRoomRegisterParam;
import minsang.chat.controller.dto.ChatRoomRegisterResponse;
import minsang.chat.domain.ChatRoomDetails;
import minsang.chat.domain.ChatRoomDetail;

public interface ChatRoomService {

	ChatRoomDetails getChatRoomsWithMessage(long memberNo);

	ChatRoomDetail getChatRoom(long chatRoomId, long memberNo);

	ChatRoomRegisterResponse registerChatRoom(ChatRoomRegisterParam param);

	void exit(long chatRoomId, long memberNo);
}

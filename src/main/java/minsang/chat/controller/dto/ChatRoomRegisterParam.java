package minsang.chat.controller.dto;

import java.util.List;

public record ChatRoomRegisterParam(long memberNo,
                                    String chatRoomName,
                                    List<Long> memberNos) {
}

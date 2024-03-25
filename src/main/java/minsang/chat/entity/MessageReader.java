package minsang.chat.entity;

import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Builder
@Data
@Document(collection = "message_reader")
public class MessageReader {

    @Id
    private String id;

    @Field("message_id")
    private long messageId;

    @Field("member_no")
    private long memberNo;

    @Field("chat_room_id")
    private long chatRoomId;

    @Field("message_create_date")
    @CreatedDate
    private Date messageCreateDate;

    public static MessageReader of(long messageId, long memberNo, long chatRoomId) {
        return MessageReader.builder()
                .messageId(messageId)
                .memberNo(memberNo)
                .chatRoomId(chatRoomId)
                .build();
    }
}

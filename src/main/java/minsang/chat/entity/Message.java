package minsang.chat.entity;

import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Builder;
import lombok.Data;
import minsang.chat.domain.ChatMessageDTO;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Builder
@Data
@Document(collection = "message")
public class Message {

    @Transient
    public static final String SEQUENCE_NAME = "message_sequence";

    @Id
    private long id;

    @Field("text")
    private String text;

    @Field("member_no")
    private Long memberNo;

    @Field("member_name")
    private String memberName;

    @Field("chat_room_id")
    private Long chatRoomId;

    @Field("create_date")
    @CreatedDate
    private Date createDate;

    public static Message of(ChatMessageDTO message) {
        return Message.builder()
                .text(message.text())
                .memberNo(message.memberNo())
                .memberName(message.memberName())
                .chatRoomId(message.chatRoomId())
                .createDate(new Date())
                .build();
    }
}

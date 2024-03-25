package minsang.chat.entity.seq;

import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document(collection = "message_sequence")
public class MessageSequence {

    @Id
    private String id;

    private long seq;
}

package minsang.chat.service;

import lombok.RequiredArgsConstructor;
import minsang.chat.entity.seq.MessageSequence;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Objects;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

@RequiredArgsConstructor
@Service
public class SequenceGeneratorService {

    private final MongoOperations mongoOperations;

    public long generateSequence(String seqName) {
        MessageSequence counter = mongoOperations.findAndModify(query(where("_id").is(seqName)),
                new Update().inc("seq", 1), options().returnNew(true).upsert(true),
                MessageSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }
}

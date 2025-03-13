package io.sample.playground.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "messages")
@CompoundIndex(def = "{'name':1, 'parentId':1}", background = true)
public class MongoMessageDTO implements Serializable {

    @Id
    private String id;

    private String name;

    private String parentId;

    private String getId() {
        return UUID.randomUUID().toString();
    }

}

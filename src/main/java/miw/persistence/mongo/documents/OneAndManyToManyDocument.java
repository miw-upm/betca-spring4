package miw.persistence.mongo.documents;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class OneAndManyToManyDocument {

    @Id
    private String id;

    private String nick;

    @DBRef
    private List<AnyDocument> anyDocumentList;

    public OneAndManyToManyDocument(String nick, List<AnyDocument> anyDocumentList) {
        this.nick = nick;
        this.anyDocumentList = anyDocumentList;
    }

    @Override
    public String toString() {
        return "OneToManyDocument [id=" + id + ", nick=" + nick + ", anyDocumentList=" + anyDocumentList + "]";
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (getClass() != obj.getClass()) {
            return false;
        } else {
            return id == ((OneAndManyToManyDocument) obj).id;
        }
    }

    public String getId() {
        return id;
    }

    public String getNick() {
        return nick;
    }

    public List<AnyDocument> getAnyDocumentList() {
        return anyDocumentList;
    }

}

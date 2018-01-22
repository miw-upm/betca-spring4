package miw.persistence.mongo;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Mobile {

    @Id
    private String id;

    private Type type;

    private Date date;

    public Mobile(String id, Type type) {
        super();
        this.id = id;
        this.type = type;
        this.date = new Date();
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        String time = new SimpleDateFormat("dd-MMM-yyyy").format(this.date.getTime());
        return "Mobile [id=" + id + ", type=" + type + ", date=" + time + "]";
    }

}

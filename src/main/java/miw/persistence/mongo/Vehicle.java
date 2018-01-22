package miw.persistence.mongo;

import java.util.Arrays;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Vehicle {

    @Id
    private String id;

    @Indexed(unique = true)
    private String register;

    private Integer[] serie = {23846, 34347, 9812};
    
    public Vehicle(String register) {
        super();
        this.register = register;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRegister() {
        return register;
    }

    public void setRegister(String register) {
        this.register = register;
    }

    public Integer[] getSerie() {
        return serie;
    }

    public void setSerie(Integer[] serie) {
        this.serie = serie;
    }


    @Override
    public String toString() {
        return "Vehicle [id=" + id + ", register=" + register + ", serie=" + Arrays.toString(serie) + "]";
    }

}

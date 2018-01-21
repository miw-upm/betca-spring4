package miw.persistence.mongo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User {
    // @Document para identificar un objeto de dominio que se persistirá en MongoDB.
    // @Field para nombrar la clave que se utilizará para el campo en el documento.
    // @Id para especificar el identificador del documento.
    // @DBRef para almacenar un puntero a un documento en lugar de incrustarlo.
    // @TypeAlias para reemplazar el nombre de clase completamente calificado con un valor diferente.
    // @PersistenceConstructor para nominar un constructor que se utilizará al crear un objeto desde la base de datos.
    // @Indexed para elegir qué campos se indexarán y si se permiten o no valores duplicados.
    // @Transient – this annotation excludes the field where it is applied from being stored in the database
    // @Value
    @Id
    private String id;

    private String name;

    private String description;
    
    private Address address;
    
    @DBRef
    private Vehicle vehicle;
    
    @DBRef
    private List<Mobile> mobileList;

    public User(String name, String description, Address address, Vehicle vehicle) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.vehicle = vehicle;
        this.mobileList= new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public List<Mobile> getMobileList() {
        return mobileList;
    }

    public void setMobileList(List<Mobile> mobileList) {
        this.mobileList = mobileList;
    }

    @Override
    public String toString() {
        return "User [id=" + id + ", name=" + name + ", description=" + description + ", address=" + address + ", vehicle=" + vehicle
                + ", mobileList=" + mobileList + "]";
    }

}

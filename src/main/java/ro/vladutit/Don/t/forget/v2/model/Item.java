package ro.vladutit.Don.t.forget.v2.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table (name = "Items")
public class Item {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    private Long codeBarId;
    private String name;
    private String description;
    //Todo: de schimbat tipul din string in date
    private String expirationDate;

    public Item() {

    }

    public Item(Long codeBarId, String name, String description, String expirationDate) {
        this.codeBarId = codeBarId;
        this.name = name;
        this.description = description;
        this.expirationDate = expirationDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCodeBarId() {
        return codeBarId;
    }

    public void setCodeBarId(Long codeBarId) {
        this.codeBarId = codeBarId;
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

    //todo: de schimbat din string in date
    public String getExpirationDate() {
        return expirationDate;
    }

    //todo: de primit un date si facut treaba cu exceptiile de format de data
    // sau de primit in continuare un string dar de transformat in format de data
    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
}

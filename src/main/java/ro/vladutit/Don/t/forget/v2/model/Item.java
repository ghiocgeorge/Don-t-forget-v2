package ro.vladutit.Don.t.forget.v2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Item {
    //Todo: schimbam numele din PRODUCT in ITEM si ID-ul nu mai este un CODEBAR,
    // ci o sa fie un ID care sa creasca singur la fiecare adaugare
    @Id
    private long codeBarId;
    private String name;
    private String description;
    //Todo: de schimbat tipul din string in date
    private String expirationDate;

    public Item() {

    }

    public Item(long codeBarId, String name, String description, String expirationDate) {
        this.codeBarId = codeBarId;
        this.name = name;
        this.description = description;
        this.expirationDate = expirationDate;
    }

    public long getCodeBarId() {
        return codeBarId;
    }

    public void setCodeBarId(long codeBarId) {
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

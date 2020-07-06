package ro.vladutit.Don.t.forget.v2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Product {
    @Id
    private long codeBarId;
    private String name;
    private String description;
    private Date expirationDate;

    public Product() {

    }

    public Product(long codeBarId, String name, String description, Date expirationDate) {
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

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }
}

package ro.vladutit.Don.t.forget.v2.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table (name = "Item")
public class Item {
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;
    private Long codeBarId;
    @NotNull
    @Size(min = 3, max = 40, message = "The item name must be {min} to {max} characters in length!")
    private String name;
    private String description = "No description";
    @NotNull
    private String expirationDate;

    @ManyToOne
    public Category category;

    public Item() {

    }

    public Item(Long codeBarId, String name, String description, String expirationDate, Long categoryId,
                String categoryName, String categoryDescription) {
        this.codeBarId = codeBarId;
        this.name = name;
        if(!description.equals("")) {
            this.description = description;
        }
        this.expirationDate = expirationDate;
        this.category = new Category(categoryId, categoryName, categoryDescription);
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
}

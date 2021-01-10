package ro.vladutit.Don.t.forget.v2.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @Size(min = 3, max = 40)
    private String name;
    private String description;
    @NotNull
    private String expirationDate;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "category_id")
    public Category category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    public User user;

    public Item() {

    }

    public Item(Long id,
                Long codeBarId,
                String name,
                String description,
                String expirationDate,
                Category category,
                User user) {
        this.id = id;
        this.codeBarId = codeBarId;
        this.name = name;
        this.description = description;
        this.expirationDate = expirationDate;
        this.category = category;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

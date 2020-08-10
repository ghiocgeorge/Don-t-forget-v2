package ro.vladutit.Don.t.forget.v2.model;

import net.bytebuddy.implementation.bind.annotation.Default;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table (name = "Category")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Size(min = 3, max = 20, message = "The category name must be {min} to {max} characters in length!")
    @Column(unique = true)
    private String name;
    private String description = "No description";

    public Category() {

    }

    public Category(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        if(!description.equals("")) {
            this.description = description;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}

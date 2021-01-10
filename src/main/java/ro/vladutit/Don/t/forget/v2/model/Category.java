package ro.vladutit.Don.t.forget.v2.model;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
    @Size(min = 3, max = 17)
    @Column(unique = true)
    private String name;
    @NotNull
    private String description;
    private Icon icon;
    private Color color;

    @ManyToOne
    @JoinColumn (name = "user_id")
    public User user;

    public Category() {

    }

    public Category(Long id, String name, String description, Icon icon, Color color, User user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.color = color;
        this.user = user;
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

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}

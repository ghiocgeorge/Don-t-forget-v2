package ro.vladutit.Don.t.forget.v2.model;

import ro.vladutit.Don.t.forget.v2.annotation.ValidTelephone;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty(message = "Please type your first and last name!")
    private String fullname;

    @ValidTelephone
    private String telephone;

    @Column(unique = true)
    private String email;

    private String password;

    private String role = "NORMAL";

    public User() {
    }

    public User(Long id, String username, String password, String fullname, String telephone, String email,
                String role) {
        this.id = id;
        this.password = password;
        this.fullname = fullname;
        this.telephone = telephone;
        this.email = email;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

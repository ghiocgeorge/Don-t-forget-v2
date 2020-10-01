package ro.vladutit.Don.t.forget.v2.model;

import javax.persistence.*;
import java.sql.ConnectionBuilder;

import static org.springframework.security.core.userdetails.User.builder;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fullname;

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

    public static org.springframework.security.core.userdetails.User.UserBuilder withUsername(String email) {
        return builder().username(email);
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

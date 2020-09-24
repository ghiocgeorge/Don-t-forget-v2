package ro.vladutit.Don.t.forget.v2.model;

import ro.vladutit.Don.t.forget.v2.annotation.MatchPassword;
import ro.vladutit.Don.t.forget.v2.annotation.ValidEmail;
import ro.vladutit.Don.t.forget.v2.annotation.ValidPassword;
import ro.vladutit.Don.t.forget.v2.annotation.ValidTelephone;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@MatchPassword
public class UserData implements Serializable {

    @NotEmpty(message = "Please type your first and last name!")
    private String fullname;

    @ValidTelephone
    private String telephone;

    @ValidEmail
    private String email;

    @ValidPassword
    private String password;

    @NotEmpty(message = "Please retype the password!")
    private String matchingPassword;

    @NotNull
    @AssertTrue(message = "Please read and accept the terms!")
    private boolean terms;

    private String role = "NORMAL";

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isTerms() {
        return terms;
    }

    public void setTerms(boolean terms) {
        this.terms = terms;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }
}

package ro.vladutit.Don.t.forget.v2.model;

import ro.vladutit.Don.t.forget.v2.annotation.MatchNewPassword;
import ro.vladutit.Don.t.forget.v2.annotation.ValidPassword;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@MatchNewPassword
public class PasswordDto implements Serializable {

    @NotEmpty(message = "Please type your old password!")
    private String oldPassword;

    @ValidPassword
    private String newPassword;

    @NotEmpty(message = "Please confirm the new password!")
    private String matchingPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getMatchingPassword() {
        return matchingPassword;
    }

    public void setMatchingPassword(String matchingPassword) {
        this.matchingPassword = matchingPassword;
    }
}

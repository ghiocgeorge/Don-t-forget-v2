package ro.vladutit.Don.t.forget.v2.validation;

import ro.vladutit.Don.t.forget.v2.annotation.MatchPassword;
import ro.vladutit.Don.t.forget.v2.model.UserData;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordMatchesValidator implements ConstraintValidator<MatchPassword, Object> {

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        final UserData user = (UserData) obj;
        return user.getPassword().equals(user.getMatchingPassword());
    }
}

package ro.vladutit.Don.t.forget.v2.validation;

import ro.vladutit.Don.t.forget.v2.annotation.MatchNewPassword;
import ro.vladutit.Don.t.forget.v2.model.PasswordDto;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PasswordNewMatchesValidator implements ConstraintValidator<MatchNewPassword, Object> {
    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        final PasswordDto user = (PasswordDto) obj;
        return user.getNewPassword().equals(user.getMatchingPassword());
    }
}
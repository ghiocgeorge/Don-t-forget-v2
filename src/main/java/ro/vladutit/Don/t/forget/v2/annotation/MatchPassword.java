package ro.vladutit.Don.t.forget.v2.annotation;

import ro.vladutit.Don.t.forget.v2.validation.PasswordMatchesValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ TYPE, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = PasswordMatchesValidator.class)
@Documented
public @interface MatchPassword {

    String message() default "Passwords do not match!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
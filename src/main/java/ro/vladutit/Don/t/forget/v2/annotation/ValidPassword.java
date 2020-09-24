package ro.vladutit.Don.t.forget.v2.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = { })
@Pattern(regexp="(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{8,}")
@NotEmpty
@Target({ TYPE, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@ReportAsSingleViolation
public @interface ValidPassword {

    String message() default "Password must contain at least one number and " +
            "one uppercase and lowercase letter, and at least 8 or more characters!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

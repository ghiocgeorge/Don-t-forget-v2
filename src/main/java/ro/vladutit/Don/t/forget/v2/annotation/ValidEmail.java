package ro.vladutit.Don.t.forget.v2.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = { })
@Email
@NotEmpty
@Pattern(regexp=".+@.+\\..+")
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER })
@Retention(RUNTIME)
@ReportAsSingleViolation
public @interface ValidEmail {

    String message() default "Please provide a valid email address (name@domain.some)!";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
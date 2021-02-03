package ro.vladutit.Don.t.forget.v2.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Pattern;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Constraint(validatedBy = { })
@Pattern(regexp="(\\+\\d{0,20})")
@Target({ TYPE, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@ReportAsSingleViolation
public @interface ValidTelephone {

    String message() default "Telephone must contain only maxim 20 digits and the country prefix (Ex: +4 for Romania)!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

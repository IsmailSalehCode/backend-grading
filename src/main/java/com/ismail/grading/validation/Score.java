/**
 * The Score annotation is intended to be applied to fields in
 * Java classes. When applied, it signals to the validation framework that the
 * annotated field should be validated using the logic defined in the
 * ScoreValidator class.
 */

package com.ismail.grading.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

// can only be applied to fields
@Target(ElementType.FIELD)
// validation dynamically at runtime.
@Retention(RetentionPolicy.RUNTIME)
// Specifies the validator class (ScoreValidator.class) that will be used to
// validate the annotated field. It tells the validation framework which class
// should perform the actual validation logic.
@Constraint(validatedBy = ScoreValidator.class)

public @interface Score {
    // default error message
    String message() default "The score is not valid";

    // I need the following two method declarations. Otherwise bad app behavior.
    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

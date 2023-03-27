package com.elliot.mall.demo.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Constraint(validatedBy = FlagValidatorClass.class)
public @interface FlagValidator {
	String[] value() default {};

	String message() default "flag is not found";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}

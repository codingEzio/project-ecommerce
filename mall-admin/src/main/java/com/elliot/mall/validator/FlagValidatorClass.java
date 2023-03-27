package com.elliot.mall.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * This is a Java class named FlagValidatorClass that implements the ConstraintValidator interface from the validation API in Java. The ConstraintValidator interface is used to implement custom validation logic for a custom validation annotation, in this case the @FlagValidator annotation.
 * It kinda looks like enum validation, but with custom logic. I might just switch this to it in the future.
 */
public class FlagValidatorClass implements ConstraintValidator<FlagValidator, Integer> {
	private String[] values;

	@Override
	public void initialize(FlagValidator constraintAnnotation) {
		this.values = constraintAnnotation.value();
	}

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		boolean isValid = false;

		if (value == null) {
			return true;
		}

		for (String s: values) {
			if (s.equals(String.valueOf(value))) {
				isValid = true;
				break;
			}
		}

		return isValid;
	}
}

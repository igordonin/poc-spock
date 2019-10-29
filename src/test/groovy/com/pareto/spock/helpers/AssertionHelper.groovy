package com.pareto.spock.helpers

import javax.validation.Validator
import javax.validation.ValidatorFactory

import static javax.validation.Validation.buildDefaultValidatorFactory

class AssertionHelper {

    static ValidatorFactory factory
    static Validator validator

    static {
        factory = buildDefaultValidatorFactory()
        validator = factory.getValidator()
    }

    static def assertValidationError(def domain, String fieldName, String errorCode) {
        def errors = validator.validate(domain)
        def fieldError = errors.find { it.propertyPath.toString() == fieldName }
        fieldError != null && fieldError.messageTemplate == errorCode
    }

    static def assertValidationHasNoErrors(def domain, String fieldName) {
        def errors = validator.validate(domain)
        def fieldError = errors.find { it.propertyPath.toString() == fieldName }
        fieldError == null
    }

    static def assertValidationHasNoErrors(def domain) {
        validator.validate(domain).size() <= 0
    }

    static final NOT_NULL = "{javax.validation.constraints.NotNull.message}"
    static final NOT_BLANK = "{javax.validation.constraints.NotBlank.message}"
    static final MIN = "{javax.validation.constraints.Min.message}"
}

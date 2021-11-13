package com.finalprojultimate.controller.validation;

import java.util.List;

public interface Validator<T> {
    boolean isValid(T object);
    List<String> getErrorMessages();
    List<String> getErrorValidationMessages();
}

package com.lemkor.api.SensorApi.utils;

import com.lemkor.api.SensorApi.exceptions.MeasurementException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;

public class ErrorsUtil {
    public static void returnErrorsToClient(BindingResult bindingResult) {
        StringBuilder error = new StringBuilder();

        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            error.append(fieldError.getField()).append(" : ").append(fieldError.getDefaultMessage()).append("; ");
        }

        throw new MeasurementException(error.toString());
    }
}

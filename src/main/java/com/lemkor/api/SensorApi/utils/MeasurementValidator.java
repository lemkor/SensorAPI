package com.lemkor.api.SensorApi.utils;

import com.lemkor.api.SensorApi.models.Measurement;
import com.lemkor.api.SensorApi.services.SensorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class MeasurementValidator implements Validator {
    private final SensorService sensorService;

    @Autowired
    public MeasurementValidator(SensorService sensorService, ModelMapper modelMapper) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Measurement.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Measurement measurement = (Measurement) target;

        if(measurement.getSensor() == null) {
            return;
        }

        if(sensorService.findByName(measurement.getSensor().getName()).isEmpty()) {
            errors.rejectValue("sensor",null, "No sensor with name "+measurement.getSensor().getName()+" found");
        }

    }
}

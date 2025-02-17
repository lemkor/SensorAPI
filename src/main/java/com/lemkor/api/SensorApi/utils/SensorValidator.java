package com.lemkor.api.SensorApi.utils;

import com.lemkor.api.SensorApi.models.Sensor;
import com.lemkor.api.SensorApi.services.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class SensorValidator implements Validator {
    private final SensorService sensorService;

    @Autowired
    public SensorValidator(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return Sensor.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Sensor sensor = (Sensor) target;
        if(sensorService.findByName(sensor.getName()).isPresent()) {
            errors.rejectValue("name", null, "Sensor with name " + sensor.getName() + " already exists");
        }
    }
}

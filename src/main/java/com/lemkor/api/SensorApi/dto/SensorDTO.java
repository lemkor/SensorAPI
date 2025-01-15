package com.lemkor.api.SensorApi.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class SensorDTO {

    @Column(name = "name")
    @NotEmpty(message = "sensor name can not be empty")
    @Size(min = 3, max = 30, message = "sensor name must be between 2 and 30 characters")
    private String name;

    public @NotEmpty(message = "sensor name can not be empty") @Size(min = 3, max = 30, message = "sensor name must be between 2 and 30 characters") String getName() {
        return name;
    }

    public void setName(@NotEmpty(message = "sensor name can not be empty") @Size(min = 3, max = 30, message = "sensor name must be between 2 and 30 characters") String name) {
        this.name = name;
    }
}

package com.lemkor.api.SensorApi.repositories;

import com.lemkor.api.SensorApi.models.Measurement;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MeasurementRepository extends JpaRepository<Measurement, Integer> {
    int countAllByRaining(@NotNull boolean raining);
}

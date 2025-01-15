package com.lemkor.api.SensorApi.controllers;

import com.lemkor.api.SensorApi.dto.MeasurementDTO;
import com.lemkor.api.SensorApi.exceptions.MeasurementException;
import com.lemkor.api.SensorApi.models.Measurement;
import com.lemkor.api.SensorApi.services.MeasurementService;
import com.lemkor.api.SensorApi.services.SensorService;
import com.lemkor.api.SensorApi.utils.MeasurementErrorResponse;
import com.lemkor.api.SensorApi.utils.MeasurementValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import static com.lemkor.api.SensorApi.utils.ErrorsUtil.returnErrorsToClient;

@RestController
@RequestMapping("/measurements")
public class MeasurementController {
    private final MeasurementService measurementService;
    private final ModelMapper modelMapper;
    private final MeasurementValidator measurementValidator;
    private final SensorService sensorService;


    @Autowired
    public MeasurementController(MeasurementService measurementService, ModelMapper modelMapper, MeasurementValidator measurementValidator, SensorService sensorService) {
        this.measurementService = measurementService;
        this.modelMapper = modelMapper;
        this.measurementValidator = measurementValidator;
        this.sensorService = sensorService;
    }

    @PostMapping("/add")
    public HttpEntity<HttpStatus> create(@RequestBody @Valid MeasurementDTO measurementDTO,
                                         BindingResult bindingResult) {
        Measurement measurementToAdd = convertToMeasurement(measurementDTO);

        measurementValidator.validate(measurementToAdd, bindingResult);
        if(bindingResult.hasErrors()) {
            returnErrorsToClient(bindingResult);
        }

        measurementService.save(measurementToAdd);

        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping()
    public List<MeasurementDTO> getAllMeasurements() {
        return measurementService.findAll().stream().map(this::convertToMeasurementDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/rainyDaysCount")
    public int getAllRainyDays() {
        return measurementService.countAllByRaining(true);
    }

    @ExceptionHandler
    public ResponseEntity<MeasurementErrorResponse> handleException(MeasurementException e) {
        MeasurementErrorResponse response = new MeasurementErrorResponse(e.getMessage(), new Date());

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Measurement convertToMeasurement(MeasurementDTO measurementDTO) {
        return modelMapper.map(measurementDTO, Measurement.class);
    }

    private MeasurementDTO convertToMeasurementDTO(Measurement measurement) {
        return modelMapper.map(measurement, MeasurementDTO.class);
    }

}

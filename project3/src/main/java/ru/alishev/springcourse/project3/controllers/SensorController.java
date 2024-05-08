package ru.alishev.springcourse.project3.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import ru.alishev.springcourse.project3.models.Sensor;
import ru.alishev.springcourse.project3.services.SensorService;
import ru.alishev.springcourse.project3.util.PersonErrorResponse;
import ru.alishev.springcourse.project3.util.PersonNotCreatedException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    private final SensorService sensorService;

    public SensorController(final SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @GetMapping
    public String getAllSensors() {
        return "HELLO";
    }

    @PostMapping("/registration")
    public ResponseEntity<HttpStatus> registerSensor(@RequestBody @Valid Sensor sensor,
                                                     BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            StringBuilder errorsMsg = new StringBuilder();

            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError fieldError : fieldErrors) {
                errorsMsg.append(fieldError.getField())
                        .append(" - ").append(fieldError.getDefaultMessage())
                        .append(";\n");
            }

            throw new PersonNotCreatedException(errorsMsg.toString());
        }

        sensorService.save(sensor);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler
    public ResponseEntity<HttpStatus> handleException(PersonNotCreatedException ex) {
        PersonErrorResponse error = new PersonErrorResponse(ex.getMessage());
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}

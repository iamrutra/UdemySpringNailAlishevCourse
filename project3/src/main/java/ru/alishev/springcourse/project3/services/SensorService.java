package ru.alishev.springcourse.project3.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alishev.springcourse.project3.models.Sensor;
import ru.alishev.springcourse.project3.repositories.SensorRepository;

@Service
@Transactional(readOnly = true)
public class SensorService {

    private final SensorRepository sensorRepository;

    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    @Transactional
    public Sensor save(Sensor sensor) {
        return sensorRepository.save(sensor);
    }
}

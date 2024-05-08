package ru.alishev.springcourse.project3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.alishev.springcourse.project3.models.Sensor;

@Repository
public interface SensorRepository extends JpaRepository<Sensor, Integer> {

}

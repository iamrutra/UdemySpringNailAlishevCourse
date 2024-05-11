package ru.alishev.springcourse.project3.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "sensor")
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    @NotEmpty(message = "Название сенсора не должно быть пустым")
    @Size(min = 3, max = 30, message = "Навзвание сенсора должно содердать от 3 до 30 символов")
    private String name;

    @OneToMany(mappedBy = "sensor")
    private List<Measurement> measurement;

    public Sensor(String name) {
        this.name = name;
    }

    public Sensor() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

package ru.alishev.springcourse.project3.models;


import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "sensor")
public class Sensor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

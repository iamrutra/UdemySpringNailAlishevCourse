package ru.alishev.springcourse.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ru.alishev.springcourse.models.Person;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Neil Alishev
 */
@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Person show(String email) {
        return jdbcTemplate.query("Select * from Person where email =?", new Object[]{email},
                new BeanPropertyRowMapper<Person>(Person.class)).stream().findFirst().orElse(null);
    }

    public List<Person> index() {
        return jdbcTemplate.query("SELECT * FROM person", new PersonMapper());
    }

    public Person show(int id) {
        return jdbcTemplate.query("SELECT * FROM person WHERE id = ?", new Object[]{id}, new PersonMapper())
                .stream().findAny().orElse(null);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person VALUES (1,?,?,?)", person.getName(),person.getAge(), person.getEmail());
    }

    public void update(int id, Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person Set name=?, age=?, email=? WHERE id=?",
                updatedPerson.getName(), updatedPerson.getAge(), updatedPerson.getEmail(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id=?", id);
    }

    public void testMultipleUpdate(){
        List<Person>people = create1000people();

        long start = System.currentTimeMillis();

        for(Person person : people){
            jdbcTemplate.update("INSERT INTO Person VALUES (?,?,?,?)", person.getId(), person.getName(),person.getAge(), person.getEmail());
        }

        long end = System.currentTimeMillis();
    }

    public void testBatchUpdate(){
        List<Person>people = create1000people();
        long start = System.currentTimeMillis();

        jdbcTemplate.batchUpdate("INSERT INTO Person VALUES(?,?,?,?)", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                ps.setInt(1, people.get(i).getId());
                ps.setString(2, people.get(i).getName());
                ps.setInt(3, people.get(i).getAge());
                ps.setString(4, people.get(i).getEmail());
            }

            @Override
            public int getBatchSize() {
                return people.size();
            }
        });

        long end = System.currentTimeMillis();
    }

    private List<Person> create1000people(){
        List<Person> people = new ArrayList<Person>();
        for (int i = 0; i < 1000; i++) {
            people.add(new Person(i, "Name: " + i, 30, "test"+i+"@gmail.com", "USA"));
        }
        return people;
    }
}

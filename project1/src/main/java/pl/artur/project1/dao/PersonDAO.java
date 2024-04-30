package pl.artur.project1.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import pl.artur.project1.model.Person;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;

@Component
public class PersonDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public Person show(int id) {
        return jdbcTemplate.query("Select * from person where id = ?", new Object[]{id}, new BeanPropertyRowMapper<>(Person.class))
                .stream().findFirst().orElse(null);
    }

    public List<Person> getAll() {
        return jdbcTemplate.query("select * from person order by id", new BeanPropertyRowMapper<>(Person.class));
    }

    public void delete(int id){
        jdbcTemplate.update("delete from person where id = ?", id);
    }
    public void newPerson(Person person){
        jdbcTemplate.update("insert into person (fio, year) values (?, ?)", person.getFio(), person.getYear());
    }
    public void edit(Person person){
        jdbcTemplate.update("update person set fio = ?, year = ? where id = ?", person.getFio(), person.getYear(), person.getId());
    }
}

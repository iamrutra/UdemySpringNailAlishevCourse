package ru.alishev.springcourse.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import ru.alishev.springcourse.dao.PersonDAO;
import ru.alishev.springcourse.models.Person;

import javax.validation.Validator;

public abstract class PersonValidator implements Validator {

    private final PersonDAO personDAO;

    public PersonValidator(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public boolean supports(Class<?> aClass) {
        return Person.class.equals(aClass);
    }

    public void validate(Object o, Errors errors) {
        Person person = (Person) o;

        if(personDAO.show(person.getEmail()) != null){
            errors.rejectValue("email", "", "This email address is already in use");
        }
    }
}

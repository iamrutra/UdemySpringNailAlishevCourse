package ru.alishev.springcourse.FirstSecurityApp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alishev.springcourse.FirstSecurityApp.models.Person;
import ru.alishev.springcourse.FirstSecurityApp.repositories.PeopleRepository;

@Service
public class RegistrService  {

    private final PeopleRepository personRepository;
    private final PeopleRepository peopleRepository;

    @Autowired
    public RegistrService(PeopleRepository personRepository, PeopleRepository peopleRepository) {
        this.personRepository = personRepository;
        this.peopleRepository = peopleRepository;
    }

    @Transactional
    public void register(Person person){
        peopleRepository.save(person);
    }
}

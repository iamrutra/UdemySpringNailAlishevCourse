package ru.alishev.springcourse.dao;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.alishev.springcourse.models.Person;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class PersonDAO {

    private final EntityManager entityManager;

    @Autowired
    public PersonDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional(readOnly = true)
    public void tesetNPlus1(){
        Session session = entityManager.unwrap(Session.class);
//        List<Person> people = session.createQuery("from Person").getResultList();
//
//        //N
//        people.forEach(person -> System.out.println("Person: " + person.getName() + " has: " + person.getItems()));
        Set<Person> people = new HashSet<>(session.createQuery("Select p from Person p left join fetch p.items")
                .getResultList());
        people.forEach(person -> System.out.println("Person: " + person.getName() + " has: " + person.getItems()));
    }
}

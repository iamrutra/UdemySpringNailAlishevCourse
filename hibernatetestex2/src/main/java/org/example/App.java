package org.example;


import org.example.model.Principal;
import org.example.model.School;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Principal.class)
                .addAnnotatedClass(School.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        session.beginTransaction();

//        School school = session.get(School.class, 1);
//        Principal principal = session.get(Principal.class, 1);
//        System.out.println(school.getPrincipal());

        School school = session.get(School.class, 3);
        Principal principal = session.get(Principal.class, 1);
        principal.setSchool(school);
        session.save(principal);

        session.getTransaction().commit();
    }
}

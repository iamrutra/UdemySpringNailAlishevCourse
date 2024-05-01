package org.example;


import org.example.model.Director;
import org.example.model.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Director.class)
                .addAnnotatedClass(Movie.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try{
            session.beginTransaction();

            Director director = new Director("Test cascading5", 30);
            director.addMovie(new Movie("Movie1", 2001));
            director.addMovie(new Movie("Movie2", 2002));
            director.addMovie(new Movie("Movie3", 2003));

            session.save(director);
            session.getTransaction().commit();
        }
        finally {
            sessionFactory.close();
        }
    }
}

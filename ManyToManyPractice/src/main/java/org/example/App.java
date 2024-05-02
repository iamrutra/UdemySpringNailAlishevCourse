package org.example;


import org.example.model.Actor;
import org.example.model.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration()
                .addAnnotatedClass(Actor.class)
                .addAnnotatedClass(Movie.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();

        try(sessionFactory) {
            session.beginTransaction();

            Actor actor = session.get(Actor.class, 2);
            Movie movie = actor.getMovies().get(0);

            actor.getMovies().remove(0);

            movie.getActors().remove(actor);


            session.getTransaction().commit();
        }
    }
}

package org.example;


import org.example.model.Director;
import org.example.model.Movie;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;


public class App {
    public static void main(String[] args) {
        Configuration configuration = new Configuration().addAnnotatedClass(Director.class)
                .addAnnotatedClass(Movie.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();

        try{
            session.beginTransaction();

//            Director director = session.get(Director.class, 1);
//            System.out.println(director.getMovies());
//            Movie movie = session.get(Movie.class, 11);
//            System.out.println(movie.getDirector());

//            Director director = session.get(Director.class, 6);
//            Movie movie = new Movie("Interstellar", 2014, director);
//            session.save(movie);

//            Director director = new Director("QUENTIN TARANTINO", 61, new ArrayList<>());
//            Movie movie = new Movie("Pulp Fiction", 1994, director);
//
//            director.getMovies().add(movie);
//
//            session.save(director);
//            session.save(movie);


//            Movie movie = session.get(Movie.class, 9);
//            movie.setDirector(session.get(Director.class, 3));
//
//            session.save(movie);


            Movie movie = session.get(Movie.class, 1);

            session.remove(movie);

            session.getTransaction().commit();
        }
        finally {
            sessionFactory.close();
        }
    }
}

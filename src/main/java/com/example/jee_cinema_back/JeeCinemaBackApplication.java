package com.example.jee_cinema_back;

import com.example.jee_cinema_back.entities.Film;
import com.example.jee_cinema_back.entities.Salle;
import com.example.jee_cinema_back.entities.Seance;
import com.example.jee_cinema_back.entities.Ticket;
import com.example.jee_cinema_back.services.ICinemaInitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

@SpringBootApplication
public class JeeCinemaBackApplication implements CommandLineRunner {

    @Autowired
    private ICinemaInitService cinemaInitService;

    @Autowired
    private RepositoryRestConfiguration restConfiguration;

    public static void main(String[] args) {
        SpringApplication.run(JeeCinemaBackApplication.class, args);
    }

    @Override
    public void run(String... args) {

        restConfiguration.exposeIdsFor(Film.class, Seance.class, Salle.class, Ticket.class);
        cinemaInitService.initVilles();
        cinemaInitService.initCinemas();
        cinemaInitService.initSalles();
        cinemaInitService.initPlaces();
        cinemaInitService.initSeances();
        cinemaInitService.initCategories();
        cinemaInitService.initFilms();
        cinemaInitService.initProjections();
        cinemaInitService.initTickets();

    }
}

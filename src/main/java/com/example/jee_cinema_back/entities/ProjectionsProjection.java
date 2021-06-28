package com.example.jee_cinema_back.entities;

import java.util.Collection;
import java.util.Date;
import org.springframework.data.rest.core.config.Projection;

@Projection(types = {Projection.class}, name = "p1")
public interface ProjectionsProjection {
    Long getId();
    Date getDateProjection();
    double getPrix();
    Salle getSalle();
    Film getFilm();
    Collection<Ticket> getTickets();
    Seance getSeance();
}

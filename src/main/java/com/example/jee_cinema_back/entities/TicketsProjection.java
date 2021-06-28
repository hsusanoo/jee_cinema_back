package com.example.jee_cinema_back.entities;
import org.springframework.data.rest.core.config.Projection;

@Projection(types={Ticket.class}, name="p1")
public interface TicketsProjection {
    Long getId();
    String getNomClient();
    double getPrix();
    Integer getCodePayement();
    boolean getReserve();
    Place getPlace();
}

package com.example.jee_cinema_back.dao;

import com.example.jee_cinema_back.entities.Place;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin(origins = "*", allowedHeaders = "*")
public interface PlaceRepository extends JpaRepository<Place, Long> {
}

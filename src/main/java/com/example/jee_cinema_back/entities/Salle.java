package com.example.jee_cinema_back.entities;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Salle implements Serializable {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int nombrePlace;
    @ManyToOne
    private Cinema cinema;
    @OneToMany(mappedBy="salle")
    @ToString.Exclude
    private Collection<Place> places;
    @OneToMany(mappedBy="salle")
    @ToString.Exclude
    private Collection<Projection> projections;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Salle salle = (Salle) o;

        return Objects.equals(id, salle.id);
    }

    @Override
    public int hashCode() {
        return 377807618;
    }
}

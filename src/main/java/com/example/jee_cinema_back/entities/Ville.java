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
public class Ville implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double longitude, latidude, altitude;
    @OneToMany(mappedBy = "ville")
    @ToString.Exclude
    private Collection<Cinema> cinemas;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Ville ville = (Ville) o;

        return Objects.equals(id, ville.id);
    }

    @Override
    public int hashCode() {
        return 433904934;
    }
}

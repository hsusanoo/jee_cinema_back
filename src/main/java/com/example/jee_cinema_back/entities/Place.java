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
public class Place  implements Serializable {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    private int numero;
    private double longitude,latidude,altitude;
    @ManyToOne
    private Salle salle;
    @OneToMany(mappedBy="place")
    @ToString.Exclude
    private Collection<Ticket> tickets;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Place place = (Place) o;

        return Objects.equals(id, place.id);
    }

    @Override
    public int hashCode() {
        return 1945780767;
    }
}

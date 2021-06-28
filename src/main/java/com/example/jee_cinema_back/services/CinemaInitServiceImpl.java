package com.example.jee_cinema_back.services;

import com.example.jee_cinema_back.dao.*;
import com.example.jee_cinema_back.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Service
public class CinemaInitServiceImpl implements ICinemaInitService {

    @Autowired
    private CategorieRepository categorieRepository;
    @Autowired
    private CinemaRepository cinemaRepository;
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private ProjectionRepository projectionRepository;
    @Autowired
    private SalleRepository salleRepository;
    @Autowired
    private SeanceRepository seanceRepository;
    @Autowired
    private TicketRepository ticketRepository;
    @Autowired
    private VilleRepository villeRepository;

    @Override
    public void initVilles() {
        Stream.of("tokyo", "osaka", "conan", "hokkaidou").forEach(nameVille -> {
            Ville ville = new Ville();
            ville.setName(nameVille);
            villeRepository.save(ville);
        });

    }

    @Override
    public void initCinemas() {
        villeRepository.findAll().forEach(v -> Stream.of("Nakama", "yokosuka", "Nagoya", "Okinanwa", "kyouto").
                forEach(nameCinema -> {
                    Cinema cinema = new Cinema();
                    cinema.setName(nameCinema);
                    cinema.setNombreSalles(3 + (int) (Math.random() * 7));
                    cinema.setVille(v);
                    cinemaRepository.save(cinema);
                }));
    }

    @Override
    public void initSalles() {
        cinemaRepository.findAll().forEach(cinema -> {
            for (int i = 0; i < cinema.getNombreSalles(); i++) {
                Salle salle = new Salle();
                salle.setName("Salle " + (i + 1));
                salle.setCinema(cinema);
                salle.setNombrePlace(15 + (int) (Math.random() * 10));
                salleRepository.save(salle);
            }
        });

    }

    @Override
    public void initPlaces() {
        salleRepository.findAll().forEach(salle -> {
            for (int i = 0; i < salle.getNombrePlace(); i++) {
                Place place = new Place();
                place.setNumero(i + 1);
                place.setSalle(salle);
                placeRepository.save(place);
            }

        });
    }

    @Override
    public void initSeances() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
        Stream.of("10:00", "12:00", "14:00", "16:00", "18:00").forEach(s -> {
            Seance seance = new Seance();
            try {
                seance.setHeureDebut(dateFormat.parse(s));
                seanceRepository.save(seance);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void initCategories() {
        Stream.of("Anime", "Drama", "Action", "Adventure").forEach(cat -> {
            Categorie categorie = new Categorie();
            categorie.setName(cat);
            categorieRepository.save(categorie);
        });
    }

    @Override
    public void initFilms() {
        double[] duration = new double[]{1, 1.5, 2, 2.5, 3};
        List<Categorie> categories = categorieRepository.findAll();
        Stream.of("君の名は", "バケモノの子", "進撃の巨人", "青い空")
                .forEach(title -> {
                    Film film = new Film();
                    film.setTitre(title);
                    film.setDuree(duration[new Random().nextInt(duration.length)]);
                    film.setPhoto(title.replaceAll(" ", "_") + ".jpg");
                    film.setCategorie(categories.get(new Random().nextInt(categories.size())));
                    filmRepository.save(film);
                });
    }

    @Override
    public void initProjections() {
        double[] prices = {400, 450, 600, 1000};
        List<Film> films = filmRepository.findAll();

        villeRepository.findAll().forEach(ville -> ville.getCinemas().forEach(cinema ->
                cinema.getSalles().forEach(salle ->
                {
                    int index = new Random().nextInt(films.size());
                    Film film = films.get(index);
                    seanceRepository.findAll().forEach(seance -> {
                        Projection projection = new Projection();
                        projection.setDateProjection(new Date());
                        projection.setFilm(film);
                        projection.setPrix(prices[new Random().nextInt(prices.length)]);
                        projection.setSalle(salle);
                        projection.setSeance(seance);
                        projectionRepository.save(projection);
                    });
                })));
    }

    @Override
    public void initTickets() {
        projectionRepository.findAll().forEach(p -> p.getSalle().getPlaces().forEach(place -> {
            Ticket ticket = new Ticket();
            ticket.setPlace(place);
            ticket.setPrix(p.getPrix());
            ticket.setProjection(p);
            ticket.setReserve(false);
            ticketRepository.save(ticket);
        }));
    }

}

package kd.dev.service;

import kd.dev.dao.*;
import kd.dev.entities.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

@Service
@Transactional
public class CinemaInitServiceImpl implements ICinemaInitService {

    Logger log = LoggerFactory.getLogger(CinemaInitServiceImpl.class);

    @Autowired
    private VilleRepository villeRepository;
    @Autowired
    private CinemaRepository cinemaRepository;
    @Autowired
    private SalleRepository salleRepository;
    @Autowired
    private SeanceRepository seanceRepository;
    @Autowired
    private PlaceRepository placeRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private ProjectionRepository projectionRepository;
    @Autowired
    private TickeRepository tickeRepository;

    @Override
    public void initVilles() {
        Stream.of("Montreal", "Laval", "Quebec", "Lévis", "Ottawa").forEach( v ->{
            Ville ville = new Ville();
            ville.setName(v);
            villeRepository.save(ville);
        });
    }

    @Override
    public void initCinemas() {
        villeRepository.findAll().forEach(ville -> {
            Stream.of("IMax", "Gozzo", "Clap","Gaumont","UGC").forEach(cine ->{
                Cinema cinema = new Cinema();
                cinema.setName(cine);
                cinema.setVille(ville);
                cinema.setNombreSalle(3+(int)Math.random()*7);
                cinemaRepository.save(cinema);
            });
        });
    }

    @Override
    public void initSalles() {
        cinemaRepository.findAll().forEach( cinema -> {
            for(int i =0 ; i<cinema.getNombreSalle(); i++){
                Salle salle  = new Salle();
                salle.setName("salle"+1);
                salle.setCinema(cinema);
                salle.setNombrePlace(15+(int)Math.random()*20);
                salleRepository.save(salle);
            }
        });
    }


    @Override
    public void initPlaces() {

        salleRepository.findAll().forEach(salle -> {
            for(int i = 0 ; i < salle.getNombrePlace(); i++){
                Place place = new Place();
                place.setSalle(salle);
                place.setNumero(i+1);
                placeRepository.save(place);
            }
        });
    }

    @Override
    public void initSeances() {
        DateFormat df = new SimpleDateFormat("HH:mm");
        Stream.of("12:00","15:00","17:00","19:00","21:00").forEach(sc ->{
            Seance seance = new Seance();
            try {
                seance.setHeureDebut(df.parse(sc));
                seanceRepository.save(seance);
            } catch (ParseException e) {
                log.info(e.getMessage());
            }
        });
    }

    @Override
    public void initCategories() {
        Stream.of("Histoire", "Actions", "Fiction", "Drama").forEach( cat ->{
            Categorie categorie = new Categorie();
            categorie.setName(cat);
            categoryRepository.save(categorie);
        });
    }

    @Override
    public void initFilms() {
        double[] duree = {1,1.5,2,2.5,3,3.5};
        List<Categorie> categories = categoryRepository.findAll();
        Stream.of("spiderman homecoming", "end game", "superman vs batman", "wonder woman", "X-Men", "parrain").forEach(f->{
            Film film = new Film();
            film.setTitre(f);
            film.setDuree(duree[new Random().nextInt(duree.length)]);
            film.setPhoto(f+".jpg");
            film.setCategorie(categories.get(new Random().nextInt(categories.size())));
            filmRepository.save(film);
        });
    }

    @Override
    public void initProjections() {
        double[] prix = {7, 8, 9, 10, 11, 12,13,14,15};
        List<Film> films = filmRepository.findAll();
        villeRepository.findAll().forEach(ville -> {
            ville.getCinemas().forEach(cinema -> {
                cinema.getSalles().forEach(salle -> {
                    int index = new Random().nextInt(films.size());
                    Film film = films.get(index);
                    seanceRepository.findAll().forEach(seance -> {
                        Projection projection = new Projection();
                        projection.setFilm(film);
                        projection.setPrix(prix[new Random().nextInt(prix.length)]);
                        projection.setSalle(salle);
                        projection.setSeance(seance);
                        projectionRepository.save(projection);
                    });
                });
            });
         });
    }

    @Override
    public void initTickets() {
        projectionRepository.findAll().forEach(projection -> {
           projection.getSalle().getPlaces().forEach(place -> {
               Ticket ticket = new Ticket();
               ticket.setPlace(place);
               ticket.setPrix(projection.getPrix());
               ticket.setProjection(projection);
               ticket.setReserved(false);
               tickeRepository.save(ticket);
           });
        });
    }
}

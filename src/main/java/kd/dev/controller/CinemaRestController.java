package kd.dev.controller;

import kd.dev.dao.FilmRepository;
import kd.dev.dao.TickeRepository;
import kd.dev.entities.Film;
import kd.dev.entities.Ticket;
import kd.dev.util.TicketForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin("*")
public class CinemaRestController {

    @Value("${kd.path.image}")
    private String pathImages;
    @Autowired
    private FilmRepository filmRepository;


    @Autowired
    private TickeRepository tickeRepository;

    @GetMapping(value = "/imageFilm/{id}", produces = MediaType.IMAGE_JPEG_VALUE)
    public byte[] image(@PathVariable(name = "id") Long id) throws Exception{
        Film film = filmRepository.findById(id).get();
        String namePhoto = film.getPhoto();
        File file = new File(System.getProperty("user.home")+pathImages+namePhoto);
        Path imageFileName = Paths.get(file.toURI());
        return Files.readAllBytes(imageFileName);
    }

    @PostMapping(value = "/payerTickets")
    public List<Ticket> payerTicket(@RequestBody TicketForm ticketForm){
        List<Ticket> tickets = new ArrayList<>();
        ticketForm.getTickets().forEach(id ->{
            Ticket ticket = tickeRepository.getOne(id);
            ticket.setNomClient(ticketForm.getNomClient());
            ticket.setCodepaiement(ticketForm.getCodePaiement());
            ticket.setReserved(true);
            tickeRepository.save(ticket);
            tickets.add(ticket);
        });
        return  tickets;
    }
}

package kd.dev.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "ticketProj" , types = Ticket.class)
public interface TicketProjection {
    public Long getId();
    public String getNomClient();
    public double getPrix();
    public int getCodepaiement();
    public boolean isReserved();
    public Place getPlace();
}
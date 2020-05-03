package kd.dev.util;

import java.util.ArrayList;
import java.util.Collection;

public class TicketForm {
    private String nomClient;
    private int codePaiement;
    private Collection<Long> tickets = new ArrayList<>();

    public TicketForm() {
    }
    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public int getCodePaiement() {
        return codePaiement;
    }

    public void setCodePaiement(int codePaiement) {
        this.codePaiement = codePaiement;
    }

    public void setTickets(Collection<Long> tickets) {
        this.tickets = tickets;
    }

    public Collection<Long> getTickets() {
        return tickets;
    }

}

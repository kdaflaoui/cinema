package kd.dev.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
public class Seance implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.TIME)
    private Date heureDebut;

    public Seance() {
    }

    public Seance(Date heureDebut) {
        this.heureDebut = heureDebut;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(Date heureDebut) {
        this.heureDebut = heureDebut;
    }
}

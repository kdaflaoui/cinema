package kd.dev.entities;

import org.springframework.data.rest.core.config.Projection;

import java.util.Date;

@Projection(name = "p1", types = {kd.dev.entities.Projection.class})
public interface ProjectionProj {
    public Long getId();
    public double getPrix();
    public Date getDateProjection();
    public Salle getSalle();
    public Film getFilm();
    public Seance getSeance();

}

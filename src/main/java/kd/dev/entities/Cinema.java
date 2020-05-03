package kd.dev.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
public class Cinema implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private double longitude, latitude, altitude;
    private int nombreSalle;
    @OneToMany(mappedBy = "cinema", fetch = FetchType.LAZY)
    private Collection<Salle> salles;
    @ManyToOne
    private Ville ville;

    public Cinema() {
    }

    public Cinema(String name, double longitude, double latitude, double altitude, int nombreSalle, Collection<Salle> salles, Ville ville) {
        this.name = name;
        this.longitude = longitude;
        this.latitude = latitude;
        this.altitude = altitude;
        this.nombreSalle = nombreSalle;
        this.salles = salles;
        this.ville = ville;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getAltitude() {
        return altitude;
    }

    public void setAltitude(double altitude) {
        this.altitude = altitude;
    }

    public int getNombreSalle() {
        return nombreSalle;
    }

    public void setNombreSalle(int nombreSalle) {
        this.nombreSalle = nombreSalle;
    }

    public Collection<Salle> getSalles() {
        return salles;
    }

    public void setSalles(Collection<Salle> salles) {
        this.salles = salles;
    }

    public Ville getVille() {
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }
}

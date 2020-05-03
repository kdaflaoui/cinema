package kd.dev.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
public class Salle implements Serializable {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int nombrePlace;
    @ManyToOne
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Cinema cinema;
    @OneToMany(mappedBy = "salle")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Place> places;
    @OneToMany(mappedBy = "salle")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Projection> projections;

 public Salle() {
 }

 public Salle(String name, int nombrePlac, Cinema cinema, Collection<Place> places, Collection<Projection> projections) {
  this.name = name;
  this.nombrePlace = nombrePlace;
  this.cinema = cinema;
  this.places = places;
  this.projections = projections;
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

 public int getNombrePlace() {
  return nombrePlace;
 }

 public void setNombrePlace(int nombrePlace) {
  this.nombrePlace = nombrePlace;
 }

 public Cinema getCinema() {
  return cinema;
 }

 public void setCinema(Cinema cinema) {
  this.cinema = cinema;
 }

 public Collection<Place> getPlaces() {
  return places;
 }

 public void setPlaces(Collection<Place> places) {
  this.places = places;
 }

 public Collection<Projection> getProjections() {
  return projections;
 }

 public void setProjections(Collection<Projection> projections) {
  this.projections = projections;
 }
}

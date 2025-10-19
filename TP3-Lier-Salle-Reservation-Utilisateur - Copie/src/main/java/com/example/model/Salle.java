package com.example;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Salle {
    @Id @GeneratedValue
    private Long id;

    private String nom;
    private String description;
    private int capacite;

    @OneToMany(mappedBy = "salle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reservation> reservations = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Equipement> equipements = new ArrayList<>();

    public Salle() {}

    public Salle(String nom, String description, int capacite) {
        this.nom = nom;
        this.description = description;
        this.capacite = capacite;
    }

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public int getCapacite() { return capacite; }
    public void setCapacite(int capacite) { this.capacite = capacite; }

    public List<Reservation> getReservations() { return reservations; }
    public void addReservation(Reservation r) {
        reservations.add(r);
        r.setSalle(this);
    }
    public void removeReservation(Reservation r) {
        reservations.remove(r);
        r.setSalle(null);
    }

    public List<Equipement> getEquipements() { return equipements; }
    public void addEquipement(Equipement e) {
        equipements.add(e);
        e.getSalles().add(this);
    }
    public void removeEquipement(Equipement e) {
        equipements.remove(e);
        e.getSalles().remove(this);
    }
}

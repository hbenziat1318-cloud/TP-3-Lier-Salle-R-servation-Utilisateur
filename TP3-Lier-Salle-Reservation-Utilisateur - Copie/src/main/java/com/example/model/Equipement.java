package com.example;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Equipement {
    @Id @GeneratedValue
    private Long id;

    private String nom;
    private String description;

    @ManyToMany(mappedBy = "equipements")
    private List<Salle> salles = new ArrayList<>();

    public Equipement() {}
    public Equipement(String nom, String description) {
        this.nom = nom;
        this.description = description;
    }

    // Getters et Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<Salle> getSalles() { return salles; }
}

package com.example;

import jakarta.persistence.*;
import java.time.LocalDateTime;

public class App {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("gestion-reservations");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        // Création utilisateurs
        Utilisateur u1 = new Utilisateur("Hana", "H.", "hana@example.com");
        em.persist(u1);

        // Création salles et équipements
        Salle s1 = new Salle("Salle A", "Grande salle", 30);
        Salle s2 = new Salle("Salle B", "Petite salle", 10);

        Equipement e1 = new Equipement("Projecteur", "HD");
        Equipement e2 = new Equipement("Tableau blanc", "Magnétique");

        s1.addEquipement(e1);
        s1.addEquipement(e2);
        s2.addEquipement(e2);

        em.persist(s1);
        em.persist(s2);
        em.persist(e1);
        em.persist(e2);

        // Création réservations
        Reservation r1 = new Reservation(LocalDateTime.now(), LocalDateTime.now().plusHours(2), "Cours");
        Reservation r2 = new Reservation(LocalDateTime.now(), LocalDateTime.now().plusHours(1), "Réunion");

        u1.addReservation(r1);
        u1.addReservation(r2);

        s1.addReservation(r1);
        s2.addReservation(r2);

        em.persist(r1);
        em.persist(r2);

        em.getTransaction().commit();

        // Affichage
        System.out.println("Utilisateur " + u1.getNom() + " a " + u1.getReservations().size() + " réservation(s).");

        // Exemple de suppression d'une réservation (orphanRemoval)
        em.getTransaction().begin();
        u1.removeReservation(r1);
        em.getTransaction().commit();
        System.out.println("Après suppression, utilisateur " + u1.getNom() + " a " + u1.getReservations().size() + " réservation(s).");

        em.close();
        emf.close();
    }
}

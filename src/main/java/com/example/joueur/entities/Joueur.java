package com.example.joueur.entities;

import java.util.Objects;

public class Joueur {
    private int id;
    private int numJoueur;
    private String poste;
    private String nom;
    private String prenom;
    private int nbBut;
    private int nbExperience;
    private String club;
    private String equipeNational;

    private Utilisateur utilisateur;

    public Joueur() {
    }

    public Joueur(int numJoueur, String poste, String nom, String prenom, int nbBut, int nbExperience, String club, String equipeNational) {
        this.numJoueur = numJoueur;
        this.poste = poste;
        this.nom = nom;
        this.prenom = prenom;
        this.nbBut = nbBut;
        this.nbExperience = nbExperience;
        this.club = club;
        this.equipeNational = equipeNational;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumJoueur() {
        return numJoueur;
    }

    public void setNumJoueur(int numJoueur) {
        this.numJoueur = numJoueur;
    }

    public String getPoste() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste = poste;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getNbBut() {
        return nbBut;
    }

    public void setNbBut(int nbBut) {
        this.nbBut = nbBut;
    }

    public int getNbExperience() {
        return nbExperience;
    }

    public void setNbExperience(int nbExperience) {
        this.nbExperience = nbExperience;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public String getEquipeNational() {
        return equipeNational;
    }

    public void setEquipeNational(String equipeNational) {
        this.equipeNational = equipeNational;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Joueur)) return false;
        Joueur joueur = (Joueur) o;
        return getNumJoueur() == joueur.getNumJoueur() && getNbBut() == joueur.getNbBut() && getNbExperience() == joueur.getNbExperience() && Objects.equals(getPoste(), joueur.getPoste()) && Objects.equals(getNom(), joueur.getNom()) && Objects.equals(getPrenom(), joueur.getPrenom()) && Objects.equals(getClub(), joueur.getClub()) && Objects.equals(getEquipeNational(), joueur.getEquipeNational());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumJoueur(), getPoste(), getNom(), getPrenom(), getNbBut(), getNbExperience(), getClub(), getEquipeNational());
    }

    @Override
    public String toString() {
        return "Joueur{" +
                "numJoueur=" + numJoueur +
                ", poste='" + poste + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", nbBut=" + nbBut +
                ", nbExperience=" + nbExperience +
                ", club=" + club +
                ", equipeNational=" + equipeNational +
                '}';
    }
}

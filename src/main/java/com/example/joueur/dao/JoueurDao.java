package com.example.joueur.dao;

import com.example.joueur.entities.Joueur;

import java.util.List;

public interface JoueurDao {
    void save(Joueur joueur, int userId);
    void update(Joueur joueur, int id, int userId);
    void delete(int id, int userId);
    Joueur findById(int id, int userId);
    List<Joueur> findAll(int userId);
    List<Joueur> findByClub(String nom, int userId);
    List<Joueur> findByEquipeNational(String nom, int userId);
}

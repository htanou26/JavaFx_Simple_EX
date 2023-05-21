package com.example.joueur.services;

import com.example.joueur.dao.DaoImplement.JoueurDaoImplement;
import com.example.joueur.dao.JoueurDao;
import com.example.joueur.entities.Joueur;

import java.util.List;

public class JoueurService {
    private JoueurDao joueurDao = new JoueurDaoImplement();
    public List<Joueur> findAll(int userId) {
        return joueurDao.findAll(userId);
    }
    public List<Joueur> findByClub(String c, int userId) {
        return joueurDao.findByClub(c, userId);
    }
    public Joueur findById(int id, int userId) {
        return joueurDao.findById(id, userId);
    }
    public void save(Joueur joueur, int userId) {
        joueurDao.save(joueur, userId);
    }
    public void update(Joueur joueur, int id, int userId) {
        joueurDao.update(joueur, id, userId);
    }
    public void remove(int i, int userId) {
        joueurDao.delete(i, userId);
    }
}

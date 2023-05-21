package com.example.joueur.services;

import com.example.joueur.dao.DaoImplement.ConnextionImpl;
import com.example.joueur.dao.UtilisateurDao;
import com.example.joueur.entities.Utilisateur;

public class UtilisateurService {
    private UtilisateurDao utilisateurDao = new ConnextionImpl();

    public void inscrire(Utilisateur utilisateur) { utilisateurDao.sInscrire(utilisateur);}
    public Utilisateur seconnecter(String email, String motDePasse) {return  utilisateurDao.seConnecter(email, motDePasse);}
}

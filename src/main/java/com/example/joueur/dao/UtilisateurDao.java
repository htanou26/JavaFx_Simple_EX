package com.example.joueur.dao;

import com.example.joueur.entities.Utilisateur;

public interface UtilisateurDao {
    Utilisateur seConnecter(String email, String motDePasse);
    void sInscrire(Utilisateur utilisateur);
}

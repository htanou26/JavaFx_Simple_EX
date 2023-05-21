package com.example.joueur.services.File;

public interface FileInterface {
    void lireFichierTexte(String nomFichier, int userId);
    void ecrireFichierTexte(String nomFichier, int userId);
    void lireFichierExcel(String nomFichier, int userId);
    void ecrireFichierExcel(String nomFichier, int userId);
    void lireFichierJson(String nomFichier, int userId);
    void ecrireFichierJson(String nomFichier, int userId);
}

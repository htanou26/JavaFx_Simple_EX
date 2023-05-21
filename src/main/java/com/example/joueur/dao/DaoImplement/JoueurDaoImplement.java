package com.example.joueur.dao.DaoImplement;

import com.example.joueur.dao.JoueurDao;
import com.example.joueur.entities.Joueur;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JoueurDaoImplement implements JoueurDao {
    private Connection conn= DB.getConnection();
    @Override
    public void save(Joueur joueur, int userId) {
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(
                    "INSERT INTO Joueur (numJoueur, poste, nom, prenom, nbBut, nbExperience, club, equipeNational, utilisateurId) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                    Statement.RETURN_GENERATED_KEYS);

            ps.setInt(1, joueur.getNumJoueur());
            ps.setString(2, joueur.getPoste());
            ps.setString(3, joueur.getNom());
            ps.setString(4, joueur.getPrenom());
            ps.setInt(5, joueur.getNbBut());
            ps.setInt(6, joueur.getNbExperience());
            ps.setString(7, joueur.getClub());
            ps.setString(8, joueur.getEquipeNational());
            ps.setInt(9, userId);


            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = ps.getGeneratedKeys();

                if (rs.next()) {
                    int id = rs.getInt(1);

                    joueur.setId(id);
                }

                DB.closeResultSet(rs);
            } else {
                System.out.println("Aucune ligne renvoyé");;
            }
        } catch (SQLException e) {
            System.err.println("problème d'insertion d'un joueur " + e);
        } finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public void update(Joueur joueur, int id, int userId) {
        PreparedStatement ps = null;

        try {
            ps = conn.prepareStatement(
                    "UPDATE Joueur SET NUMJOUEUR = ?, POSTE = ?, NOM = ?, PRENOM = ?, NbBUT = ?, NbEXPERIENCE = ?, Club = ?, EquipeNational = ? WHERE Id = ? AND utilisateurId = ?");

            ps.setInt(1, joueur.getNumJoueur());
            ps.setString(2, joueur.getPoste());
            ps.setString(3, joueur.getNom());
            ps.setString(4, joueur.getPrenom());
            ps.setInt(5, joueur.getNbBut());
            ps.setInt(6, joueur.getNbExperience());
            ps.setString(7, joueur.getClub());
            ps.setString(8, joueur.getEquipeNational());
            ps.setInt(9, id);
            ps.setInt(10, userId);

            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("problème de mise à jour d'un club " + e);;
        } finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public void delete(int id, int userId) {
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement("DELETE FROM Joueur WHERE id = ? AND utilisateurId = ?");

            ps.setInt(1, id);
            ps.setInt(2, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println("problème de suppression d'un joueur");
        } finally {
            DB.closeStatement(ps);
        }
    }

    @Override
    public Joueur findById(int id, int userId) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = conn.prepareStatement(
                    "SELECT h.* FROM Joueur h WHERE h.id = ? AND utilisateurId = ?");
            ps.setInt(1, id);
            ps.setInt(2, userId);
            rs = ps.executeQuery();
            if (rs.next()) {
                return instantiateJoueur(rs);
            }
            return null;
        } catch (SQLException e) {
            System.err.println("problème de requête pour trouver le joueur" + e);
            return null;
        } finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Joueur> findAll(int userId) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(
                    "SELECT h.* FROM Joueur h WHERE h.utilisateurId = ?");
            ps.setInt(1, userId);
            rs = ps.executeQuery();
            List<Joueur> list = new ArrayList<>();

            while (rs.next()) {
                list.add(instantiateJoueur(rs));
            }

            return list;
        } catch (SQLException e) {
            System.err.println("problème de requête pour sélectionner les joueurs" + e);
            return null;
        } finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Joueur> findByClub(String nom, int userId) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(
                    "SELECT h.* FROM Joueur h WHERE h.Club = ? AND h.utilisateurId = ? ORDER BY h.Nom");

            ps.setString(1, nom);
            ps.setInt(2, userId);

            rs = ps.executeQuery();
            List<Joueur> list = new ArrayList<>();

            while (rs.next()) {
                list.add(instantiateJoueur(rs));
            }
            return list;
        } catch (SQLException e) {
            System.err.println("problème de requête pour sélectionner les joueur d'un club donnée" + e);
            return null;
        } finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Joueur> findByEquipeNational(String nom, int userId) {
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            ps = conn.prepareStatement(
                    "SELECT h.* FROM Joueur h WHERE h.EquipeNational = ? AND h.utilisateurId = ? ORDER BY h.Nom");

            ps.setString(1, nom);
            ps.setInt(2, userId);
            rs = ps.executeQuery();
            List<Joueur> list = new ArrayList<>();

            while (rs.next()) {
                list.add(instantiateJoueur(rs));
            }
            return list;
        } catch (SQLException e) {
            System.err.println("problème de requête pour sélectionner les joueur d'une equipe donnée" + e);
            return null;
        } finally {
            DB.closeStatement(ps);
            DB.closeResultSet(rs);
        }
    }

    private Joueur instantiateJoueur(ResultSet rs) throws SQLException {
        Joueur joueur = new Joueur();

        joueur.setId(rs.getInt("Id"));
        joueur.setNumJoueur(rs.getInt("numJoueur"));
        joueur.setPoste(rs.getString("poste"));
        joueur.setNom(rs.getString("Nom"));
        joueur.setPrenom(rs.getString("Prenom"));
        joueur.setNbBut(rs.getInt("nbBut"));
        joueur.setNbExperience(rs.getInt("nbExperience"));
        joueur.setClub(rs.getString("Club"));
        joueur.setEquipeNational(rs.getString("EquipeNational"));

        return joueur;
    }
}

package com.example.joueur;

import com.example.joueur.entities.Joueur;
import com.example.joueur.entities.Utilisateur;
import com.example.joueur.services.File.FileService;
import com.example.joueur.services.JoueurService;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JoueurController {
    @FXML
    private TextField numJoueurField;
    @FXML
    private TextField posteField;
    @FXML
    private TextField nomField;
    @FXML
    private TextField prenomField;
    @FXML
    private TextField nbButField;
    @FXML
    private TextField nbExperienceField;
    @FXML
    private TextField clubField;
    @FXML
    private TextField equipeNationalField;
    @FXML
    private Button ajouterButton;
    @FXML
    private Button modifierButton;
    @FXML
    private Button supprimerButton;
    @FXML
    private TableView<Joueur> joueurTable;
    @FXML
    private TableColumn<Joueur, Integer> idColumn;
    @FXML
    private TableColumn<Joueur, Integer> numJoueurColumn;
    @FXML
    private TableColumn<Joueur, String> posteColumn;
    @FXML
    private TableColumn<Joueur, String> nomColumn;
    @FXML
    private TableColumn<Joueur, String> prenomColumn;
    @FXML
    private TableColumn<Joueur, Integer> nbButColumn;
    @FXML
    private TableColumn<Joueur, Integer> nbExperienceColumn;
    @FXML
    private TableColumn<Joueur, String> clubColumn;
    @FXML
    private TableColumn<Joueur, String> equipeNationalColumn;
    @FXML
    private Label messageLabel;
    private Utilisateur utilisateur = new Utilisateur();
    private JoueurService joueurService = new JoueurService();
    private List<Joueur> joueurList = new ArrayList<>();
    private FileService fileService = new FileService();
    public void setJoueurService(JoueurService joueurService) {
        this.joueurService = joueurService;
    }
    @FXML
    private void initialize() {
        idColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getId()));
        numJoueurColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getNumJoueur()));
        posteColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getPoste()));
        nomColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getNom()));
        prenomColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getPrenom()));
        nbButColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getNbBut()));
        nbExperienceColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getNbExperience()));
        clubColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getClub()));
        equipeNationalColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getEquipeNational()));

        joueurTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                fillFieldsWithSelectedJoueur(newSelection);
            } else {
                clearFields();
            }
        });
        joueurList = FXCollections.observableArrayList(joueurService.findAll(utilisateur.getId()));
        joueurTable.setItems((ObservableList<Joueur>) joueurList);
    }

    @FXML
    private void handleAjouter(ActionEvent event) {
        int numJoueur = Integer.parseInt(numJoueurField.getText());
        String poste = posteField.getText();
        String nom = nomField.getText();
        String prenom = prenomField.getText();
        int nbBut = Integer.parseInt(nbButField.getText());
        int nbExperience = Integer.parseInt(nbExperienceField.getText());
        String club = clubField.getText();
        String equipeNational = equipeNationalField.getText();

        Joueur joueur = new Joueur(numJoueur, poste, nom, prenom, nbBut, nbExperience, club, equipeNational);
        joueurService.save(joueur, utilisateur.getId());

        joueurList.add(joueur);
        clearFields();
        messageLabel.setText("Joueur ajouté avec succès.");
    }

    @FXML
    private void handleModifier(ActionEvent event) {
        Joueur selectedJoueur = joueurTable.getSelectionModel().getSelectedItem();
        if (selectedJoueur != null) {
            int numJoueur = Integer.parseInt(numJoueurField.getText());
            String poste = posteField.getText();
            String nom = nomField.getText();
            String prenom = prenomField.getText();
            int nbBut = Integer.parseInt(nbButField.getText());
            int nbExperience = Integer.parseInt(nbExperienceField.getText());
            String club = clubField.getText();
            String equipeNational = equipeNationalField.getText();
            selectedJoueur.setNumJoueur(numJoueur);
            selectedJoueur.setPoste(poste);
            selectedJoueur.setNom(nom);
            selectedJoueur.setPrenom(prenom);
            selectedJoueur.setNbBut(nbBut);
            selectedJoueur.setNbExperience(nbExperience);
            selectedJoueur.setClub(club);
            selectedJoueur.setEquipeNational(equipeNational);

            joueurService.update(selectedJoueur, selectedJoueur.getId(), getUtilisateur().getId());
            joueurTable.refresh();
            clearFields();
            messageLabel.setText("Joueur modifié avec succès.");
        } else {
            messageLabel.setText("Aucun joueur sélectionné.");
        }
    }

    @FXML
    private void handleSupprimer(ActionEvent event) {
        Joueur selectedJoueur = joueurTable.getSelectionModel().getSelectedItem();
        if (selectedJoueur != null) {
            joueurService.remove(selectedJoueur.getId(), getUtilisateur().getId());
            joueurList.remove(selectedJoueur);
            clearFields();
            messageLabel.setText("Joueur supprimé avec succès.");
        } else {
            messageLabel.setText("Aucun joueur sélectionné.");
        }
    }

    @FXML
    private void handleSelection(ActionEvent event) {
        Joueur selectedJoueur = joueurTable.getSelectionModel().getSelectedItem();
        if (selectedJoueur != null) {
            numJoueurField.setText(Integer.toString(selectedJoueur.getNumJoueur()));
            posteField.setText(selectedJoueur.getPoste());
            nomField.setText(selectedJoueur.getNom());
            prenomField.setText(selectedJoueur.getPrenom());
            nbButField.setText(Integer.toString(selectedJoueur.getNbBut()));
            nbExperienceField.setText(Integer.toString(selectedJoueur.getNbExperience()));
            clubField.setText(selectedJoueur.getClub());
            equipeNationalField.setText(selectedJoueur.getEquipeNational());
            messageLabel.setText("");
        } else {
            clearFields();
            messageLabel.setText("Aucun joueur sélectionné.");
        }
    }
    @FXML
    private void importerFichierTexte() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Importer un fichier texte");
        dialog.setHeaderText(null);
        dialog.setContentText("Entrez le nom du fichier texte :");
        Optional<String> result = dialog.showAndWait();

        result.ifPresent(nomFichier -> {
            // Appel à la méthode du service pour lire le fichier texte
            fileService.lireFichierTexte(nomFichier, utilisateur.getId());
        });
    }

    @FXML
    private void importerFichierJSON() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Importer un fichier JSON");
        dialog.setHeaderText(null);
        dialog.setContentText("Entrez le nom du fichier JSON :");
        Optional<String> result = dialog.showAndWait();

        result.ifPresent(nomFichier -> {
            // Appel à la méthode du service pour lire le fichier JSON
            fileService.lireFichierJson(nomFichier, utilisateur.getId());
        });
    }


    @FXML
    private void importerFichierExcel() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Importer un fichier Excel");
        dialog.setHeaderText(null);
        dialog.setContentText("Entrez le nom du fichier Excel :");
        Optional<String> result = dialog.showAndWait();

        result.ifPresent(nomFichier -> {
            // Appel à la méthode du service pour lire le fichier Excel
            fileService.lireFichierExcel(nomFichier, utilisateur.getId());
        });
    }

    @FXML
    private void handleActualiser(ActionEvent event) {
        joueurList = FXCollections.observableArrayList(joueurService.findAll(utilisateur.getId()));
        joueurTable.setItems((ObservableList<Joueur>) joueurList);
    }


    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    @FXML
    private void clearFields() {
        numJoueurField.clear();
        posteField.clear();
        nomField.clear();
        prenomField.clear();
        nbButField.clear();
        nbExperienceField.clear();
        clubField.clear();
        equipeNationalField.clear();
    }
    private void fillFieldsWithSelectedJoueur(Joueur joueur) {
        numJoueurField.setText(Integer.toString(joueur.getNumJoueur()));
        posteField.setText(joueur.getPoste());
        nomField.setText(joueur.getNom());
        prenomField.setText(joueur.getPrenom());
        nbButField.setText(Integer.toString(joueur.getNbBut()));
        nbExperienceField.setText(Integer.toString(joueur.getNbExperience()));
        clubField.setText(joueur.getClub());
        equipeNationalField.setText(joueur.getEquipeNational());
    }

    public void exporterEnJSON(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Exporter un fichier JSON");
        dialog.setHeaderText(null);
        dialog.setContentText("Entrez le nom du fichier JSON :");
        Optional<String> result = dialog.showAndWait();

        result.ifPresent(nomFichier -> {
            // Appel à la méthode du service pour lire le fichier JSON
            fileService.ecrireFichierJson(nomFichier, utilisateur.getId());
        });
    }

    public void exporterEnExcel(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Exporter un fichier Excel");
        dialog.setHeaderText(null);
        dialog.setContentText("Entrez le nom du fichier Excel :");
        Optional<String> result = dialog.showAndWait();

        result.ifPresent(nomFichier -> {
            // Appel à la méthode du service pour lire le fichier Excel
            fileService.ecrireFichierExcel(nomFichier, utilisateur.getId());
        });
    }

    public void exporterEnTexte(ActionEvent actionEvent) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Importer un fichier texte");
        dialog.setHeaderText(null);
        dialog.setContentText("Entrez le nom du fichier texte :");
        Optional<String> result = dialog.showAndWait();

        result.ifPresent(nomFichier -> {
            // Appel à la méthode du service pour lire le fichier texte
            fileService.ecrireFichierTexte(nomFichier, utilisateur.getId());
        });
    }
}

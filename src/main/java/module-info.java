module com.example.joueur {
    requires javafx.controls;
    requires javafx.fxml;
    requires json.simple;
    requires org.apache.poi.ooxml;
    requires gson;
    requires java.sql;

    opens com.example.joueur.dao.DaoImplement to javafx.base, javafx.graphics, javafx.fxml;
    opens com.example.joueur.dao to javafx.base, javafx.graphics, javafx.fxml;
    opens com.example.joueur.entities to javafx.base, javafx.graphics, javafx.fxml, gson;
    opens com.example.joueur.services.File to javafx.base, javafx.graphics, javafx.fxml;
    opens com.example.joueur.services to javafx.base, javafx.graphics, javafx.fxml;
    opens com.example.joueur to javafx.base, javafx.graphics, javafx.fxml;
    exports com.example.joueur;

    exports com.example.joueur.dao.DaoImplement;
    exports com.example.joueur.dao;
    exports com.example.joueur.entities;
}
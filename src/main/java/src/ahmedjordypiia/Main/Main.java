package src.ahmedjordypiia.Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import src.ahmedjordypiia.Controle.Recherche;
import src.ahmedjordypiia.Vue.TreeOfLifeVisual;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("Tree Of Life");

        // Charger le fichier FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/src/ahmedjordypiia/treeOfLife.fxml"));
        Parent root = loader.load();

        // Définir la scène avec le Parent chargé
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        // Définir la taille de la fenêtre
        primaryStage.setMinWidth(1000);
        primaryStage.setMinHeight(700);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
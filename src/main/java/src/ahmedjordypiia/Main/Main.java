package src.ahmedjordypiia.Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import src.ahmedjordypiia.Modele.Node;
import src.ahmedjordypiia.Modele.Tree;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("JavaFX TreeView Example");

        // Charger le fichier FXML
        Parent root = FXMLLoader.load(getClass().getResource("/src/ahmedjordypiia/hello-view.fxml"));

        // Définir la scène avec le Parent chargé
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
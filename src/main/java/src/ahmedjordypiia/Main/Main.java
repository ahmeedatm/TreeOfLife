package src.ahmedjordypiia.Main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import src.ahmedjordypiia.Vue.TreeOfLifeVisual;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setTitle("JavaFX TreeView Example");

        // Charger le fichier FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/src/ahmedjordypiia/hello-view.fxml"));
        Parent root = loader.load();

        // Créer une instance de TreeOfLifeVisual et obtenir le Group de l'arbre
        TreeOfLifeVisual treeOfLifeVisual = new TreeOfLifeVisual();
        Group treeGroup = treeOfLifeVisual.getTreeGroup();

        // Obtenir une référence à l'AnchorPane dans le fichier FXML
        AnchorPane anchorPane = (AnchorPane) root.lookup("#myAnchorPane");

        // Ajouter le Group de l'arbre à l'AnchorPane
        anchorPane.getChildren().add(treeGroup);

        // Définir la scène avec le Parent chargé
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
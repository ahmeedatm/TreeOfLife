package src.ahmedjordypiia;

import javafx.application.Application;
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

        Tree tree = new Tree();
        //tree.readCSV("src/main/resources/treeoflife_nodes_simplified.csv");
        //Node root = tree.getNodes().get("1");

        //TreeItem<String> rootItem = createTree(root);
        //TreeView<String> treeView = new TreeView<>(rootItem);

        //Scene scene = new Scene(treeView, 300, 200);
        //primaryStage.setScene(scene);
        primaryStage.show();
    }

    private TreeItem<String> createTree(Node node) {
        TreeItem<String> item = new TreeItem<>(node.getName());
        /*
        for (Node child : node.getChildren()) {
            item.getChildren().add(createTree(child));
        }
         */
        return item;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
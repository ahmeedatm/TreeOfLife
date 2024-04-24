package src.ahmedjordypiia.Vue;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import src.ahmedjordypiia.Modele.*;

public class TreeOfLifeVisual extends Application {

    private final int WINDOW_WIDTH = 1600; // Modifiez cette valeur pour ajuster la largeur de la fenêtre
    private final int WINDOW_HEIGHT = 1200; // Modifiez cette valeur pour ajuster la hauteur de la fenêtre


    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT, Color.WHITE);

        try {
            // Charger les données des nœuds et des liens à partir des fichiers CSV
            Tree tree = new Tree();
            tree.readNodesCSV("src/main/resources/src/ahmedjordypiia/treeoflife_nodes_simplified.csv");
            tree.readLinksCSV("src/main/resources/src/ahmedjordypiia/treeoflife_links_simplified.csv");

            // Dessiner l'arbre de vie
            drawTree(root, tree.getNodes().get(0), WINDOW_WIDTH / 2, WINDOW_HEIGHT / 1.5, -90, 200, 8); // Changez la longueur de 100 à 200

        } catch (Exception e) {
            e.printStackTrace();
        }

        primaryStage.setTitle("Tree of Life");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void drawTree(Group group, Node node, double x, double y, double angle, double length, int depth) {
        // Dessiner le nom du nœud pour tous les nœuds, pas seulement les feuilles
        drawNodeName(group, node.getName(), x, y);

        if (depth == 0) {
            return;
        }

        // Calculer l'angle entre chaque enfant
        double angleStep = 180.0 / (node.getChildren().size() + 1);

        // Appels récursifs pour les branches plus petites
        for (int i = 0; i < node.getChildren().size(); i++) {
            Node child = node.getChildren().get(i);

            // Calculer l'angle et le point final de la branche pour cet enfant
            double childAngle = angle - 90 + angleStep * (i + 1);
            double xEnd = x + length * Math.cos(Math.toRadians(childAngle));
            double yEnd = y + length * Math.sin(Math.toRadians(childAngle));

            // Dessiner la branche
            Line line = new Line(x, y, xEnd, yEnd);
            group.getChildren().add(line);

            // Dessiner l'arbre pour cet enfant
            drawTree(group, child, xEnd, yEnd, childAngle, length * 0.7, depth - 1);
        }
    }

    private void drawNodeName(Group group, String name, double x, double y) {
        Circle circle = new Circle(x, y, 5, Color.WHITE); // Fond de cercle blanc
        group.getChildren().add(circle);

        Circle blossom = new Circle(x, y, 4, Color.RED); // Fleur rouge
        group.getChildren().add(blossom);

        javafx.scene.text.Text text = new javafx.scene.text.Text(x - 15, y + 20, name); // Déplacez le texte un peu plus loin du centre du blossom
        group.getChildren().add(text);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
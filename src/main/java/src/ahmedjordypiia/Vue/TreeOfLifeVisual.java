package src.ahmedjordypiia.Vue;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Affine;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import src.ahmedjordypiia.Modele.*;

public class TreeOfLifeVisual {

    private final int WINDOW_WIDTH = 1000; // Modifiez cette valeur pour ajuster la largeur de la fenêtre
    private final int WINDOW_HEIGHT = 700; // Modifiez cette valeur pour ajuster la hauteur de la fenêtre
    private Scale scale = new Scale();
    private final double zoomIntensity = 1.1;
    private Affine affine = new Affine();

    public Group getTreeGroup() {
        Group root = new Group();
        root.getTransforms().setAll(affine);
        root.setOnScroll(event -> {
            double delta = event.getDeltaY();
            double scale = delta > 0 ? zoomIntensity : 1/zoomIntensity;
            zoom(scale, event.getX(), event.getY());
            event.consume();
        });
        try {
            // Charger les données des nœuds et des liens à partir des fichiers CSV
            Tree tree = new Tree();
            tree.readNodesCSV("src/main/resources/src/ahmedjordypiia/treeoflife_nodes_simplified.csv");
            tree.readLinksCSV("src/main/resources/src/ahmedjordypiia/treeoflife_links_simplified.csv");

            // Dessiner l'arbre de vie
            drawTree(root, tree.getNodes().get(0), WINDOW_WIDTH / 2, WINDOW_HEIGHT / 1.25, -90, 150, 8); // Changez la longueur de 100 à 200

            // Appliquer un zoom sur l'arbre
            root.getTransforms().add(scale);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return root;
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
            line.setStroke(Color.WHITE);
            group.getChildren().add(line);

            // Dessiner l'arbre pour cet enfant
            drawTree(group, child, xEnd, yEnd, childAngle, length * 0.7, depth - 1);
        }
    }

    private void drawNodeName(Group group, String name, double x, double y) {
        Circle circle = new Circle(x, y, 5, Color.WHITE); // Fond de cercle blanc
        group.getChildren().add(circle);

        Circle blossom = new Circle(x, y, 4, Color.RED); // Fleur rouge
        blossom.setOpacity(0.5);
        group.getChildren().add(blossom);

        javafx.scene.text.Text text = new javafx.scene.text.Text(x - 15, y + 20, name); // Déplacez le texte un peu plus loin du centre du blossom
        text.setFill(Color.WHITE);
        group.getChildren().add(text);
    }

    public void zoomIn() {
        scale.setX(scale.getX() * zoomIntensity);
        scale.setY(scale.getY() * zoomIntensity);
    }

    public void zoomOut() {
        scale.setX(scale.getX() / zoomIntensity);
        scale.setY(scale.getY() / zoomIntensity);
    }
    public void zoom(double factor, double x, double y) {
        affine.prependScale(factor, factor, x, y);
    }
}
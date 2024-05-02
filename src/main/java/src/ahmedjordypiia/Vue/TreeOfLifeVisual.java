package src.ahmedjordypiia.Vue;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.transform.Affine;
import javafx.scene.transform.NonInvertibleTransformException;
import javafx.scene.transform.Scale;
import javafx.stage.Stage;
import src.ahmedjordypiia.Modele.*;

public class TreeOfLifeVisual {

    private final int WINDOW_WIDTH = 1000; // Modifiez cette valeur pour ajuster la largeur de la fenêtre
    private final int WINDOW_HEIGHT = 700; // Modifiez cette valeur pour ajuster la hauteur de la fenêtre

    private double mouseAnchorX;
    private double mouseAnchorY;
    private Scale scale = new Scale();
    private final double zoomIntensity = 1.1;
    private Affine affine = new Affine();

    public Group getTreeGroup() {
        Group root = new Group();
        root.getTransforms().setAll(affine);

        root.setOnScroll(event -> {
            double delta = event.getDeltaY();
            double scale = delta > 0 ? zoomIntensity : 1 / zoomIntensity;
            javafx.geometry.Point2D transformedPoint;
            try {
                transformedPoint = affine.inverseTransform(event.getX(), event.getY());
            } catch (NonInvertibleTransformException e) {
                throw new RuntimeException(e);
            }
            zoom(scale, transformedPoint.getX(), transformedPoint.getY());
            event.consume();
        });

        //Drag and drop
        root.setOnMousePressed(event -> {
            // Capture the mouse position when the mouse button is pressed
            mouseAnchorX = event.getX();
            mouseAnchorY = event.getY();
        });

        root.setOnMouseDragged(event -> {
            // Calculate the difference between the current mouse position and the anchor position
            double deltaX = event.getX() - mouseAnchorX;
            double deltaY = event.getY() - mouseAnchorY;

            // Move the tree by the calculated amount
            for (javafx.scene.Node node : root.getChildren()) {
                node.setTranslateX(node.getTranslateX() + deltaX);
                node.setTranslateY(node.getTranslateY() + deltaY);
            }

            // Update the anchor position
            mouseAnchorX = event.getX();
            mouseAnchorY = event.getY();
        });

        try {
            // Charger les données des nœuds et des liens à partir des fichiers CSV
            Tree tree = new Tree();
            tree.readNodesCSV("src/main/resources/src/ahmedjordypiia/treeoflife_nodes_simplified.csv");
            tree.readLinksCSV("src/main/resources/src/ahmedjordypiia/treeoflife_links_simplified.csv");

            // Dessiner l'arbre de vie
            drawTree(root, tree, tree.getNodes().get(0), WINDOW_WIDTH / 2.25 , WINDOW_HEIGHT / 1.5, -140, 150, 8, 20); // 20 est la taille de texte initiale            // Appliquer un zoom sur l'arbre
            root.getTransforms().add(scale);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return root;
    }

    private void drawTree(Group group, Tree tree, Node node, double x, double y, double angle, double length, int depth, double textSize) {
        // Dessiner le nom du nœud pour tous les nœuds, pas seulement les feuilles
        drawNodeName(group, node.getName(), x, y, textSize);

        if (depth == 0) {
            return;
        }

        // Calculer l'angle entre chaque enfant
        double angleStep;
        if (node == tree.getNodes().get(0)) { // Si le nœud actuel est le nœud racine
            angleStep = 220.0 / (node.getChildren().size()); // Répartir les enfants sur 360 degrés
        } else {
            angleStep = 180.0 / (Math.max(node.getChildren().size(), 1) + 1);
        }

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
            drawTree(group, tree, child, xEnd, yEnd, childAngle, length * 0.65, depth - 1, textSize * 0.75); // réduire la longueur des branches de 30% à chaque niveau
        }
    }

    private void drawNodeName(Group group, String name, double x, double y, double textSize) {
        Circle circle = new Circle(x, y, 5, Color.WHITE); // Fond de cercle blanc
        group.getChildren().add(circle);

        Circle blossom = new Circle(x, y, 4, Color.RED); // Fleur rouge
        blossom.setOpacity(0.5);
        group.getChildren().add(blossom);

        javafx.scene.text.Text text = new javafx.scene.text.Text(x - 15, y + 20, name); // Déplacez le texte un peu plus loin du centre du blossom
        text.setFill(Color.WHITE);
        text.setFont(new Font(textSize)); // Définir la taille du texte
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
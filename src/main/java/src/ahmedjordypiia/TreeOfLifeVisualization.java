package src.ahmedjordypiia;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TreeOfLifeVisualization extends Application {

    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 600;
    private double zoomFactor = 1.0;
    private Map<Integer, Node> nodes = new HashMap<>();

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT, Color.WHITE);

        try {
            // Charger les données des noeuds et des liens à partir des fichiers CSV
            loadNodes("src/main/resources/src/ahmedjordypiia/treeoflife_nodes_simplified.csv");
            loadLinks("src/main/resources/src/ahmedjordypiia/treeoflife_links_simplified.csv");

            // Dessiner l'arbre de vie
            drawTree(root, nodes.get(1), WINDOW_WIDTH / 2, WINDOW_HEIGHT / 1.5, -90, 100, 8);

            // Add scroll event handler for zooming
            scene.setOnScroll(this::handleScroll);
        } catch (IOException e) {
            e.printStackTrace();
        }

        primaryStage.setTitle("Tree of Life");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void loadNodes(String nodesFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(nodesFile));
        String line;
        // Ignore the first line (header)
        reader.readLine();
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            int nodeId = Integer.parseInt(parts[0]);
            String nodeName = parts[1];
            boolean leafNode = Integer.parseInt(parts[3]) == 1;
            nodes.put(nodeId, new Node(nodeId, nodeName, leafNode));
        }
        reader.close();
    }


    private void loadLinks(String linksFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(linksFile));
        String line;
        // Ignore the first line (header)
        reader.readLine();
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(",");
            int sourceNodeId = Integer.parseInt(parts[0]);
            int targetNodeId = Integer.parseInt(parts[1]);
            Node sourceNode = nodes.get(sourceNodeId);
            Node targetNode = nodes.get(targetNodeId);
            if (sourceNode != null && targetNode != null) {
                sourceNode.addChild(targetNode);
            }
        }
        reader.close();
    }


    private void drawTree(Group group, Node node, double x, double y, double angle, double length, int depth) {
        if (depth == 0) {
            drawBlossom(group, x, y); // Add a blossom at the end of the branch
            return;
        }

        // Calculate the end point of the branch
        double xEnd = x + length * Math.cos(Math.toRadians(angle)) * zoomFactor;
        double yEnd = y + length * Math.sin(Math.toRadians(angle)) * zoomFactor;

        // Draw the branch
        Line line = new Line(x * zoomFactor, y * zoomFactor, xEnd, yEnd);
        group.getChildren().add(line);

        // Recursive calls for smaller branches
        Map<Integer, Node> children = node.getChildren();
        int numChildren = children.size();
        int i = 0;
        for (Node child : children.values()) {
            double childAngle = angle + ((double) (i - (numChildren - 1) / 2) / numChildren) * 40; // Distribute children evenly
            drawTree(group, child, xEnd, yEnd, childAngle, length * 0.7, depth - 1);
            i++;
        }
    }

    private void drawBlossom(Group group, double x, double y) {
        Circle blossom = new Circle(x * zoomFactor, y * zoomFactor, 5 * zoomFactor, Color.RED); // Create a red blossom
        group.getChildren().add(blossom); // Add the blossom to the group
    }

    private void handleScroll(ScrollEvent event) {
        double deltaY = event.getDeltaY();
        if (deltaY < 0) {
            // Zoom out
            zoomFactor *= 0.9; // Decrease zoom by 10%
        } else {
            // Zoom in
            zoomFactor *= 1.1; // Increase zoom by 10%
        }
        event.consume();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static class Node {
        private int id;
        private String name;
        private boolean leafNode;
        private Map<Integer, Node> children = new HashMap<>();

        public Node(int id, String name, boolean leafNode) {
            this.id = id;
            this.name = name;
            this.leafNode = leafNode;
        }

        public void addChild(Node child) {
            children.put(child.id, child);
        }

        public Map<Integer, Node> getChildren() {
            return children;
        }
    }
}

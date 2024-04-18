package src.ahmedjordypiia;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.ScrollEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.stage.Stage;

public class RecursiveTree extends Application {

    private final int WINDOW_WIDTH = 800;
    private final int WINDOW_HEIGHT = 600;
    private double zoomFactor = 1.0;

    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT, Color.WHITE);

        // Start the recursive drawing at the center bottom of the window
        drawTree(root, WINDOW_WIDTH / 2, WINDOW_HEIGHT / 1.5, -90, 100, 8);

        // Add scroll event handler for zooming
        scene.setOnScroll(this::handleScroll);

        primaryStage.setTitle("Recursive Tree");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void drawTree(Group group, double x, double y, double angle, double length, int depth) {
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
        drawTree(group, xEnd, yEnd, angle - 20, length * 0.7, depth - 1);
        drawTree(group, xEnd, yEnd, angle + 20, length * 0.7, depth - 1);
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
}


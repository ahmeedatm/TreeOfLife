package src.ahmedjordypiia;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TreeViewExample extends Application {

    private static final String NODES_CSV_FILE = "src/main/resources/src/ahmedjordypiia/treeoflife_nodes_simplified.csv";
    private static final String LINKS_CSV_FILE = "src/main/resources/src/ahmedjordypiia/treeoflife_links_simplified.csv";

    @Override
    public void start(Stage primaryStage) {
        TreeItem<String> rootItem = buildTreeFromCSV();

        TreeView<String> treeView = new TreeView<>(rootItem);
        treeView.setShowRoot(false);

        StackPane root = new StackPane(treeView);
        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Tree of Life");
        primaryStage.show();
    }

    private TreeItem<String> buildTreeFromCSV() {
        TreeItem<String> rootItem = new TreeItem<>("Life on Earth");

        try (BufferedReader nodesReader = new BufferedReader(new FileReader(NODES_CSV_FILE));
             BufferedReader linksReader = new BufferedReader(new FileReader(LINKS_CSV_FILE))) {
            // Skip header lines
            nodesReader.readLine();
            linksReader.readLine();

            String nodesLine;
            while ((nodesLine = nodesReader.readLine()) != null) {
                String[] nodeData = nodesLine.split(",");
                String nodeId = nodeData[0];
                String nodeName = nodeData[1];
                TreeItem<String> nodeItem = new TreeItem<>(nodeName);
                rootItem.getChildren().add(nodeItem);

                String linksLine;
                while ((linksLine = linksReader.readLine()) != null) {
                    String[] linkData = linksLine.split(",");
                    String sourceNodeId = linkData[0];
                    String targetNodeId = linkData[1];
                    if (sourceNodeId.equals(nodeId)) {
                        TreeItem<String> targetNode = findNodeById(rootItem, targetNodeId);
                        if (targetNode != null) {
                            nodeItem.getChildren().add(targetNode);
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rootItem;
    }

    private TreeItem<String> findNodeById(TreeItem<String> root, String nodeId) {
        if (root.getValue().equals(nodeId)) {
            return root;
        }
        for (TreeItem<String> child : root.getChildren()) {
            TreeItem<String> found = findNodeById(child, nodeId);
            if (found != null) {
                return found;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        launch(args);
    }
}


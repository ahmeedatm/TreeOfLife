package src.ahmedjordypiia.Modele;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Tree {
    private Map<Integer, Node> nodes = new HashMap<>();

    public void readNodesCSV(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                Node node = new Node(fields);
                nodes.put(node.getId(), node);
            }
        }
    }

    public void readLinksCSV(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                int sourceNodeId = Integer.parseInt(fields[0]);
                int targetNodeId = Integer.parseInt(fields[1]);
                Node sourceNode = nodes.get(sourceNodeId);
                Node targetNode = nodes.get(targetNodeId);
                if (sourceNode != null && targetNode != null) {
                    sourceNode.addChildren(targetNode);
                }
            }
        }
    }

    public ArrayList<Node> getNodes() {
        return new ArrayList<>(nodes.values());
    }

    public static void main(String[] args) {
        Tree tree = new Tree();
        try {
            tree.readNodesCSV("src/main/resources/src/ahmedjordypiia/treeoflife_nodes_simplified.csv");
            tree.readLinksCSV("src/main/resources/src/ahmedjordypiia/treeoflife_links_simplified.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        printTree(tree.getNodes().get(0), 0);
    }

    private static void printTree(Node node, int level) {
        StringBuilder indent = new StringBuilder();
        for (int i = 0; i < level; i++) {
            indent.append("  "); // Ajoute deux espaces pour chaque niveau
        }
        System.out.println(indent + node.getName());
        for (Node child : node.getChildren()) {
            printTree(child, level + 1);
        }
    }

}
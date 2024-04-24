package src.ahmedjordypiia.Modele;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Tree {
    private ArrayList<Node> nodes = new ArrayList<Node>();

    public Tree(String nodesPath, String linksPath) {
        try {
            this.readNodesCSV(nodesPath);
            this.readLinksCSV(linksPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readNodesCSV(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                Node node = new Node(fields);
                nodes.add(node);
            }
        }
    }

    public void readLinksCSV(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            reader.readLine();
            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(",");
                for (Node node : nodes) {
                    if (node.getId() == Integer.parseInt(fields[0])) {
                        //Links source = new Links(node);
                        for (Node child : nodes) {
                            if (child.getId() == Integer.parseInt(fields[1]))
                                node.addChildren(child);
                        }
                    }
                }
            }
        }
    }

    public ArrayList<Node> getNodes() {
        return nodes;
    }

    public Node searchNode(String name) {
        for (Node node : nodes) {
            if (node.getName().equalsIgnoreCase(name)) {
                return node;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        Tree tree = new Tree("src/main/resources/treeoflife_nodes_simplified.csv", "src/main/resources/treeoflife_links_simplified.csv");

        for (Node node : tree.getNodes()) {
            System.out.println(node.getName());
            for (Node child : node.getChildren()) {
                System.out.println("  " + child.getName());
            }
        }

    }

}

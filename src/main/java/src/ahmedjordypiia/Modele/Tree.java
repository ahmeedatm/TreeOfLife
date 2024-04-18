package src.ahmedjordypiia.Modele;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Tree {
    private ArrayList<Node> nodes = new ArrayList<Node>();

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

    public static void main(String[] args) {
        Tree tree = new Tree();
        try {
            tree.readNodesCSV("src/main/resources/treeoflife_nodes_simplified.csv");
            tree.readLinksCSV("src/main/resources/treeoflife_links_simplified.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Node node : tree.getNodes()) {
            System.out.println(node.getName());
            for (Node child : node.getChildren()) {
                System.out.println("  " + child.getName());
            }
        }


    }

}

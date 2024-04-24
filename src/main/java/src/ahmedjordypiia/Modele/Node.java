package src.ahmedjordypiia.Modele;

import java.util.ArrayList;

public class Node {
    private int id;
    private String name;
    private ArrayList<Node> children = new ArrayList<>();

    public Node(String[] fields) {
        this.id = Integer.parseInt(fields[0]);
        this.name = fields[1];
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void addChildren(Node child) {
        if (this.children == null) {
            this.children = new ArrayList<>();
        }
        this.children.add(child);
    }

    public ArrayList<Node> getChildren() {
        return children;
    }

    public boolean isLeafNode() {
        return children.isEmpty();
    }
}